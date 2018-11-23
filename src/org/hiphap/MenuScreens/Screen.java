package org.hiphap.MenuScreens;

public abstract class Screen {
  public abstract Transition show(String message);

  public Transition show() {
    return show(null);
  }
}
