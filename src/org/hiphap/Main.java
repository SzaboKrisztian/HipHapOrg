package org.hiphap;

import org.hiphap.MenuScreens.LoginScreen;
import org.hiphap.MenuScreens.MenuScreen;
import org.hiphap.MenuScreens.Screen;
import org.hiphap.MenuScreens.Transition;

import java.util.Stack;

public class Main {
  public static void main(String[] args) {
    Stack<Screen> menus = new Stack<>();
    menus.push(new LoginScreen());
    String lastMessage = null;
    while (true) {
      Transition result = menus.peek().show(lastMessage);
      lastMessage = result.getMessage();
      switch (result.getType()) {
        case SWITCH:
          if (result.getScreen() != null) {
            menus.push(result.getScreen());
          }
          break;
        case LOGOUT:
          menus.clear();
          menus.push(new LoginScreen());
          break;
        case BACK:
          do {
            menus.pop();
          } while (!(menus.peek() instanceof MenuScreen));
          break;
        case EXIT:
          System.exit(0);
        case INVALID:
          break;
      }
    }
  }
}
