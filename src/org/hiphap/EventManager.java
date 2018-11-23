package org.hiphap;

import java.util.ArrayList;

public class EventManager {
  private static EventManager instance;
  private ArrayList<Event> events = new ArrayList<>();

  private EventManager() {
    events.add(new Event("Whatever"));
  }

  public static EventManager getInstance() {
    if (instance == null) {
      instance = new EventManager();
    }
    return instance;
  }

  public Event getEventByName(String name) {
    Event result = null;

    for (Event item: events) {
      if (item.getName().equals(name)) {
        result = item;
      }
    }

    return result;
  }
}
