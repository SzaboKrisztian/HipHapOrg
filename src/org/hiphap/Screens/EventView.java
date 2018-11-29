package org.hiphap.Screens;

import org.hiphap.Event;
import org.hiphap.EventManager;

public class EventView extends MenuScreen {
  private Event currentEvent;

  public EventView(Event event) {
    this.currentEvent = event;
    this.setMenuNode(true);
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
        result = new Transition(Transition.Type.SWITCH, new EventEditDetails(currentEvent));
        break;
      case "2":
      case "3":
      case "4":
      case "5":
      case "6":
      case "7":
        result = new Transition(Transition.Type.INVALID, "Not implemented yet");
        break;
      case "8":
        if (readBoolean("WARNING! Operation cannot be undone. Are you sure you wish to delete the event?")) {
          if (EventManager.getInstance().deleteEvent(currentEvent)) {
            result = new Transition(Transition.Type.BACK, "Event successfully deleted.");
          } else {
            result = new Transition(Transition.Type.INVALID, "Error deleting event.");
          }
        } else {
          result = new Transition(Transition.Type.INVALID, "Operation aborted.");
        }
        break;
      default:
        result = new Transition(Transition.Type.INVALID, "Not implemented yet");
    }
    return result;
  }

  public void showContent() {
    printPadding();
    System.out.printf("Event name: %s%n", currentEvent.getName());
    System.out.printf("Event type: %s%n", currentEvent.getEventType() == null ? "N/A" : currentEvent.getEventType());
    System.out.printf("Starts: %s%n", currentEvent.getStart() == null ? "N/A" : currentEvent.getStartAsString());
    System.out.printf("Ends: %s%n", currentEvent.getFinish() == null ? "N/A" : currentEvent.getFinishAsString());
    System.out.printf("Location: %s%n", currentEvent.getLocation() == null ? "N/A" : currentEvent.getLocation());
    System.out.printf("Organized for: %s%n", currentEvent.getOrganizedFor() == null ? "N/A" : currentEvent.getOrganizedFor());
    System.out.printf("No. of attendees: %s%n", currentEvent.getAttendees() == null ? "N/A" : currentEvent.getAttendees().size());
    System.out.printf("No. of HHO staff hired: %s%n", currentEvent.getStaff() == null ? "N/A" : currentEvent.getStaff().size());
    System.out.printf("Total resources cost: %s%n", currentEvent.getEventResources() == null ? "N/A" : currentEvent.getEventResources());
    System.out.printf("----------%n");
  }
}