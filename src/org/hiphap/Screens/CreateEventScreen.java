package org.hiphap.Screens;

import org.hiphap.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class CreateEventScreen extends Screen {
  public Transition show(String message) {
    boolean isArrangement =
        clsAndReadBoolean("Would you like to make this an arrangement of events?");
    String noun = isArrangement ? "arrangement" : "event";
    Event newEvent;
    String eventName = null;
    do {
      if (eventName != null) {
        System.out.println("This field must be completed");
      }
      eventName = clsAndReadString("Enter the " + noun + "'s name: ");
    } while (eventName.equals(""));
    if (!isArrangement) {
      newEvent = new Event(eventName);
    } else {
      newEvent = new Arrangement(eventName);
    }
    System.out.println("Select the event's type: ");
    newEvent.setEventType(pickEventType());
    newEvent.setLocation(clsAndReadString("Enter the " + noun + "'s location: "));
    String startText = clsAndReadString("Enter the " + noun + "'s start as yyyy-mm-dd hh:mm:ss: ");
    String finishText = readString("Enter the " + noun + "'s end as yyyy-mm-dd hh:mm:ss: ");
    LocalDateTime start, finish;
    try {
      start = LocalDateTime.parse(startText, Event.DT_FORMAT);
      newEvent.setStart(start);
      try {
        finish = LocalDateTime.parse(finishText, Event.DT_FORMAT);
        if (finish.isAfter(start)) {
          newEvent.setFinish(finish);
        } else {
          System.out.println("Finishing time must be after starting time.");
          readString("[Enter] to continue...");
        }
      } catch (DateTimeParseException e) {
        System.out.println("Error parsing finishing date/time. No value set;");
        readString("[Enter] to continue...");
      }
    } catch (DateTimeParseException e) {
      System.out.println("Error parsing starting date/time. No value set;");
      readString("[Enter] to continue...");
    }

    newEvent.setHipHapOrganizer(UserManager.getInstance().getCurrentUser());
    EventManager.getInstance().addEvent(newEvent);
    return new Transition(Transition.Type.BACK, "The " + noun + " was successfully created.");
  }

  private EventType pickEventType() {
    Transition reply = new EventTypeListView().show(null);
    if (reply.getType() == Transition.Type.REPLY && reply.getPayload() != null) {
      return (EventType) reply.getPayload();
    } else {
      return null;
    }
  }
}
