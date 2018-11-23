package org.hiphap.MenuScreens;

public class MainMenuScreen extends MenuScreen {
  Transition handleInput(String input) {
    switch (input) {
      case "2":
        return new Transition(Transition.Type.SWITCH, new SearchEventByNameScreen());
      case "1":
        return new Transition(Transition.Type.SWITCH, new MainMenuScreen());
      default:
        return null;
    }
  }

  void init() {
    addMenuOption("1", "Create...");
    addMenuOption("2", "Select event");
  }

  void showContent() {
    printPadding();
  }
}
