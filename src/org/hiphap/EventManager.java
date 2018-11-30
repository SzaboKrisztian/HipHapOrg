package org.hiphap;

import java.io.File;
import java.util.ArrayList;

public class EventManager {
  private static EventManager instance;
  private ArrayList<Event> events = new ArrayList<>();
  private final String EVENTS_DATA_FILE = "data".concat(File.separator).concat("events.dat");

  private EventManager() {
    loadEventData();
  }

  public static EventManager getInstance() {
    if (instance == null) {
      instance = new EventManager();
    }
    return instance;
  }

  public boolean loadEventData() {
    Object data = FileManager.loadBinaryDataFromFile(EVENTS_DATA_FILE);
    if (data != null) {
      try {
        events = (ArrayList<Event>) data;
        Logger.getInstance().write("Event data loaded successfully.");
        return true;
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading event data: " + e.toString());
        return false;
      }
    }
    return false;
  }

  public void saveEventData() {
    FileManager.saveBinaryDataToFile(EVENTS_DATA_FILE, events);
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

  public void addEvent(Event event) {
    events.add(event);
    saveEventData();
  }

  public boolean deleteEvent(Event event) {
    boolean result = events.remove(event);
    saveEventData();
    return result;
  }

  public void clearEventsOfType(EventType type) {
    for (Event event: events) {
      if (event.getEventType() == type) {
        event.setEventType(null);
      }
    }
  }
}
