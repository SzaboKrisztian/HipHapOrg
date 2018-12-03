package org.hiphap.Screens;

import org.hiphap.UserManager;

public class MainMenuScreen extends MenuScreen {
  public MainMenuScreen() {
    this.setMenuNode(true);
    addMenuOption("1", "Create...");
    addMenuOption("2", "Select...");
    if (UserManager.getInstance().getCurrentUser().isAdmin()) {
      addMenuOption("3", "Manage users");
    }
  }

  Transition handleInput(String input) {
    switch (input) {
      case "1":
        return new Transition(Transition.Type.SWITCH, new CreateMenu());
      case "2":
        return new Transition(Transition.Type.SWITCH, new SelectMenu());
      case "3":
        if (UserManager.getInstance().getCurrentUser().isAdmin()) {
          return new Transition(Transition.Type.SWITCH, new ManageUsers());
        }
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
  }

  void showContent() {
    printPadding();
  }
}
