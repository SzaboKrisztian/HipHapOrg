package org.hiphap.Screens;

import org.hiphap.ConsoleManager;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This abstract class extends the abstract {@link Screen} class, implementing the show
 * method for the general case of creating a screen which displays several menu options
 * that the user can choose from.
 */
public abstract class MenuScreen extends Screen {
  /**
   * A {@link LinkedHashMap} that holds a pair of {@link String} objects; the first
   * represents the key the input the user has to provide to activate a particular menu
   * option, and the second is that option's human-friendly label.
   */
  private LinkedHashMap<String, String> options = new LinkedHashMap<>();

  /**
   * Displays the menu entries on the console and waits for user input. Also
   * handles the generic inputs that are supposed to work on all menu screens.
   *
   * @param message a {@link String} to be displayed on the next screen. Used for
   *                printing success messages, error messages, and other information.
   *                Can be null, thus displaying nothing in particular.
   * @return a {@link Transition} object that tells the main loop what action to take
   */
  public Transition show(String message) {
    Transition action;
    this.showContent();
    for (Map.Entry<String, String> entry : options.entrySet()) {
      System.out.println("[" + entry.getKey() + "] " + entry.getValue());
    }
    if (message != null) {
      System.out.println(message);
    }
    String input = readString("Pick an option: ");
    switch (input.toLowerCase()) {
      case "x":
        action = new Transition(Transition.Type.EXIT);
        break;
      case "s":
        action = new Transition(Transition.Type.SAVE_DATA, "Save successful.");
        break;
      case "b":
        action = new Transition(Transition.Type.BACK);
        break;
      case "l":
        action = new Transition(Transition.Type.LOGOUT);
        break;
      default:
        action = handleInput(input);
        break;
    }
    return action;
  }

  /**
   * This adds a menu entry to the list
   *
   * @param key  a {@link String} that the user has to input to access the associated function
   * @param name a {@link String} human-friendly label for the menu entry
   */
  void addMenuOption(String key, String name) {
    this.options.put(key, name);
  }

  /**
   * An abstract method meant to display some content before the menu entries, such that these
   * are always printed at the bottom of the console, before asking for user input.
   */
  abstract void showContent();

  /**
   * Abstract method that is meant for the subclasses to implement specific behaviour
   * based on the user input.
   *
   * @param input a {@link String} object containing the user's input, to be delegated
   *              to the concrete subclass
   * @return a {@link Transition} object that tells the main loop what action to take
   */
  abstract Transition handleInput(String input);
}
