package org.hiphap;

import java.io.*;
import java.util.Map;

public class FileManager {
  public static boolean printNotifications(Event event) throws IOException {
    boolean found = false;
    for (Map.Entry<Entity, Boolean> entry: event.getOrganizersAsEntrySet()) {
      if (entry.getValue()) {
        found = true;
        break;
      }
    }
    if (!found) {
      for (Map.Entry<Entity, Boolean> entry: event.getParticipantsAsEntrySet()) {
        if (entry.getValue()) {
          found = true;
          break;
        }
      }
    }
    if (found) {
      File outputFile = new File("notifications" + File.separator +
          event.getName().replace(' ', '_') + ".txt");
      outputFile.mkdir();
      PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
      writer.printf("%nOrganizers:%n--------------------%n");
      for (Map.Entry<Entity, Boolean> entry : event.getOrganizersAsEntrySet()) {
        if (entry.getValue()) {
          writer.printf("%s%n", buildNotificationString(entry.getKey()));
        }
      }
      writer.printf("%nParticipants:%n--------------------%n");
      for (Map.Entry<Entity, Boolean> entry : event.getParticipantsAsEntrySet()) {
        if (entry.getValue()) {
          writer.printf("%s%n", buildNotificationString(entry.getKey()));
        }
      }
      return true;
    }
    return false;
  }

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
      } else if (!phone.equals("")) {
        result.append("SMS at: ");
        result.append(phone);
      }
    }
    return result.toString();
  }

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
