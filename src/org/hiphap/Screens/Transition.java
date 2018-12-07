package org.hiphap.Screens;

/**
 * This class provides communication between the different concrete implementations of the
 * {@link Screen} and {@link MenuScreen} abstract base classes.
 */
public class Transition {
  /**
   * An an inner enum type, that tells the main loop what action to take next
   */
  private Type type;
  /**
   * In case of having to switch to a new {@link Screen}, this attribute will
   * contain that {@link Screen} object to switch to. Cannot be null.
   */
  private Screen screen;
  /**
   * An optional {@link String} message to be displayed on the next {@link Screen}. Note
   * that this can also mean the same {@link Screen}, if no switching takes place, just
   * the displaying again of the current {@link Screen}. Can be null.
   */
  private String message;
  /**
   * An optional {@link Object} to be passed to the next {@link Screen}. The receiving
   * {@link Screen} is responsible for receiving it and handling it appropriately.
   */
  private Object payload;

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

  public Screen getScreen() {
    return screen;
  }

  public String getMessage() {
    return message;
  }

  public void setPayload(Object payload) {
    this.payload = payload;
  }

  public Object getPayload() {
    return this.payload;
  }

  public enum Type {
    REPLY,
    SWITCH,
    COMPOSITION_CHANGE,
    BACK,
    SAVE_DATA,
    LOGOUT,
    EXIT,
    SUCCESS,
    ERROR
  }
}
