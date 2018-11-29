package org.hiphap.Screens;

import org.hiphap.UserManager;

public class LoginScreen extends MenuScreen {
  private String message;

  public LoginScreen(String message) {
    this.setMenuNode(true);
    this.message = message;
    addMenuOption("1", "Login");
  }
  Transition handleInput(String input) {
    switch (input) {
      case "1":
        String username, password;
        username = readString("Enter your username: ");
        password = readString("Enter your password: ");
        if (UserManager.getInstance().authenticate(username, password)) {
          return new Transition(Transition.Type.SWITCH, new MainMenuScreen(), "Authentication successful.");
        } else {
          return new Transition(Transition.Type.INVALID, "Username and/or password incorrect.");
        }
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again: ");
    }
  }

  public void showContent() {
    printPadding();
    if (this.message != null) {
      System.out.println(message + "\n");
    }
    System.out.println("Welcome to the HipHapOrg Event Planner");
    System.out.println("----------------------------------------");
    System.out.println("In all menus [B] is back, [X] is exit, and [L] is logout");
    System.out.println("Choose an option to continue\n");
  }
}
