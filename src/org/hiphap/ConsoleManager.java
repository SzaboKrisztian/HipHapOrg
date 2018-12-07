package org.hiphap;

import java.util.Scanner;

/**
 * This class is responsible for creating a single instance of {@link Scanner} that
 * is accessible from all throughout the system, to be used for user input
 */

public class ConsoleManager {
  private static ConsoleManager instance;
  private static final Scanner scn = new Scanner(System.in);

  private ConsoleManager() {
  }

  public static ConsoleManager getInstance() {
    if (instance == null) {
      instance = new ConsoleManager();
    }
    return instance;
  }

  /**
   * Gets a reference to the single {@link Scanner} object ever created withing the system
   *
   * @return the {@link Scanner} reference
   */
  public Scanner getScanner() {
    return scn;
  }
}
