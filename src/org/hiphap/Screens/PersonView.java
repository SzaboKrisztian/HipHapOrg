package org.hiphap.Screens;

import org.hiphap.Person;
import org.hiphap.PersonManager;

public class PersonView extends MenuScreen {
  private Person currentPerson;

  Transition handleInput(String input) {
    switch (input) {
      case "1":
        String firstName = null;
        do {
          clearScreen();
          System.out.println("Old name: " + currentPerson.toFullString());
          if (firstName != null) {
            System.out.println("First name cannot be blank.");
          }
          firstName = readString("Input first name: ");
        } while (firstName.equals(""));
        String lastName = readString("Input last name: ");
        String middleName = readString("Input middle name: ");
        currentPerson.setFirstName(firstName);
        currentPerson.setLastName(lastName);
        currentPerson.setMiddleName(middleName);
        return new Transition(Transition.Type.SUCCESS, "Person name successfully changed.");
      case "2":
        clearScreen();
        System.out.println("Old email address: " + (currentPerson.getEmail().equals("") ?
            "N/A" : currentPerson.getEmail()));
        currentPerson.setEmail(readString("Input new email address: "));
        return new Transition(Transition.Type.SUCCESS, "Email address successfully changed.");
      case "3":
        clearScreen();
        System.out.println("Old phone number: " + (currentPerson.getPhone().equals("") ?
            "N/A" : currentPerson.getPhone()));
        currentPerson.setPhone(readString("Input new phone number: "));
        return new Transition(Transition.Type.SUCCESS, "Phone number successfully changed.");
      case "4":
        if (clsAndReadBoolean("WARNING! Operation cannot be undone. Are you " +
            "sure you wish to delete this person?")) {
          if (PersonManager.getInstance().deletePerson(currentPerson)) {
            return new Transition(Transition.Type.BACK, "Person successfully deleted.");
          } else {
            return new Transition(Transition.Type.ERROR, "Error deleting person.");
          }
        } else {
          return new Transition(Transition.Type.ERROR, "Operation aborted.");
        }
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
  }

  public PersonView(Person person) {
    this.currentPerson = person;
    this.setMenuNode(true);
    addMenuOption("1", "Edit person name");
    addMenuOption("2", "Edit email address");
    addMenuOption("3", "Edit phone number");
    addMenuOption("4", "Delete person");
  }

  public void showContent() {
    clearScreen();
    System.out.println("Person name: " + currentPerson.toString());
    System.out.println("Email address: " + currentPerson.getEmail());
    System.out.println("Phone number: " + currentPerson.getPhone());
  }
}
