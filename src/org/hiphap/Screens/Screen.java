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

  public static String readString(String message) {
    clearScreen();
    System.out.print(message);

    return ConsoleManager.getInstance().getScanner().nextLine();
  }

  public static boolean readBoolean(String message) {
    clearScreen();
    System.out.println(message);
    System.out.print("Input y for yes, anything else for no: ");
    String reply = ConsoleManager.getInstance().getScanner().nextLine();
    return reply.length() > 0 && reply.toLowerCase().startsWith("y");
  }

  private static void clearScreen() {
    int linesToPrint = 50;

    for (int i = 0; i < linesToPrint; i++) {
      System.out.printf("%n");
    }
  }
}
