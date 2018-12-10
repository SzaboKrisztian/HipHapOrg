package org.hiphap;

import org.hiphap.Screens.EventView;
import org.hiphap.Screens.LoginScreen;
import org.hiphap.Screens.Screen;
import org.hiphap.Screens.Transition;

import java.io.File;
import java.util.Stack;

import static org.hiphap.Screens.Screen.clsAndReadBoolean;

/**
 * This class is the main entry point in the program. The main function also holds the
 * program's infinite loop, which manages a stack of {@link Screen} objects, that represent
 * each GUI screen. These communicate among themselves by the way of {@link Transition} objects
 * that tell the main loop what action to take next, and can optionally also pass {@link String}
 * messages and arbitrary {@link Object}s among each other.
 */
public class Main {
  /**
   * The program's entry point
   *
   * @param args command line arguments are ignored as they serve no function
   */
  public static void main(String[] args) {
    initData();
    Stack<Screen> screens = new Stack<>();
    screens.push(new LoginScreen(null));
    String lastMessage = null;
    while (true) {
      Transition result = screens.peek().show(lastMessage);
      lastMessage = result.getMessage();
      switch (result.getType()) {
        case SWITCH:
          if (result.getScreen() != null && UserManager.getInstance().isAuthenticated()) {
            screens.push(result.getScreen());
          }
          break;
        case LOGOUT:
          promptToSaveChanges();
          UserManager.getInstance().logout();
          screens.clear();
          screens.push(new LoginScreen("Logout successful."));
          break;
        case BACK:
          if (screens.size() > 1) {
            do {
              screens.pop();
            } while (!screens.peek().isMenuNode());
          }
          break;
        case EXIT:
          promptToSaveChanges();
          System.exit(0);
        case SAVE_DATA:
          if (UserManager.getInstance().isAuthenticated()) {
            saveAllData();
          }
          break;
        case COMPOSITION_CHANGE:
          Event newEvent = (Event) result.getPayload();
          do {
            screens.pop();
          } while (!(screens.peek() instanceof EventView));
          screens.pop();
          screens.push(new EventView(newEvent));
          break;
        case SUCCESS:
        case ERROR:
          break;
      }
    }
  }

  /**
   * Initializes all the singleton manager classes. This has the side effect of
   * also loading all the data from the associated files.
   */
  private static void initData() {
    File directory = new File("data");
    if (directory.mkdir()) {
      Logger.getInstance().write("Data folder not found. Starting from scratch.");
    }
    Logger.getInstance();
    UserManager.getInstance();
    EventManager.getInstance();
    PersonManager.getInstance();
    OrganizationManager.getInstance();
    EmployeeManager.getInstance();
    EventTypeManager.getInstance();
  }

  /**
   * A final prompt to save all system data, used before logging the {@link User} out
   * or completely exiting the program.
   */
  private static void promptToSaveChanges() {
    if (UserManager.getInstance().isAuthenticated()) {
      boolean result = clsAndReadBoolean("Do you wish to save changes before leaving?");
      if (result) {
        saveAllData();
      }
    }
  }

  /**
   * Make each singleton manager class save its respective data to the associated file.
   */
  private static void saveAllData() {
    EventManager.getInstance().saveEventData();
    UserManager.getInstance().saveUserData();
    PersonManager.getInstance().savePersonData();
    OrganizationManager.getInstance().saveOrganizationData();
    EmployeeManager.getInstance().saveEmployeeData();
    EventTypeManager.getInstance().saveEventTypeData();
  }
}
