package org.hiphap.MenuScreens;

public class MainMenuScreen extends MenuScreen {
  public MainMenuScreen() {
    addMenuOption("1", "Create...");
    addMenuOption("2", "Select...");
  }

  Transition handleInput(String input) {
    switch (input) {
      case "1":
        return new Transition(Transition.Type.SWITCH, new CreateMenu());
      case "2":
        return new Transition(Transition.Type.SWITCH, new SelectMenu());
      default:
        return null;
    }
  }

  void init() {

  }

  void showContent() {
    printPadding();
  }
}
