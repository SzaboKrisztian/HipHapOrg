package org.hiphap.Screens;

import org.hiphap.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class CreateEventScreen extends Screen {
  public Transition show(String message) {
    Event newEvent;
    String eventName = null;
    do {
      if (eventName != null && eventName.equals("")) {
        System.out.println("This field must be completed");
      }
      eventName = clsAndReadString("Enter the event's name: ");
    } while (eventName.equals(""));
    newEvent = new Event(eventName);
    newEvent.setEventType(pickEventType());
    newEvent.setLocation(clsAndReadString("Enter the event's location: "));
    String startText = clsAndReadString("Enter the event's start as yyyy-mm-dd hh:mm:ss: ");
    String finishText = readString("Enter the event's end as yyyy-mm-dd hh:mm:ss: ");
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
        }
      } catch (DateTimeParseException e) {
        System.out.println("Error parsing finishing date/time. No value set;");
      }
    } catch (DateTimeParseException e) {
      System.out.println("Error parsing starting date/time. No value set;");
    }

    newEvent.setHipHapOrganizer(UserManager.getInstance().getCurrentUser());
    EventManager.getInstance().addEvent(newEvent);
    return new Transition(Transition.Type.BACK, "Event successfully created.");
  }

  private EventType pickEventType() {
    ArrayList<EventType> eventTypes = EventTypeManager.getInstance().getEventTypes();
    if (eventTypes.isEmpty()) {
      return null;
    } else {
      int index = 1;
      for (EventType eventType: eventTypes) {
        System.out.printf("[%d] %s%n", index++, eventType.getName());
      }
      Integer selection = readInteger("Pick an event type: ");
      if (selection != null && selection >= 1 && selection <= eventTypes.size()) {
        return eventTypes.get(selection - 1);
      } else {
        return null;
      }
    }
  }
}
