package org.hiphap.Screens;

import org.hiphap.Employee;
import org.hiphap.Event;
import org.hiphap.EventManager;
import org.hiphap.FileManager;

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
    addMenuOption("6", "Manage composition");
    addMenuOption("7", "Print event report");
    addMenuOption("8", "Notify subscribed parties");
    addMenuOption("9", "Delete event");
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
        return new Transition(Transition.Type.ERROR, "Not implemented yet");
      case "7":
        try {
          FileManager.printEventReport(currentEvent);
          return new Transition(Transition.Type.SUCCESS, "Event report successfully written.");
        } catch (IOException e) {
          return new Transition(Transition.Type.ERROR, "Error writing report file.");
        }
      case "8":
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

      case "9":
        if (clsAndReadBoolean("WARNING! Operation cannot be undone. Are you sure you wish to delete the event?")) {
          if (EventManager.getInstance().deleteEvent(currentEvent)) {
            return new Transition(Transition.Type.BACK, "Event successfully deleted.");
          } else {
            return new Transition(Transition.Type.ERROR, "Error deleting event.");
          }
        } else {
          return new Transition(Transition.Type.ERROR, "Operation aborted.");
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
    System.out.printf("Organized for: %s", currentEvent.getOrganizers().isEmpty() ? "N/A" : currentEvent.getOrganizers().get(0));
    if (currentEvent.getOrganizers().size() > 1) {
      System.out.printf(" plus %d more.%n", currentEvent.getOrganizers().size() - 1);
    } else {
      System.out.printf("%n");
    }
    System.out.printf("No. of attendees: %s%n", currentEvent.getParticipants().isEmpty() ? "N/A" : currentEvent.getParticipants().size());
    System.out.printf("No. of HHO staff hired: %s, cost: %.2f kr.%n", currentEvent.getStaff().isEmpty() ? "N/A" : currentEvent.getStaff().size(), calculateStaffCost());
    System.out.printf("Total resources cost: %s kr.%n", currentEvent.getEventResources().isEmpty() ? "N/A" : currentEvent.getResourcesCost());
    Double hipHapFee = currentEvent.getResourcesCost() * 0.05 < 1000.0 ? 1000.0 : currentEvent.getResourcesCost() * 0.05;
    System.out.printf("HipHap organizing fee: %.2f kr.%n", hipHapFee);
    System.out.printf("Total event cost: %.2f kr.%n", calculateStaffCost() + currentEvent.getResourcesCost() + hipHapFee);
    System.out.printf("----------%n");
  }

  private double calculateStaffCost() {
    double result = 0.0;
    for (Employee employee: currentEvent.getStaff()) {
      result += employee.getHourlyRate() * currentEvent.getHours(employee);
    }

    return result;
  }
}
