package org.hiphap.MenuScreens;

import org.hiphap.Event;

public class EventView extends MenuScreen {
  private Event currentEvent;

  public EventView(Event event) {
    this.currentEvent = event;
    addMenuOption("1", "Edit event details");
    addMenuOption("2", "Manage organizers");
    addMenuOption("3", "Manage participants");
    addMenuOption("4", "Manage resources");
    addMenuOption("5", "Manage staff");
    addMenuOption("6", "Manage composition");
    addMenuOption("7", "Print event report");
    addMenuOption("8", "Delete event");
  }

  Transition handleInput(String input) {
    Transition result;
    switch (input) {
      case "1":
      case "2":
      case "3":
      case "4":
      case "5":
      case "6":
      case "7":
      case "8":
      default:
        result = new Transition(Transition.Type.INVALID, "Not implemented yet");
    }
    return result;
  }

  public void showContent() {
    printPadding(1);
    System.out.println("Event name: " + currentEvent.getName());
  }
}
