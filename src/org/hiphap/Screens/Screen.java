package org.hiphap.Screens;

import org.hiphap.ConsoleManager;

/**
 * This abstract base class represents a generic GUI screen
 */
public abstract class Screen {
  /**
   * A flag to mark whether the behaviour of the "Back" action should
   * stop at it, or if it should go further. This is needed to move the user
   * between the important screens, skipping and intermediary ones.
   */
  private boolean menuNode = false;

  /**
   * Check whether this screen is a so-called "menu node"
   *
   * @return true if it is a "menu node"
   */
  public boolean isMenuNode() {
    return menuNode;
  }

  /**
   * Set if the screen should be a "menu node"
   *
   * @param menuNode the flag to set
   */
  public void setMenuNode(boolean menuNode) {
    this.menuNode = menuNode;
  }

  /**
   * The most important method of any screen. This displays itself on the console
   * and returns a {@link Transition} object telling the program's main loop what
   * action to take next.
   *
   * @param message a {@link String} to be displayed on the next screen. Used for
   *                printing success messages, error messages, and other information.
   *                Can be null, thus displaying nothing in particular.
   * @return a {@link Transition} object that tells the main loop what action to take
   */
  public abstract Transition show(String message);

  /**
   * Clears the screen, prints a custom prompt message, and reads the next line of user input.
   *
   * @param message a {@link String} representing the prompt to be displayed to the user
   * @return a line of user input
   */
  public static String clsAndReadString(String message) {
    clearScreen();
    return readString(message);
  }

  /**
   * Prints a custom prompt message, and reads the next line of user input.
   *
   * @param message a {@link String} representing the prompt to be displayed to the user
   * @return a line of user input
   */
  public static String readString(String message) {
    System.out.print(message);

    return ConsoleManager.getInstance().getScanner().nextLine();
  }

  /**
   * Clears the screen, prints a custom prompt message, and reads the next line of user
   * input, which gets interpreted as a boolean. Any input that starts with a 'y' character
   * is considered a value of true, and anything else represents a value of false.
   *
   * @param message a {@link String} representing the prompt to be displayed to the user
   * @return a boolean value representing the user's response
   */
  public static boolean clsAndReadBoolean(String message) {
    clearScreen();
    return readBoolean(message);
  }

  /**
   * Prints a custom prompt message, and reads the next line of user input, which
   * gets interpreted as a boolean. Any input that starts with a 'y' character is
   * considered a value of true, and anything else represents a value of false.
   *
   * @param message a {@link String} representing the prompt to be displayed to the user
   * @return a boolean value representing the user's response
   */
  public static boolean readBoolean(String message) {
    System.out.println(message);
    System.out.print("Input y for yes, anything else for no: ");
    String input = ConsoleManager.getInstance().getScanner().nextLine();
    return input.length() > 0 && input.toLowerCase().startsWith("y");
  }

  /**
   * Prints a custom prompt message, and reads the next line of user input, which
   * gets interpreted as an {@link Integer} value. If this fails, the method returns null.
   *
   * @param message a {@link String} representing the prompt to be displayed to the user
   * @return an {@link Integer} representing the user's input, or null if parsing fails
   */
  public static Integer readInteger(String message) {
    System.out.println(message);
    String input = ConsoleManager.getInstance().getScanner().nextLine();
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  /**
   * Clears the screen, prints a custom prompt message, and reads the next line
   * of user input, which gets interpreted as an {@link Double} value. If this
   * fails, the method returns null.
   *
   * @param message a {@link String} representing the prompt to be displayed to the user
   * @return a {@link Double} representing the user's input, or null if parsing fails
   */
  public static Double clsAndReadDouble(String message) {
    clearScreen();
    return readDouble(message);
  }

  /**
   * Prints a custom prompt message, and reads the next line of user input, which gets
   * interpreted as an {@link Double} value. If this fails, the method returns null.
   *
   * @param message a {@link String} representing the prompt to be displayed to the user
   * @return a {@link Double} representing the user's input, or null if parsing fails
   */
  public static Double readDouble(String message) {
    System.out.println(message);
    String input = ConsoleManager.getInstance().getScanner().nextLine();
    try {
      return Double.parseDouble(input);
    } catch (NumberFormatException e) {
      return null;
    }
  }

  /**
   * Clears the screen by printing blank lines
   */
  public static void clearScreen() {
    int linesToPrint = 50;

    for (int i = 0; i < linesToPrint; i++) {
      System.out.printf("%n");
    }
  }
}
