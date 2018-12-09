package org.hiphap.Screens;

import org.hiphap.Arrangement;
import org.hiphap.Event;
import org.hiphap.EventManager;

public class ManageComposition extends MenuScreen {
  private Arrangement arrangement;

  public ManageComposition(Arrangement arrangement) {
    this.arrangement = arrangement;
    setMenuNode(true);
    displaySubEvents();
    addMenuOption("N", "Create new empty event in arrangement");
    addMenuOption("M", "Move existing event to arrangement");
    addMenuOption("E", "Extract event from arrangement");
    addMenuOption("T", "Transform arrangement into normal event");
  }

  @Override
  void showContent() {
    clearScreen();
  }

  @Override
  Transition handleInput(String input) {
    try {
      int option = Integer.parseInt(input);
      if (option >= 1 && option <= arrangement.getSubEvents().size()) {
        return new Transition(Transition.Type.SWITCH, new EventView(arrangement.getSubEvents().get(option - 1)));
      } else {
        return new Transition(Transition.Type.ERROR, "Invalid option; try again.");
      }
    } catch (NumberFormatException e) {
      switch (input.toLowerCase()) {
        case "n":
          arrangement.addSubEvent(new Event(clsAndReadString("Enter a name for the new event: ")));
          return new Transition(Transition.Type.BACK, "Event successfully added.");
        case "m":
          Transition reply = new SelectEventScreen().show(null);
          if (reply.getType() == Transition.Type.REPLY && reply.getPayload() != null) {
            Event selectedEvent = (Event) reply.getPayload();
            if (selectedEvent instanceof Arrangement) {
              return new Transition(Transition.Type.ERROR, "Cannot add an arrangement to another arrangement");
            }
            arrangement.addSubEvent(selectedEvent);
            EventManager.getInstance().deleteEvent(selectedEvent);
            return new Transition(Transition.Type.SUCCESS, "Event successfully moved into arrangement.");
          } else {
            return reply;
          }
        case "e":
          clearScreen();
          displaySubEvents();
          Integer selection = readInteger("Select event: ");
          if (selection != null) {
            if (selection >= 1 && selection <= arrangement.getSubEvents().size()) {
              Event selectedEvent = arrangement.getSubEvents().get(selection - 1);
              EventManager.getInstance().addEvent(selectedEvent);
              arrangement.removeEvent(selectedEvent);
              return new Transition(Transition.Type.SUCCESS, "Event successfully extracted from arrangement.");
            } else {
              return new Transition(Transition.Type.ERROR, "Invalid option; try again.");
            }
          } else {
            return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
          }
        case "t":
          if (arrangement.getSubEvents().isEmpty()) {
            EventManager.getInstance().addEvent(new Event(this.arrangement));
            EventManager.getInstance().deleteEvent(this.arrangement);
            return new Transition(Transition.Type.COMPOSITION_CHANGE, "Arrangement successfully transformed into event.");
          } else {
            return new Transition(Transition.Type.ERROR, "Arrangement must first be free of sub-events");
          }
        default:
          return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
      }
    }
  }

  private void displaySubEvents() {
    int index = 1;
    for (Event event: arrangement.getSubEvents()) {
      addMenuOption(Integer.toString(index++), event.getName());
    }
  }
}
