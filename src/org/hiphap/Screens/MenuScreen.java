package org.hiphap.Screens;

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
    for (Map.Entry<String, String> entry : options.entrySet()) {
      System.out.println("[" + entry.getKey() + "] " + entry.getValue());
    }
    if (message != null) {
      System.out.println(message);
    }
    String input = scan.nextLine();
    switch (input.toLowerCase()) {
      case "x":
        action = new Transition(Transition.Type.EXIT);
        break;
      case "s":
        action = new Transition(Transition.Type.SAVE_DATA, "Save successful.");
        break;
      case "b":
        action = new Transition(Transition.Type.BACK);
        break;
      case "l":
        action = new Transition(Transition.Type.LOGOUT);
        break;
      default:
        action = handleInput(input);
        break;
    }
    return action;
  }

  void addMenuOption(String key, String name) {
    this.options.put(key, name);
  }

  abstract void showContent();

  abstract Transition handleInput(String input);
}
