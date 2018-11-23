package org.hiphap.MenuScreens;

public class LoginScreen extends MenuScreen {
  MenuScreen handleInput(String input) {
    switch (input) {
      case "1":
        return new MainMenuScreen();
      case "0":
        System.exit(0);
      default:
        return null;
    }
  }

  void init() {
    this.options.put("0", "Exit");
    this.options.put("1", "Login");
  }

  public void showContent() {

  }
}
