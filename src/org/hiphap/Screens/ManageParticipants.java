package org.hiphap.Screens;

import org.hiphap.Entity;
import org.hiphap.Event;
import org.hiphap.Organization;
import org.hiphap.Person;
import java.util.ArrayList;

public class ManageParticipants extends MenuScreen {
  private Event currentEvent;

  public ManageParticipants(Event event) {
    this.currentEvent = event;
    addMenuOption("1", "View participants");
    addMenuOption("2", "Add participant from persons database");
    addMenuOption("3", "Add participant from organizations database");
    addMenuOption("4", "Add new person as participant");
    addMenuOption("5", "Add new organization as participant");
    addMenuOption("6", "Delete participant");
  }


  @Override
  void showContent() {
    clearScreen();
  }

  @Override
  Transition handleInput(String input) {
    Transition result;
    boolean subscribeToNotifications;
    switch (input) {
      case "1":
        ArrayList<Entity> list = filterParticipants();
        for (Entity item: list) {
          System.out.printf(" - %s%n", item);
        }
        readString("Any input to continue;");
        return new Transition(Transition.Type.SUCCESS);
      case "2":
        result = new SelectPersonScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Person person = (Person) result.getPayload();
          subscribeToNotifications = clsAndReadBoolean("Subscribe person to notifications?");
          currentEvent.addParticipant(person, subscribeToNotifications);
          return new Transition(Transition.Type.SUCCESS, "Person successfully added to participants list.");
        } else {
          return result;
        }
      case "3":
        result = new SelectOrganizationScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Organization organization = (Organization) result.getPayload();
          subscribeToNotifications = clsAndReadBoolean("Subscribe organization to notifications?");
          currentEvent.addParticipant(organization, subscribeToNotifications);
          return new Transition(Transition.Type.SUCCESS, "Organization successfully added to participants list.");
        } else {
          return result;
        }
      case "4":
        String firstName = null;
        do {
          clearScreen();
          if (firstName != null) {
            System.out.println("First name cannot be blank");
          }
          firstName = readString("Enter the person's first name: ");
        } while (firstName.equals(""));
        Person newPerson = new Person(firstName);
        newPerson.setLastName(clsAndReadString("Enter the person's last name: "));
        newPerson.setMiddleName(clsAndReadString("Enter the person's middle name: "));
        newPerson.setPhone(clsAndReadString("Enter the person's phone number: "));
        newPerson.setEmail(clsAndReadString("Enter the person's email address: "));
        subscribeToNotifications = clsAndReadBoolean("Subscribe person to notifications?");
        currentEvent.addParticipant(newPerson, subscribeToNotifications);
        return new Transition(Transition.Type.SUCCESS, "Person successfully added to participant list.");
      case "5":
        String name = null;
        do {
          clearScreen();
          if (name != null) {
            System.out.println("Organization name cannot be blank");
          }
          name = readString("Enter the organization's name: ");
        } while (name.equals(""));
        Organization newOrganization = new Organization(name);
        newOrganization.setAddress(clsAndReadString("Enter the organization's address: "));
        newOrganization.setPhone(clsAndReadString("Enter the organization's phone number: "));
        newOrganization.setEmail(clsAndReadString("Enter the organization's email address: "));
        subscribeToNotifications = clsAndReadBoolean("Subscribe organization to notifications?");
        currentEvent.addParticipant(newOrganization, subscribeToNotifications);
        return new Transition(Transition.Type.SUCCESS, "Organization successfully added to participant list.");
      case "6":
        ArrayList<Entity> filteredParticipants = filterParticipants();
        clearScreen();
        int index = 1;
        for (Entity entity: filteredParticipants) {
          System.out.printf("[%d] %s%n", index++, entity);
        }
        Integer choice = readInteger("Select which participant to delete: ");
        if (choice != null) {
          if (choice >= 1 && choice <= filteredParticipants.size()) {
            currentEvent.deleteParticipant(filteredParticipants.get(choice - 1));
            return new Transition(Transition.Type.SUCCESS, "Participant successfully removed from list.");
          } else {
            return new Transition(Transition.Type.ERROR, "Invalid selection; try again.");
          }
        } else {
          return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
        }
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
  }

  private ArrayList<Entity> filterParticipants() {
    String query = clsAndReadString("Enter a search query");
    ArrayList<Entity> filteredParticipants = new ArrayList<>();
    for (Entity entity: currentEvent.getParticipants()) {
      if (entity instanceof Person) {
        Person item = (Person) entity;
        if (item.getFirstName().toLowerCase().contains(query.toLowerCase())
            || item.getMiddleName().toLowerCase().contains(query.toLowerCase())
            || item.getLastName().toLowerCase().contains(query.toLowerCase())) {
          filteredParticipants.add(item);
        }
      } else if (entity instanceof Organization) {
        Organization item = (Organization) entity;
        if (item.getName().toLowerCase().contains(query.toLowerCase())) {
          filteredParticipants.add(item);
        }
      }
    }
    return filteredParticipants;
  }
}
