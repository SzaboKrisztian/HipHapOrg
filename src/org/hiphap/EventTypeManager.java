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

  @SuppressWarnings("unchecked")
  public void loadEventTypeData() {
    Object data = FileManager.loadBinaryDataFromFile(EventTypeS_DATA_FILE);
    if (data != null) {
      try {
        eventTypes = (ArrayList<EventType>) data;
        Logger.getInstance().write("EventType data loaded successfully.");
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading eventType data: " + e.toString());
      }
    }
  }

  public void saveEventTypeData() {
    FileManager.saveBinaryDataToFile(EventTypeS_DATA_FILE, eventTypes);
  }

  public void addEventType(EventType eventType) {
    eventTypes.add(eventType);
    saveEventTypeData();
  }

  public void deleteEventType(EventType eventType) {
    eventTypes.remove(eventType);
    saveEventTypeData();
    EventManager.getInstance().clearEventsOfType(eventType);
  }
}
