package org.hiphap;

import java.util.Scanner;
/**
 *this class has a role of instantiating the single scanner within the system from console input
 * @return instance
 * */

public class ConsoleManager {
  private static ConsoleManager instance;
  private static final Scanner scn = new Scanner(System.in);

  private ConsoleManager() {}

  /**
   *
   * @return instance
   */
  public static ConsoleManager getInstance() {
    if (instance == null) {
      instance = new ConsoleManager();
    }
    return instance;
  }

  /**
   * get scanner input
   * @return scn
   */
  public Scanner getScanner() {
    return scn;
  }
}
