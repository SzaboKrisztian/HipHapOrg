package org.hiphap.Screens;

import org.hiphap.Event;
import org.hiphap.EventManager;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class SelectEventScreen extends MenuScreen {
  public SelectEventScreen() {
    addMenuOption("1", "Find by name");
    addMenuOption("2", "Find by date");
    addMenuOption("3", "Find by location");
    addMenuOption("4", "Find by organizer");
  }

  @Override
  void showContent() {
    clearScreen();
  }

  @Override
  Transition handleInput(String input) {
    String query;
    ArrayList<Event> result;
    switch (input) {
      case "1":
        query = clsAndReadString("Enter an event name to search for: ");
        result = EventManager.getInstance().searchByName(query);
        break;
      case "2":
        String timeText = clsAndReadString("Enter the event's end as yyyy-mm-dd hh:mm:ss: ");
        LocalDateTime time;
        try {
          time = LocalDateTime.parse(timeText, Event.DT_FORMAT);
          result = EventManager.getInstance().searchByDate(time);
        } catch (DateTimeParseException e){
          return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
        }
        break;
      case "3":
        query = clsAndReadString("Enter an event location to search for: ");
        result = EventManager.getInstance().searchByLocation(query);
        break;
      case "4":
        query = clsAndReadString("Enter a name to search for: ");
        result = EventManager.getInstance().searchByOrganizer(query);
        break;
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
    if (result.isEmpty()) {
      return new Transition(Transition.Type.ERROR, "No event name matched your query.");
    } else {
      return new EventListView(result).show(null);
    }
  }
}