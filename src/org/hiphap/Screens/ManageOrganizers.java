package org.hiphap.Screens;

import org.hiphap.Entity;
import org.hiphap.Event;
import org.hiphap.Organization;
import org.hiphap.Person;
import java.util.ArrayList;

public class ManageOrganizers extends MenuScreen {
  private Event currentEvent;

  public ManageOrganizers(Event event) {
    this.currentEvent = event;
    addMenuOption("1", "View organizers");
    addMenuOption("2", "Add organizer from persons database");
    addMenuOption("3", "Add organizer from organizations database");
    addMenuOption("4", "Add new person as organizer");
    addMenuOption("5", "Add new organization as organizer");
    addMenuOption("6", "Delete organizer");
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
        ArrayList<Entity> list = filterOrganizers();
        for (Entity item: list) {
          System.out.printf(" - %s%n", item);
        }
        return new Transition(Transition.Type.SUCCESS);
      case "2":
        result = new SelectPersonScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Person person = (Person) result.getPayload();
          subscribeToNotifications = clsAndReadBoolean("Subscribe person to notifications?");
          currentEvent.addOrganizer(person, subscribeToNotifications);
          return new Transition(Transition.Type.SUCCESS, "Person successfully added to organizers list.");
        } else {
          return result;
        }
      case "3":
        result = new SelectOrganizationScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Organization organization = (Organization) result.getPayload();
          subscribeToNotifications = clsAndReadBoolean("Subscribe organization to notifications?");
          currentEvent.addOrganizer(organization, subscribeToNotifications);
          return new Transition(Transition.Type.SUCCESS, "Organization successfully added to organizers list.");
        } else {
          return result;
        }
      case "4":
        String firstName = null;
        do {
          clearScreen();
          if (firstName != null && firstName.equals("")) {
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
        currentEvent.addOrganizer(newPerson, subscribeToNotifications);
        return new Transition(Transition.Type.SUCCESS, "Person successfully added to organizers list.");
      case "5":
        String name = null;
        do {
          clearScreen();
          if (name != null && name.equals("")) {
            System.out.println("Organization name cannot be blank");
          }
          name = readString("Enter the organization's name: ");
        } while (name.equals(""));
        Organization newOrganization = new Organization(name);
        newOrganization.setAddress(clsAndReadString("Enter the organization's address: "));
        newOrganization.setPhone(clsAndReadString("Enter the organization's phone number: "));
        newOrganization.setEmail(clsAndReadString("Enter the organization's email address: "));
        subscribeToNotifications = clsAndReadBoolean("Subscribe organization to notifications?");
        currentEvent.addOrganizer(newOrganization, subscribeToNotifications);
        return new Transition(Transition.Type.SUCCESS, "Organization successfully added to organizers list.");
      case "6":
        ArrayList<Entity> filteredOrganizers = filterOrganizers();
        clearScreen();
        int index = 1;
        for (Entity entity: filteredOrganizers) {
          System.out.printf("[%d] %s%n", index++, entity);
        }
        Integer choice = readInteger("Select which organizer to delete: ");
        if (choice != null) {
          if (choice >= 1 && choice <= filteredOrganizers.size()) {
            boolean success = currentEvent.deleteOrganizer(filteredOrganizers.get(choice - 1));
            if (success) {
              return new Transition(Transition.Type.SUCCESS, "Organizer successfully removed from list.");
            } else {
              return new Transition(Transition.Type.INVALID, "Error removing organizer from list.");
            }
          } else {
            return new Transition(Transition.Type.INVALID, "Invalid selection; try again.");
          }
        } else {
          return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
        }
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
  }

  private ArrayList<Entity> filterOrganizers() {
    String query = clsAndReadString("Enter a search query");
    ArrayList<Entity> filteredOrganizers = new ArrayList<>();
    for (Entity entity: currentEvent.getOrganizers()) {
      if (entity instanceof Person) {
        Person item = (Person) entity;
        if (item.getFirstName().toLowerCase().contains(query.toLowerCase())
            || item.getMiddleName().toLowerCase().contains(query.toLowerCase())
            || item.getLastName().toLowerCase().contains(query.toLowerCase())) {
          filteredOrganizers.add(item);
        }
      } else if (entity instanceof Organization) {
        Organization item = (Organization) entity;
        if (item.getName().toLowerCase().contains(query.toLowerCase())) {
          filteredOrganizers.add(item);
        }
      }
    }
    return filteredOrganizers;
  }
}
