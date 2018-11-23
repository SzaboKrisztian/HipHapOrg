package org.hiphap;

import java.util.Scanner;

public class ConsoleManager {
  static ConsoleManager instance;
  static final Scanner scn = new Scanner(System.in);

  private ConsoleManager() {}

  public static ConsoleManager getInstance() {
    if (instance == null) {
      instance = new ConsoleManager();
    }
    return instance;
  }

  public Scanner getScanner() {
    return scn;
  }
}
