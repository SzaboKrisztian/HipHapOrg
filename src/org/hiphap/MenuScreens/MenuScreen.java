package org.hiphap.MenuScreens;

import org.hiphap.ConsoleManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class MenuScreen implements Screen {
  HashMap<String, String> options = new HashMap<>();
  Scanner scan = ConsoleManager.getInstance().getScanner();

  MenuScreen() {
    init();
  }

  public Screen showMenuOptions() {
    this.showContent();
    Screen next = null;
    while (true) {
      for (Map.Entry<String, String> entry : options.entrySet()) {
        System.out.println("[" + entry.getKey() + "] " + entry.getValue());
      }
      String input = scan.nextLine();
      if (!options.keySet().contains(input.toLowerCase())) {
        System.out.println("Invalid input; try again: ");
      } else {
          next = handleInput(input);
          break;
      }
    }
    return next;
  }

  abstract void showContent();

  abstract Screen handleInput(String input);

  abstract void init();
}
