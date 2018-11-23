package org.hiphap;

import org.hiphap.MenuScreens.LoginScreen;
import org.hiphap.MenuScreens.MenuScreen;
import org.hiphap.MenuScreens.Screen;

import java.util.Stack;

public class Main {
  public static void main(String[] args) {
    Stack<Screen> menus = new Stack<>();
    menus.push(new LoginScreen());
    while (true) {
      Screen result = menus.peek().showMenuOptions();
      if (result == null) {
        menus.pop();
        if (!(menus.peek() instanceof MenuScreen)) {
          menus.pop();
        }
      } else {
        menus.push(result);
      }
    }
  }
}
