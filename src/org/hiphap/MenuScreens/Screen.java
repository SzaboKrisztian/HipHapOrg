package org.hiphap.MenuScreens;

import org.hiphap.ConsoleManager;

public abstract class Screen {
  public abstract Transition show(String message);

  public Transition show() {
    return show(null);
  }

  public static String readString(String message) {
    int linesToPrint = 24;

    if (message != null) {
      linesToPrint--;
    }

    for (int i = 0; i < linesToPrint; i++) {
      System.out.printf("%n");
    }

    System.out.print(message);

    return ConsoleManager.getInstance().getScanner().nextLine();
  }

  public static String readString() {
    return readString(null);
  }
}
