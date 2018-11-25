package org.hiphap.MenuScreens;

import org.hiphap.ConsoleManager;
import org.hiphap.Event;
import org.hiphap.EventManager;

public class SearchEventByNameScreen extends Screen {
  public Transition show(String message) {
    System.out.println("Enter a name: ");
    String input = ConsoleManager.getInstance().getScanner().nextLine();
    Event event = EventManager.getInstance().getEventByName(input);
    if (event != null) {
      return new Transition(Transition.Type.SWITCH, new EventView(event));
    } else {
      return new Transition(Transition.Type.BACK, "No event by that name found.");
    }
  }
}