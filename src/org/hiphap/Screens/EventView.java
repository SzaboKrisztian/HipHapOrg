package org.hiphap.Screens;

import org.hiphap.*;

import java.io.IOException;

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
    addMenuOption("6", "Print event report");
    addMenuOption("7", "Notify subscribed parties");
    addMenuOption("8", "Delete event");
    if (EventManager.getInstance().isTopLevelEvent(currentEvent)) {
      addMenuOption("9", "Manage composition");
    }
  }

  Transition handleInput(String input) {
    switch (input) {
      case "1":
        return new Transition(Transition.Type.SWITCH, new EventEditDetails(currentEvent));
      case "2":
        return new Transition(Transition.Type.SWITCH, new ManageOrganizers(currentEvent));
      case "3":
        return new Transition(Transition.Type.SWITCH, new ManageParticipants(currentEvent));
      case "4":
        return new Transition(Transition.Type.SWITCH, new ManageEventResources(currentEvent));
      case "5":
        return new Transition(Transition.Type.SWITCH, new ManageStaff(currentEvent));
      case "6":
        try {
          FileManager.printEventReport(currentEvent);
          return new Transition(Transition.Type.SUCCESS, "Event report successfully written.");
        } catch (IOException e) {
          return new Transition(Transition.Type.ERROR, "Error writing report file.");
        }
      case "7":
        try {
          boolean result = FileManager.printNotifications(currentEvent);
          if (result) {
            return new Transition(Transition.Type.SUCCESS, "Notifications successfully sent.");
          } else {
            return new Transition(Transition.Type.ERROR, "No entities subscribed to receive notifications found.");
          }
        } catch (IOException e) {
          return new Transition(Transition.Type.ERROR, "Error writing notifications file.");
        }

      case "8":
        if (clsAndReadBoolean("WARNING! Operation cannot be undone. Are you sure you wish to delete the event?")) {
          if (EventManager.getInstance().deleteEvent(currentEvent)) {
            return new Transition(Transition.Type.BACK, "Event successfully deleted.");
          } else {
            return new Transition(Transition.Type.ERROR, "Error deleting event.");
          }
        } else {
          return new Transition(Transition.Type.ERROR, "Operation aborted.");
        }
      case "9":
        if (EventManager.getInstance().isTopLevelEvent(currentEvent)) {
          if (currentEvent instanceof Arrangement) {
            Arrangement currentArrangement = (Arrangement) currentEvent;
            return new Transition(Transition.Type.SWITCH, new ManageComposition(currentArrangement));
          } else {
            return new Transition(Transition.Type.SWITCH, new CompositionPrompt(currentEvent));
          }
        }
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
  }

  public void showContent() {
    clearScreen();
    String noun = "Event";
    if (currentEvent instanceof Arrangement) {
      noun = "Arrangement";
    }
    System.out.printf(noun + " name: %s%n", currentEvent.getName());
    System.out.printf(noun + " type: %s%n", currentEvent.getEventType() == null ? "N/A" : currentEvent.getEventType());
    System.out.printf("Starts: %s%n", currentEvent.getStart() == null ? "N/A" : currentEvent.getStartAsString());
    System.out.printf("Ends: %s%n", currentEvent.getFinish() == null ? "N/A" : currentEvent.getFinishAsString());
    System.out.printf("Location: %s%n", (currentEvent.getLocation() == null || currentEvent.getLocation().equals("")) ? "N/A" : currentEvent.getLocation());
    System.out.printf("Organized for: %s", currentEvent.getOrganizers().isEmpty() ? "N/A" : currentEvent.getOrganizers().get(0));
    if (currentEvent.getOrganizers().size() > 1) {
      System.out.printf(" plus %d more.%n", currentEvent.getOrganizers().size() - 1);
    } else {
      System.out.printf("%n");
    }
    System.out.printf("No. of participants: %s%n", currentEvent.getParticipants().isEmpty() ? "N/A" : currentEvent.getParticipants().size());
    System.out.printf("No. of HHO staff hired: %s, cost: %.2f kr.%n", currentEvent.getStaff().isEmpty() ? "N/A" : currentEvent.getStaff().size(), currentEvent.getStaffCost());
    System.out.printf("Total resources cost: %s kr.%n", currentEvent.getEventResources().isEmpty() ? "N/A" : currentEvent.getResourcesCost());
    System.out.printf("HipHap organizing fee: %.2f kr.%n", currentEvent.getHipHapFee());
    System.out.printf("Total cost: %.2f kr.%n", currentEvent.getTotalEventCost());
    if (this.currentEvent instanceof Arrangement) {
      Double total = 0.0;
      Arrangement arr = (Arrangement) this.currentEvent;
      for (Event event: arr.getSubEvents()) {
        total += event.getTotalEventCost();
      }
      System.out.printf("Sub-events total cost: %.2f kr.%n", total);
      System.out.printf("Grand total cost: %.2f kr. %n", currentEvent.getTotalEventCost() + total);
    }
    System.out.printf("----------%n");
  }


}
