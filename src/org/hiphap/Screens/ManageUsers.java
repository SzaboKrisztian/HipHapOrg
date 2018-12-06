package org.hiphap.Screens;

import org.hiphap.User;
import org.hiphap.UserManager;

public class ManageUsers extends MenuScreen {
  public ManageUsers() {
    addMenuOption("1", "List system users");
    addMenuOption("2", "Change current user's password");
    addMenuOption("3", "Create new user");
    addMenuOption("4", "Delete user");
  }

  @Override
  void showContent() {
    clearScreen();
    System.out.println("Currently logged in as: " + UserManager.getInstance().getCurrentUser().getUsername());
  }

  @Override
  Transition handleInput(String input) {
    switch (input) {
      case "1":
        clearScreen();
        for (String username: UserManager.getInstance().getUsernames()) {
          System.out.printf(" - %s%n", username);
        }
        readString("Any input to continue...\n");
        return new Transition(Transition.Type.SUCCESS);
      case "2":
        return changePassword();
      case "3":
        return createNewUser();
      case "4":
        return deleteUser();
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
  }

  private Transition changePassword() {
    String oldPassword = clsAndReadString("Enter old password: ");
    String newPassword = clsAndReadString("Enter new password: ");
    String repeatPassword = clsAndReadString("Confirm new password: ");
    if (UserManager.getInstance().getCurrentUser().changePassword(oldPassword, newPassword, repeatPassword)) {
      return new Transition(Transition.Type.SUCCESS, "Password successfully changed.");
    } else {
      return new Transition(Transition.Type.ERROR, "Wrong password, or new passwords do not match.");
    }
  }

  private Transition createNewUser() {
    String username = clsAndReadString("Enter username: ");
    if (!UserManager.getInstance().isUsernameTaken(username)) {
      String password, repeatPassword;
      clearScreen();
      do {
        password = readString("Choose a password: ");
        repeatPassword = clsAndReadString("Repeat password: ");
        if (!password.equals(repeatPassword)) {
          System.out.println("Passwords do not match. Try again.");
        }
      } while (!password.equals(repeatPassword));
      boolean isAdmin = clsAndReadBoolean("Give user administrator privileges? ");
      UserManager.getInstance().addUser(new User(username, password, isAdmin));
      return new Transition(Transition.Type.SUCCESS, "User successfully added.");
    } else {
      return new Transition(Transition.Type.ERROR, "Username already exists.");
    }
  }

  private Transition deleteUser() {
    String username = clsAndReadString("Which user would you like to delete? ");
    if (UserManager.getInstance().isUsernameTaken(username)) {
      if (!UserManager.getInstance().getCurrentUser().getUsername().equals(username)) {
        if (!readBoolean("WARNING! This action cannot be undone. Are you sure " +
            "you want to delete the user \"" + username + "\"?")) {
          return new Transition(Transition.Type.ERROR, "Operation aborted.");
        }
        if (UserManager.getInstance().deleteUser(username)) {
          return new Transition(Transition.Type.SUCCESS, "User deleted successfully.");
        } else {
          return new Transition(Transition.Type.ERROR, "Error deleting user.");
        }
      } else {
        return new Transition(Transition.Type.ERROR, "Cannot delete the currently logged in user.");
      }
    } else {
      return new Transition(Transition.Type.ERROR, "Username does not exist.");
    }
  }
}
