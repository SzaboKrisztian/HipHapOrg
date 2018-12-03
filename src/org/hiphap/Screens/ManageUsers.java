package org.hiphap.Screens;

import org.hiphap.User;
import org.hiphap.UserManager;

public class ManageUsers extends MenuScreen {
  public ManageUsers() {
    addMenuOption("1", "Change current user's password");
    addMenuOption("2", "Create new user");
    addMenuOption("3", "Delete user");
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
        return changePassword();
      case "2":
        return createNewUser();
      case "3":
        return deleteUser();
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
  }

  private Transition changePassword() {
    String oldPassword = clsAndReadString("Enter old password: ");
    String newPassword = clsAndReadString("Enter new password: ");
    String repeatPassword = clsAndReadString("Confirm new password: ");
    if (UserManager.getInstance().getCurrentUser().changePassword(oldPassword, newPassword, repeatPassword)) {
      return new Transition(Transition.Type.SUCCESS, "Password successfully changed.");
    } else {
      return new Transition(Transition.Type.INVALID, "Wrong password, or new passwords do not match.");
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
      UserManager.getInstance().addUser(new User(username, password, null, isAdmin));
      return new Transition(Transition.Type.SUCCESS, "User successfully added.");
    } else {
      return new Transition(Transition.Type.INVALID, "Username already exists.");
    }
  }

  private Transition deleteUser() {
    String username = clsAndReadString("Which user would you like to delete? ");
    if (UserManager.getInstance().isUsernameTaken(username)) {
      if (!UserManager.getInstance().getCurrentUser().getUsername().equals(username)) {
        if (UserManager.getInstance().deleteUser(username)) {
          return new Transition(Transition.Type.SUCCESS, "User deleted successfully.");
        } else {
          return new Transition(Transition.Type.INVALID, "Error deleting user.");
        }
      } else {
        return new Transition(Transition.Type.INVALID, "Cannot delete the currently logged in user.");
      }
    } else {
      return new Transition(Transition.Type.INVALID, "Username does not exist.");
    }
  }
}
