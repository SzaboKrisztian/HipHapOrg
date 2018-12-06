package org.hiphap.Screens;

import org.hiphap.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

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
        String name = clsAndReadString("Old name: " + currentEvent.getName() + "\nInput new name, blank to cancel: ");
        if (!name.equals("")) {
          currentEvent.setName(name);
          return new Transition(Transition.Type.SUCCESS, "Event name successfully changed.");
        } else {
          return new Transition(Transition.Type.SUCCESS, "Operation cancelled.");
        }
      case "2":
        return new Transition(Transition.Type.SWITCH, new EventTypeListView(currentEvent));
      case "3":
        clearScreen();
        System.out.println("Old start time: " + currentEvent.getStartAsString());
        String startText = readString("Enter new starting time as yyyy-mm-dd hh:mm:ss: ");
        try {
          LocalDateTime newStart = LocalDateTime.parse(startText, Event.DT_FORMAT);
          currentEvent.setStart(newStart);
          String message = "Start time changed.";
          if (currentEvent.getFinish() != null && currentEvent.getFinish().isBefore(newStart)) {
            currentEvent.setFinish(null);
            message += " Finish time reset because of time conflict.";
          }
          return new Transition(Transition.Type.SUCCESS, message);
        } catch (DateTimeParseException e) {
          return new Transition(Transition.Type.ERROR, "Invalid input.");
        }
      case "4":
        clearScreen();
        System.out.println("Old finishing time: " + currentEvent.getFinishAsString());
        String finishText = readString("Enter new finishing time as yyyy-mm-dd hh:mm:ss: ");
        try {
          LocalDateTime newFinish = LocalDateTime.parse(finishText, Event.DT_FORMAT);
          currentEvent.setFinish(newFinish);
          String message = "Finishing time changed.";
          if (currentEvent.getStart() != null && currentEvent.getStart().isAfter(newFinish)) {
            currentEvent.setStart(null);
            message += " Starting time reset because of time conflict.";
          }
          return new Transition(Transition.Type.SUCCESS, message);
        } catch (DateTimeParseException e) {
          return new Transition(Transition.Type.ERROR, "Invalid input.");
        }
      case "5":
        clearScreen();
        String oldLocation;
        if (currentEvent.getLocation() == null || currentEvent.getLocation().equals("")) {
          oldLocation = "N/A";
        } else {
          oldLocation = currentEvent.getLocation();
        }
        String location = clsAndReadString("Old location: " + oldLocation + "\nInput new location, blank to cancel: ");
        if (!location.equals("")) {
          currentEvent.setLocation(location);
          return new Transition(Transition.Type.SUCCESS, "Event location successfully changed.");
        } else {
          return new Transition(Transition.Type.SUCCESS, "Operation cancelled.");
        }
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
  }

  public void showContent() {
    clearScreen();
    System.out.printf("Event name: %s%n", currentEvent.getName());
    System.out.printf("Event type: %s%n", currentEvent.getEventType() == null ? "N/A" : currentEvent.getEventType());
    System.out.printf("Starts: %s%n", currentEvent.getStart() == null ? "N/A" : currentEvent.getStartAsString());
    System.out.printf("Ends: %s%n", currentEvent.getFinish() == null ? "N/A" : currentEvent.getFinishAsString());
    System.out.printf("Location: %s%n", (currentEvent.getLocation() == null || currentEvent.getLocation().equals("")) ? "N/A" : currentEvent.getLocation());
    System.out.printf("----------%n");
  }
}
