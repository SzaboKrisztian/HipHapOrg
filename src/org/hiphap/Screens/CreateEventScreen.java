package org.hiphap.Screens;

import org.hiphap.Event;
import org.hiphap.EventManager;
import org.hiphap.UserManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;


public class CreateEventScreen extends Screen {
  public Transition show(String message) {
    Event newEvent;
    String eventName = null;
    do {
      if (eventName != null && eventName.equals("")) {
        System.out.println("This field must be completed");
      }
      eventName = readString("Enter the event's name: ");
    } while (eventName.equals(""));
    newEvent = new Event(eventName);
    newEvent.setLocation(readString("Enter the event's location: "));
    String startText = readString("Enter the event's start as yyyy-mm-dd [hh:mm:ss]: ");
    String finishText = readString("Enter the event's end as yyyy-mm-dd [hh:mm:ss]: ");
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

    newEvent.setOrganizer(UserManager.getInstance().getCurrentUser());
    EventManager.getInstance().addEvent(newEvent);
    return new Transition(Transition.Type.BACK, "Event successfully created.");
  }
}