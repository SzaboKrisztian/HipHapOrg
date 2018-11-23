package org.hiphap.MenuScreens;

import org.hiphap.Event;

public class EventView extends MenuScreen {
  private Event currentEvent;
  Screen handleInput(String input) {
    return null;
  }

  public EventView(Event event) {
    this.currentEvent = event;
  }

  void init() {
    this.options.put("1", "Something");
  }

  public void showContent() {
    System.out.println("Event name: " + currentEvent.getName());
  }
}
