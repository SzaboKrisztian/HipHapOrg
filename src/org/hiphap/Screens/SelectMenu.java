package org.hiphap.Screens;

public class SelectMenu extends MenuScreen {
  public SelectMenu() {
    this.setMenuNode(true);
    addMenuOption("1", "Select an event");
    addMenuOption("2", "Select a person");
    addMenuOption("3", "Select an organization");
    addMenuOption("4", "Select an employee");
  }

  Transition handleInput(String input) {
    switch (input) {
      case "1":
        return new Transition(Transition.Type.SWITCH, new SelectEventScreen());
      case "2":
        return new Transition(Transition.Type.SWITCH, new SelectPersonScreen());
      case "3":
        return new Transition(Transition.Type.SWITCH, new SelectOrganizationScreen());
      case "4":
        return new Transition(Transition.Type.SWITCH, new SelectEmployeeScreen());
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
  }

  public void showContent() {
    printPadding();
  }
}