package org.hiphap;

import java.io.File;
import java.util.ArrayList;

/**
 * This class uses the singleton pattern to allow easy access to the {@link EventType} data
 * from any point within the system.
 */
public class EventTypeManager {
  private static EventTypeManager instance;
  /**
   * An {@link ArrayList} that holds all {@link EventType} objects the system keeps track of.
   */
  private ArrayList<EventType> eventTypes = new ArrayList<>();
  /**
   * A {@link String} representation of the filename to and from which this class
   * loads and saves the data.
   */
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

  /**
   * Loads the contents from the data file into the internal {@link ArrayList}.
   */
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

  /**
   * Writes the contents of the internal {@link ArrayList} into the data file.
   */
  public void saveEventTypeData() {
    FileManager.saveBinaryDataToFile(EventTypeS_DATA_FILE, eventTypes);
  }

  /**
   * Adds an {@link EventType} object into the collection and saves the data to file.
   *
   * @param eventType the {@link EventType} object to be added
   */
  public void addEventType(EventType eventType) {
    eventTypes.add(eventType);
    saveEventTypeData();
  }

  /**
   * Removes an {@link EventType} object from the collection and saves the data to file.
   *
   * @param eventType the {@link EventType} to be removed from the collection
   */
  public void deleteEventType(EventType eventType) {
    eventTypes.remove(eventType);
    saveEventTypeData();
    EventManager.getInstance().clearEventsOfType(eventType);
  }
}
