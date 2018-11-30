package org.hiphap.Screens;

import org.hiphap.Event;

public class EventEditDetails extends MenuScreen {
  private Event currentEvent;

  public EventEditDetails(Event event) {
    this.currentEvent = event;
    this.setMenuNode(true);
    addMenuOption("1", "Edit name");
    addMenuOption("2", "Select event type");
    addMenuOption("3", "Edit event start");
    addMenuOption("4", "Edit event finish");
    addMenuOption("5", "Edit location");
  }

  Transition handleInput(String input) {
    switch (input) {
      case "1":
        String name = readString("Old name: " + currentEvent.getName() + "\nInput new name, blank to cancel: ");
        if (!name.equals("")) {
          currentEvent.setName(name);
          return new Transition(Transition.Type.SUCCESS, "Event name successfully changed.");
        } else {
          return new Transition(Transition.Type.SUCCESS, "Operation cancelled.");
        }
      case "2":
        return new Transition(Transition.Type.SWITCH, new EventTypeListView());
      case "3":
      case "4":
        return new Transition(Transition.Type.INVALID, "Not implemented yet");
      case "5":
        String location = readString("Old location: " + currentEvent.getLocation() + "\nInput new location, blank to cancel: ");
        if (!location.equals("")) {
          currentEvent.setLocation(location);
          return new Transition(Transition.Type.SUCCESS, "Event location successfully changed.");
        } else {
          return new Transition(Transition.Type.SUCCESS, "Operation cancelled.");
        }
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
  }

  public void showContent() {
    printPadding();
    System.out.printf("Event name: %s%n", currentEvent.getName());
    System.out.printf("Event type: %s%n", currentEvent.getEventType() == null ? "N/A" : currentEvent.getEventType());
    System.out.printf("Starts: %s%n", currentEvent.getStart() == null ? "N/A" : currentEvent.getStartAsString());
    System.out.printf("Ends: %s%n", currentEvent.getFinish() == null ? "N/A" : currentEvent.getFinishAsString());
    System.out.printf("Location: %s%n", currentEvent.getLocation() == null ? "N/A" : currentEvent.getLocation());
    System.out.printf("----------%n");
  }
}
