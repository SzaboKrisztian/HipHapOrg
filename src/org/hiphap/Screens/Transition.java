package org.hiphap.Screens;

public class Transition {
  private Type type;
  private Screen screen;
  private String message;
  private Object payload;

  public Transition() {}

  public Transition(Type type) {
    this.type = type;
  }

  public Transition(Type type, Screen screen) {
    this(type);
    this.screen = screen;
  }

  public Transition(Type type, String message) {
    this(type);
    this.message = message;
  }

  public Transition(Type type, Screen screen, String message) {
    this(type, screen);
    this.message = message;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Type getType() {
    return this.type;
  }

  public void setScreen(Screen screen) {
    this.screen = screen;
  }

  public Screen getScreen() {
    return screen;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setPayload(Object payload) {
    this.payload = payload;
  }

  public Object getPayload() {
    return this.payload;
  }

  public enum Type {
    SWITCH,
    BACK,
    SAVE_DATA,
    LOGOUT,
    EXIT,
    SUCCESS,
    INVALID
  }
}
