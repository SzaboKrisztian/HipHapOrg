package org.hiphap.MenuScreens;

public class LoginScreen extends MenuScreen {
  public LoginScreen() {
    addMenuOption("1", "Login");
  }
  Transition handleInput(String input) {
    switch (input) {
      case "1":
        return new Transition(Transition.Type.SWITCH, new MainMenuScreen());
      default:
        return null;
    }
  }

  public void showContent() {
    printPadding(5);
    System.out.println("Welcome to the HipHapOrg Event Planner");
    System.out.println("----------------------------------------");
    System.out.println("In all menus [B] is back, [X] is exit, and [L] is logout");
    System.out.println("Choose an option to continue\n");
  }
}
