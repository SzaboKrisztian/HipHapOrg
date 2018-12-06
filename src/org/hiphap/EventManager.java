package org.hiphap;

import java.io.File;
import java.time.LocalDateTime;
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

  @SuppressWarnings("unchecked")
  public void loadEventData() {
    Object data = FileManager.loadBinaryDataFromFile(EVENTS_DATA_FILE);
    if (data != null) {
      try {
        events = (ArrayList<Event>) data;
        Logger.getInstance().write("Event data loaded successfully.");
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading event data: " + e.toString());
      }
    }
  }

  public void saveEventData() {
    FileManager.saveBinaryDataToFile(EVENTS_DATA_FILE, events);
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
        Logger.getInstance().write("Found one. hopefully cleared.");
        event.setEventType(null);
      }
    }
  }

  public ArrayList<Event> searchByName(String name) {
    ArrayList<Event> result = new ArrayList<>();

    if (UserManager.getInstance().getCurrentUser().isAdmin()) {
      for (Event event : events) {
        if (event.getName().toLowerCase().contains(name)) {
          result.add(event);
        }
      }
    } else {
      for (Event event : events) {
        if (event.getName().toLowerCase().contains(name) &&
            event.getHipHapOrganizer() == UserManager.getInstance().getCurrentUser()) {
          result.add(event);
        }
      }
    }

    return result;
  }

  public ArrayList<Event> searchByLocation(String location) {
    ArrayList<Event> result = new ArrayList<>();

    if (UserManager.getInstance().getCurrentUser().isAdmin()) {
      for (Event event : events) {
        if (event.getLocation().toLowerCase().contains(location)) {
          result.add(event);
        }
      }
    } else {
      for (Event event : events) {
        if (event.getLocation().toLowerCase().contains(location) &&
            event.getHipHapOrganizer() == UserManager.getInstance().getCurrentUser()) {
          result.add(event);
        }
      }
    }

    return result;
  }

  public ArrayList<Event> searchByDate(LocalDateTime time) {
    ArrayList<Event> result = new ArrayList<>();

    if (UserManager.getInstance().getCurrentUser().isAdmin()) {
      for (Event event : events) {
        if (isTimeInsideEvent(time, event)) {
          result.add(event);
        }
      }
    } else {
      for (Event event : events) {
        if (isTimeInsideEvent(time, event) &&
            event.getHipHapOrganizer() == UserManager.getInstance().getCurrentUser()) {
          result.add(event);
        }
      }
    }

    return result;
  }

  private boolean isTimeInsideEvent(LocalDateTime time, Event event) {
    return (event.getStart() != null && (event.getStart().toLocalDate().
        isBefore(time.toLocalDate()) || event.getStart().toLocalDate().
        isEqual(time.toLocalDate()))) && (event.getFinish() != null &&
        (event.getFinish().toLocalDate().isAfter(time.toLocalDate()) ||
        event.getFinish().toLocalDate().isEqual(time.toLocalDate())));
  }

  public boolean isTopLevelEvent(Event event) {
    return this.events.contains(event);
  }
}
