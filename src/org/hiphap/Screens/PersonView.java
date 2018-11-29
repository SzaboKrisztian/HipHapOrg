package org.hiphap.Screens;

import org.hiphap.Person;

public class PersonView extends MenuScreen {
  private Person currentPerson;

  Transition handleInput(String input) {
    Transition result;
    switch (input) {
      case "1":
      case "2":
      default:
        result = new Transition(Transition.Type.INVALID, "Not implemented yet");
    }
    return result;
  }

  public PersonView(Person person) {
    this.currentPerson = person;
    this.setMenuNode(true);
    addMenuOption("1", "Edit person details");
    addMenuOption("2", "Delete person");
  }

  public void showContent() {
    printPadding();
    System.out.println("Person name: " + currentPerson);
  }
}
