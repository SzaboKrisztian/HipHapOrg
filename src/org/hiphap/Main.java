package org.hiphap;

import org.hiphap.Screens.LoginScreen;
import org.hiphap.Screens.Screen;
import org.hiphap.Screens.Transition;
import java.util.Stack;

public class Main {
  public static void main(String[] args) {
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
          UserManager.getInstance().logout();
          screens.clear();
          screens.push(new LoginScreen("Logout successful."));
          break;
        case BACK:
          do {
            screens.pop();
          } while (!(screens.peek().isMenuNode()));
          break;
        case EXIT:
          System.exit(0);
        case SUCCESS:
        case INVALID:
          break;
      }
    }
  }
}
