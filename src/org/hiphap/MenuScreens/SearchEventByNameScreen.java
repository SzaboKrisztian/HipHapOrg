package org.hiphap.MenuScreens;

import org.hiphap.ConsoleManager;
import org.hiphap.Event;
import org.hiphap.EventManager;

public class SearchEventByNameScreen implements Screen {
  public Screen showMenuOptions() {
    System.out.println("Enter a name: ");
    String input = ConsoleManager.getInstance().getScanner().nextLine();
    Event event = EventManager.getInstance().getEventByName(input);
    if (event != null) {
      return new EventView(event);
    } else {
      System.out.println("No event by that name found.");
      return null;
    }
  }
}