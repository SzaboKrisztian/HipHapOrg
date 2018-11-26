package org.hiphap;

import org.hiphap.MenuScreens.LoginScreen;
import org.hiphap.MenuScreens.MenuScreen;
import org.hiphap.MenuScreens.Screen;
import org.hiphap.MenuScreens.Transition;
import java.util.Stack;

public class Main {
  public static void main(String[] args) {
    Stack<Screen> screens = new Stack<>();
    screens.push(new LoginScreen());
    String lastMessage = null;
    while (true) {
      Transition result = screens.peek().show(lastMessage);
      lastMessage = result.getMessage();
      switch (result.getType()) {
        case SWITCH:
          if (result.getScreen() != null) {
            screens.push(result.getScreen());
          }
          break;
        case LOGOUT:
          screens.clear();
          screens.push(new LoginScreen());
          break;
        case BACK:
          do {
            screens.pop();
          } while (!(screens.peek() instanceof MenuScreen));
          break;
        case EXIT:
          System.exit(0);
        case INVALID:
          break;
      }
    }
  }
}
