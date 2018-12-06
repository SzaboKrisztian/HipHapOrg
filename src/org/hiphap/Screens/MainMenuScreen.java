package org.hiphap.Screens;

import org.hiphap.FileManager;
import org.hiphap.UserManager;

import java.io.IOException;

public class MainMenuScreen extends MenuScreen {
  public MainMenuScreen() {
    this.setMenuNode(true);
    addMenuOption("1", "Create...");
    addMenuOption("2", "Select...");
    if (UserManager.getInstance().getCurrentUser().isAdmin()) {
      addMenuOption("3", "Manage users");
      addMenuOption("4", "Export csv data");
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
      case "4":
        if (UserManager.getInstance().getCurrentUser().isAdmin()) {
          try {
            FileManager.exportCsvData();
            return new Transition(Transition.Type.SUCCESS, "Data successfully exported.");
          } catch (IOException e) {
            return new Transition(Transition.Type.ERROR, "Error exporting to csv files.");
          }
        }
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
  }

  void showContent() {
    clearScreen();
  }
}
