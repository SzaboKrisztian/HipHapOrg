package org.hiphap.Screens;

import org.hiphap.UserManager;

public class LoginScreen extends MenuScreen {
  private String message;

  public LoginScreen(String message) {
    this.setMenuNode(true);
    this.message = message;
    if (UserManager.getInstance().isAuthenticated()) {
      addMenuOption("1", "Proceed as " + UserManager.getInstance().getCurrentUser().getUsername());
      addMenuOption("2", "Switch user");
    } else {
      addMenuOption("1", "Login");
    }
  }
  Transition handleInput(String input) {
    if (UserManager.getInstance().isAuthenticated()) {
      switch (input) {
        case "1":
          return new Transition(Transition.Type.SWITCH, new MainMenuScreen());
        case "2":
          return new Transition(Transition.Type.LOGOUT);
        default:
          return new Transition(Transition.Type.INVALID, "Invalid input; try again: ");
      }
    } else {
      switch (input) {
        case "1":
          String username, password;
          username = clsAndReadString("Enter your username: ");
          password = clsAndReadString("Enter your password: ");
          if (UserManager.getInstance().authenticate(username, password)) {
            return new Transition(Transition.Type.SWITCH, new MainMenuScreen(), "Authentication successful.");
          } else {
            return new Transition(Transition.Type.INVALID, "Username and/or password incorrect.");
          }
        default:
          return new Transition(Transition.Type.INVALID, "Invalid input; try again: ");
      }
    }
  }

  public void showContent() {
    printPadding();
    if (this.message != null) {
      System.out.println(message + "\n");
    }
    System.out.println("Welcome to the HipHapOrg Event Planner");
    System.out.println("----------------------------------------");
    System.out.println("In all menus [B] is back, [S] is save all data, [L] is logout, and [X] is exit");
    System.out.println("Choose an option to continue\n");
  }
}
