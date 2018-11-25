package org.hiphap.MenuScreens;

public class CreateMenu extends MenuScreen {
  public CreateMenu() {
    addMenuOption("1", "Create new event");
    addMenuOption("2", "Create new person");
    addMenuOption("3", "Create new organization");
    addMenuOption("4", "Create new employee");
  }

  Transition handleInput(String input) {
    switch (input) {
      case "1":
      case "2":
      case "3":
      case "4":
        return new Transition(Transition.Type.INVALID, "Not implemented yet");
      default:
        return null;
    }
  }

  public void showContent() {
    printPadding();
  }
}
