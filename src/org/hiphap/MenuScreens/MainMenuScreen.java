package org.hiphap.MenuScreens;

public class MainMenuScreen extends MenuScreen {
  Screen handleInput(String input) {
    switch (input) {
      case "2":
        return new SearchEventByNameScreen();
      case "1":
        return new MainMenuScreen();
      case "0":
      default:
        return null;
    }
  }

  void init() {
    this.options.put("0", "Logout");
    this.options.put("2", "Select event");
    this.options.put("1", "Create...");
  }

  void showContent() {

  }
}
