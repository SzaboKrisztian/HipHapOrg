package org.hiphap.Screens;

import org.hiphap.ConsoleManager;

public abstract class Screen {
  private boolean menuNode = false;

  public boolean isMenuNode() {
    return menuNode;
  }

  public void setMenuNode(boolean menuNode) {
    this.menuNode = menuNode;
  }

  public abstract Transition show(String message);

  public static String clsAndReadString(String message) {
    clearScreen();
    return readString(message);
  }

  public static String readString(String message) {
    System.out.print(message);

    return ConsoleManager.getInstance().getScanner().nextLine();
  }

  public static boolean clsAndReadBoolean(String message) {
    clearScreen();
    return readBoolean(message);
  }

  public static boolean readBoolean(String message) {
    System.out.println(message);
    System.out.print("Input y for yes, anything else for no: ");
    String input = ConsoleManager.getInstance().getScanner().nextLine();
    return input.length() > 0 && input.toLowerCase().startsWith("y");
  }

  public static Integer readInteger(String message) {
    clearScreen();
    System.out.println(message);
    String input = ConsoleManager.getInstance().getScanner().nextLine();
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  public static Double readDouble(String message) {
    clearScreen();
    System.out.println(message);
    String input = ConsoleManager.getInstance().getScanner().nextLine();
    try {
      return Double.parseDouble(input);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  public static void clearScreen() {
    int linesToPrint = 50;

    for (int i = 0; i < linesToPrint; i++) {
      System.out.printf("%n");
    }
  }
}
