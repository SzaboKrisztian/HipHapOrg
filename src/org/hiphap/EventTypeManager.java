package org.hiphap;

import java.io.File;
import java.util.ArrayList;

public class EventTypeManager {
  private static EventTypeManager instance;
  private ArrayList<EventType> eventTypes = new ArrayList<>();
  private final String EventTypeS_DATA_FILE = "data".concat(File.separator).concat("eventTypes.dat");

  private EventTypeManager() {
    loadEventTypeData();
  }

  public static EventTypeManager getInstance() {
    if (instance == null) {
      instance = new EventTypeManager();
    }
    return instance;
  }

  public ArrayList<EventType> getEventTypes() {
    return this.eventTypes;
  }

  public boolean loadEventTypeData() {
    Object data = FileManager.loadBinaryDataFromFile(EventTypeS_DATA_FILE);
    if (data != null) {
      try {
        eventTypes = (ArrayList<EventType>) data;
        Logger.getInstance().write("EventType data loaded successfully.");
        return true;
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading eventType data: " + e.toString());
        return false;
      }
    }
    return false;
  }

  public void saveEventTypeData() {
    FileManager.saveBinaryDataToFile(EventTypeS_DATA_FILE, eventTypes);
  }

  public void addEventType(EventType eventType) {
    eventTypes.add(eventType);
    saveEventTypeData();
  }

  public boolean deleteEventType(EventType eventType) {
    boolean result = eventTypes.remove(eventType);
    saveEventTypeData();
    EventManager.getInstance().clearEventsOfType(eventType);
    return result;
  }
}
