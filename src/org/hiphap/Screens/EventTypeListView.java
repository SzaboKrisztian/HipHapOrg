package org.hiphap.Screens;

import org.hiphap.EventType;
import org.hiphap.EventTypeManager;

import java.util.ArrayList;

public class EventTypeListView extends MenuScreen {
  private ArrayList<EventType> eventTypes;

  public EventTypeListView() {
    eventTypes = EventTypeManager.getInstance().getEventTypes();
    for (int i = 0; i < eventTypes.size(); i++) {
      addMenuOption(String.valueOf(i + 1), eventTypes.get(i).getName());
    }
    System.out.println("Inside event type list view");
  }

  @Override
  void showContent() {

  }

  @Override
  Transition handleInput(String input) {
    Transition result = new Transition(Transition.Type.BACK);
    switch (input) {
      case "n":
        String name = readString("Enter a name for the new event type: ");
        EventType newEventType = new EventType(name);
        EventTypeManager.getInstance().addEventType(newEventType);
        result.setPayload(newEventType);
        return result;
      case "d":
        displayEntries();
        String delInput = readString("Which entry would you like to delete?");
        try {
          int delOption = Integer.parseInt(delInput);
          if (delOption >= 1 && delOption <= eventTypes.size()) {
            EventTypeManager.getInstance().deleteEventType(eventTypes.get(delOption - 1));
            return new Transition(Transition.Type.SUCCESS, "Successfully deleted entry.");
          } else {
            return new Transition(Transition.Type.INVALID, "Invalid option; try again.");
          }
        } catch (NumberFormatException e) {
          return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
        }
      case "r":
        displayEntries();
        String renInput = readString("Which entry would you like to rename?");
        try {
          int renOption = Integer.parseInt(renInput);
          if (renOption >= 1 && renOption <= eventTypes.size()) {
            EventType selected = eventTypes.get(renOption);
            String newName = readString("Old name: " + selected.getName() + "\nInput new name: ");
            if (!newName.equals("")) {
              selected.setName(newName);
            } else {
              return new Transition(Transition.Type.INVALID, "Operation aborted.");
            }
            return new Transition(Transition.Type.SUCCESS, "Successfully renamed entry.");
          } else {
            return new Transition(Transition.Type.INVALID, "Invalid option; try again.");
          }
        } catch (NumberFormatException e) {
          return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
        }
      default:
        int option;
        try {
          option = Integer.parseInt(input);
          if (option >= 1 && option <= eventTypes.size()) {
            result.setPayload(eventTypes.get(option - 1));
            return result;
          } else {
            return new Transition(Transition.Type.INVALID, "Invalid option; try again: ");
          }
        } catch (NumberFormatException e) {
          return new Transition(Transition.Type.INVALID, "Invalid input; try again: ");
        }
    }
  }

  private void displayEntries() {
    for (int i = 0; i < eventTypes.size(); i++) {
      System.out.printf("[%d] %s%n", i + 1, eventTypes.get(i));
    }
  }
}
