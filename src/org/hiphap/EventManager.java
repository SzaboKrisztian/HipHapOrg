package org.hiphap;

import java.util.ArrayList;

public class EventManager {
  private static EventManager instance;
  private ArrayList<Event> events = new ArrayList<>();

  private EventManager() {
    events.add(new Event("Whatever"));
    events.add(new Event("Whatevs"));
    events.add(new Event("Something"));
    events.add(new Event("Someone"));
    events.add(new Event("Hello"));
    events.add(new Event("Is it me"));
    events.add(new Event("you're looking for"));
    events.add(new Event("and I wonder"));
    events.add(new Event("where you are"));
    events.add(new Event("and I wonder"));
    events.add(new Event("what you do"));
    events.add(new Event("Lionel Ritchie's party"));
    events.add(new Event("So will the real Shady"));
    events.add(new Event("please stand up"));
    events.add(new Event("and put one of those fingers"));
    events.add(new Event("on each end up"));
    events.add(new Event("to be proud to be outta your mind"));
    events.add(new Event("and outta control"));
    events.add(new Event("and one more time, loud as you can"));
    events.add(new Event("how's it go?"));
    events.add(new Event("his palms are sweaty"));
    events.add(new Event("knees weak, arms are heavy"));
    events.add(new Event("the vomit on his sweater, already"));
    events.add(new Event("mom's spaghetti"));
  }

  public static EventManager getInstance() {
    if (instance == null) {
      instance = new EventManager();
    }
    return instance;
  }

  public ArrayList<Event> searchByName(String name) {
    ArrayList<Event> result = new ArrayList<>();

    for (Event event: events) {
      if (event.getName().toLowerCase().contains(name)) {
        result.add(event);
      }
    }

    return result;
  }

  public boolean deleteEvent(Event event) {
    return events.remove(event);
  }
}
