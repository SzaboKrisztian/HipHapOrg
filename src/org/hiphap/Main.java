package org.hiphap;

import org.hiphap.Screens.EventView;
import org.hiphap.Screens.LoginScreen;
import org.hiphap.Screens.Screen;
import org.hiphap.Screens.Transition;

import java.io.File;
import java.util.Stack;

import static org.hiphap.Screens.Screen.clsAndReadBoolean;

public class Main {
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
          do {
            screens.pop();
          } while (!screens.peek().isMenuNode());
          break;
        case EXIT:
          promptToSaveChanges();
          System.exit(0);
        case SAVE_DATA:
          if (UserManager.getInstance().isAuthenticated()) {
            saveAllData();
          }
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
    EmployeeManager.getInstance().loadEmployeeData();
    EventTypeManager.getInstance();
  }

  private static void promptToSaveChanges() {
    if (UserManager.getInstance().isAuthenticated()) {
      boolean result = clsAndReadBoolean("Do you wish to save changes before leaving?");
      if (result) {
        saveAllData();
      }
    }
  }

  private static void saveAllData() {
    EventManager.getInstance().saveEventData();
    UserManager.getInstance().saveUserData();
    PersonManager.getInstance().savePersonData();
    OrganizationManager.getInstance().saveOrganizationData();
    EmployeeManager.getInstance().saveEmployeeData();
  }
}
