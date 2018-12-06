package org.hiphap.Screens;

import org.hiphap.Event;
import org.hiphap.EventType;
import org.hiphap.EventTypeManager;

import java.util.ArrayList;

public class EventTypeListView extends MenuScreen {
  private Event currentEvent;
  private ArrayList<EventType> eventTypes;

  public EventTypeListView(Event currentEvent) {
    this.currentEvent = currentEvent;
    eventTypes = EventTypeManager.getInstance().getEventTypes();
    for (int i = 0; i < eventTypes.size(); i++) {
      addMenuOption(String.valueOf(i + 1), eventTypes.get(i).getName());
    }
    System.out.println("[N] = new entry, [D] = delete entry, [R] = rename entry");
  }

  @Override
  void showContent() {

  }

  @Override
  Transition handleInput(String input) {
    switch (input) {
      case "n":
        String name = clsAndReadString("Enter a name for the new event type: ");
        EventType newEventType = new EventType(name);
        EventTypeManager.getInstance().addEventType(newEventType);
        this.currentEvent.setEventType(newEventType);
        return new Transition(Transition.Type.BACK, "Successfully added new event type.");
      case "d":
        clearScreen();
        displayEntries();
        String delInput = readString("Which entry would you like to delete?");
        try {
          int delOption = Integer.parseInt(delInput);
          if (delOption >= 1 && delOption <= eventTypes.size()) {
            EventType selectedEventType = eventTypes.get(delOption - 1);
            EventTypeManager.getInstance().deleteEventType(eventTypes.get(delOption - 1));
            if (currentEvent.getEventType() == selectedEventType) {
              currentEvent.setEventType(null);
            }
            return new Transition(Transition.Type.SUCCESS, "Successfully deleted entry.");
          } else {
            return new Transition(Transition.Type.ERROR, "Invalid option; try again.");
          }
        } catch (NumberFormatException e) {
          return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
        }
      case "r":
        displayEntries();
        String renInput = readString("Which entry would you like to rename?");
        try {
          int renOption = Integer.parseInt(renInput);
          if (renOption >= 1 && renOption <= eventTypes.size()) {
            EventType selected = eventTypes.get(renOption);
            String newName = clsAndReadString("Old name: " + selected.getName() + "\nInput new name: ");
            if (!newName.equals("")) {
              selected.setName(newName);
            } else {
              return new Transition(Transition.Type.ERROR, "Operation aborted.");
            }
            return new Transition(Transition.Type.SUCCESS, "Successfully renamed entry.");
          } else {
            return new Transition(Transition.Type.ERROR, "Invalid option; try again.");
          }
        } catch (NumberFormatException e) {
          return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
        }
      default:
        int option;
        try {
          option = Integer.parseInt(input);
          if (option >= 1 && option <= eventTypes.size()) {
            EventType selectedEventType = eventTypes.get(option - 1);
            currentEvent.setEventType(selectedEventType);
            return new Transition(Transition.Type.BACK, "Event type successfully set.");
          } else {
            return new Transition(Transition.Type.ERROR, "Invalid option; try again: ");
          }
        } catch (NumberFormatException e) {
          return new Transition(Transition.Type.ERROR, "Invalid input; try again: ");
        }
    }
  }

  private void displayEntries() {
    for (int i = 0; i < eventTypes.size(); i++) {
      System.out.printf("[%d] %s%n", i + 1, eventTypes.get(i));
    }
  }
}
