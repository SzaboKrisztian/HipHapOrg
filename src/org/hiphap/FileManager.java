package org.hiphap;

import java.io.*;
import java.util.Map;

/**
 * This class only holds static methods meant to help with writing to and reading from
 * files on disk.
 */
public class FileManager {
  /**
   * Sends out the notifications to the subscribed parties. In our system, this is just
   * simulated by creating a file under the "notifications" folder and writing them there.
   * The filename is generated based on the {@link Event} object's name.
   *
   * @param event the {@link Event} to whose subscribed parties to send notifications to
   * @return true if there is at least one party found that subscribed to notifications
   * @throws IOException if there is any error while writing the file to disk
   */
  public static boolean printNotifications(Event event) throws IOException {
    boolean found = false;
    for (Map.Entry<Entity, Boolean> entry : event.getOrganizersAsEntrySet()) {
      if (entry.getValue()) {
        found = true;
        break;
      }
    }
    if (!found) {
      for (Map.Entry<Entity, Boolean> entry : event.getParticipantsAsEntrySet()) {
        if (entry.getValue()) {
          found = true;
          break;
        }
      }
    }
    if (found) {
      String path = "notifications";
      File directory = new File(path);
      directory.mkdir();
      File outputFile = new File(path.concat(File.separator).concat(event.
          getName().replace(' ', '_')).concat(".txt"));
      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
      writer.println("\nOrganizers:\n--------------------");
      for (Map.Entry<Entity, Boolean> entry : event.getOrganizersAsEntrySet()) {
        if (entry.getValue()) {
          writer.println(buildNotificationString(entry.getKey()));
        }
      }
      writer.println("\nParticipants:\n--------------------");
      for (Map.Entry<Entity, Boolean> entry : event.getParticipantsAsEntrySet()) {
        if (entry.getValue()) {
          writer.println(buildNotificationString(entry.getKey()));
        }
      }
      writer.flush();
      writer.close();
      return true;
    }
    return false;
  }

  /**
   * Export {@link Employee}, {@link Organization}, and {@link Person} data to csv files
   * under the "exported_data" folder. The associated files are called, in turn, "employees.csv",
   * "organizations.csv", and "persons.csv". These are overwritten if the already exist.
   *
   * @throws IOException if there is any error while writing the files to disk
   */
  public static void exportCsvData() throws IOException {
    String path = "exported_data";
    File directory = new File(path);
    directory.mkdir();

    File outputFile = new File(path.concat(File.separator).concat("employees.csv"));
    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
    for (Employee employee : EmployeeManager.getInstance().getEmployees()) {
      writer.println(employee.getCsvString());
    }
    writer.flush();
    writer.close();

    outputFile = new File(path.concat(File.separator).concat("organizations.csv"));
    writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
    for (Organization organization : OrganizationManager.getInstance().getOrganizations()) {
      writer.println(organization.getCsvString());
    }
    writer.flush();
    writer.close();

    outputFile = new File(path.concat(File.separator).concat("persons.csv"));
    writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
    for (Person person : PersonManager.getInstance().getPersons()) {
      writer.println(person.getCsvString());
    }
    writer.flush();
    writer.close();
  }

  /**
   * Print a report under the "reports" folder, for a particular {@link Event} object,
   * containing a summary of all the data associated with it. The file name is generated
   * based on the {@link Event} object's name.
   *
   * @param event the {@link Event} based on which to generate the report
   * @throws IOException if there is any error while writing the file to disk
   */
  public static void printEventReport(Event event) throws IOException {
    String path = "reports";
    File directory = new File(path);
    directory.mkdir();
    File outputFile = new File(path.concat(File.separator).concat(event.
        getName().replace(' ', '_')).concat(".txt"));
    PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
    writer.printf("General report for event: %s%n", event.getName());
    writer.printf("Type of event: %s%n", event.eventType == null ? "N/A" : event.eventType.getName());
    writer.printf("Event location: %s%n", event.location == null || event.location.equals("") ? "N/A" : event.getLocation());
    writer.printf("Starting date and time: %s%n", event.getStart() == null ? "N/A" : event.getStart().format(Event.DT_FORMAT));
    writer.printf("Ending date and time: %s%n", event.getFinish() == null ? "N/A" : event.getFinish().format(Event.DT_FORMAT));
    writer.printf("Event created by system user: %s%n", event.getHipHapOrganizer().getUsername());
    writer.println("Event organized for: " + (event.getOrganizers().isEmpty() ? "N/A" : ""));
    int i, size;
    if (!event.getOrganizers().isEmpty()) {
      i = 0;
      size = event.getOrganizers().size();
      while (i < size - 2) {
        writer.printf("%s, ", event.getOrganizers().get(i++));
      }
      writer.printf("%s%n", event.getOrganizers().get(size - 1));
    }
    size = event.getParticipants().size();
    writer.printf("Participants: %d%n", size);
    if (!event.getParticipants().isEmpty()) {
      i = 0;
      while (i < size - 2) {
        writer.printf("%s, ", event.getParticipants().get(i++));
      }
      writer.printf("%s%n", event.getParticipants().get(size - 1));
    }
    writer.printf("Event resources: %.2f kr.%n", event.getResourcesCost());
    for (EventResource eventResource : event.getEventResources()) {
      writer.printf(" - %s", eventResource.getName());
      if (eventResource.getCost() != null) {
        writer.printf(": %.2f", eventResource.getCost());
      }
      writer.printf("%n");
    }
    writer.printf("Event staff: %d hired.%n", event.getStaff().size());
    if (!event.getStaff().isEmpty()) {
      StringBuilder staffString = new StringBuilder();
      for (Employee employee : event.getStaff()) {
        staffString.append(employee).append(", ");
      }
      writer.print(staffString.toString().substring(0, staffString.length() - 2));
    }
    writer.flush();
    writer.close();
  }

  /**
   * Generates a single notification line based on available contact details
   * of a particular {@link Entity}.
   *
   * @param entity the {@link Entity} for who to generate the notification
   * @return a {@link String} containing the notification text
   */
  private static String buildNotificationString(Entity entity) {
    StringBuilder result = new StringBuilder();
    String email = entity.getEmail();
    String phone = entity.getPhone();
    if (!email.equals("") || !phone.equals("")) {
      result.append("Notified ");
      result.append(entity.toString());
      result.append(" by ");
      if (!email.equals("")) {
        result.append("email at: ");
        result.append(email);
        if (!phone.equals("")) {
          result.append(" and SMS at: ");
          result.append(phone);
        }
      } else {
        result.append("SMS at: ");
        result.append(phone);
      }
    }
    return result.toString();
  }

  /**
   * Deserialize an {@link Object} from binary data in a file.
   *
   * @param filename the file whose contents are to be deserialized
   * @return an {@link Object} holding the data from the file
   */
  public static Object loadBinaryDataFromFile(String filename) {
    FileInputStream fileInputStream;
    try {
      fileInputStream = new FileInputStream(filename);
    } catch (FileNotFoundException e) {
      return null;
    }
    try {
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      Object result = objectInputStream.readObject();
      objectInputStream.close();
      return result;
    } catch (IOException | ClassNotFoundException e) {
      // Very small chances of this happening; we won't handle this.
    }
    return null;
  }

  /**
   * Serialize an {@link Object} as binary data and write it to a file.
   *
   * @param filename the file to which to write
   * @param object   the {@link Object} to be written
   */
  public static void saveBinaryDataToFile(String filename, Object object) {
    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;
    try {
      fileOutputStream = new FileOutputStream(filename);
      try {
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
      } catch (IOException e) {
        Logger.getInstance().write(e.toString());
        // some chances that this might happen; we don't handle this in our case;
      }
    } catch (FileNotFoundException e) {
      // can't really happen
    }
  }
}
