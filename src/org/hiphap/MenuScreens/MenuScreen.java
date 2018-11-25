package org.hiphap.MenuScreens;

import org.hiphap.ConsoleManager;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class MenuScreen extends Screen {
  private LinkedHashMap<String, String> options = new LinkedHashMap<>();
  private Scanner scan = ConsoleManager.getInstance().getScanner();

  public Transition show(String message) {
    Transition action;
    this.showContent();
    if (message != null) {
      System.out.println(message);
    }
    for (Map.Entry<String, String> entry : options.entrySet()) {
      System.out.println("[" + entry.getKey() + "] " + entry.getValue());
    }
    String input = scan.nextLine();
    if (!options.keySet().contains(input.toLowerCase())) {
      switch (input.toLowerCase()) {
        case "x":
          action = new Transition(Transition.Type.EXIT);
          break;
        case "b":
          action = new Transition(Transition.Type.BACK);
          break;
        case "l":
          action = new Transition(Transition.Type.LOGOUT);
          break;
        default:
          action = new Transition(Transition.Type.INVALID, "Invalid input; try again");
          break;
      }
    } else {
        action = handleInput(input);
    }
    return action;
  }

  void printPadding(int minusNumLines) {
    int numOptions = options.size();
    int screenSize = 24;
    int numBlankLines = screenSize - numOptions - minusNumLines;
    for (int i = 0; i < numBlankLines; i++) {
      System.out.printf("%n");
    }
  }

  void printPadding() {
    printPadding(0);
  }

  void addMenuOption(String key, String name) {
    this.options.put(key, name);
  }

  abstract void showContent();

  abstract Transition handleInput(String input);
}
