package org.hiphap;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * This class uses the singleton pattern to allow easy access to the {@link Event} data
 * from any point within the system.
 */
public class EventManager {
  private static EventManager instance;
  /**
   * An {@link ArrayList} that holds all {@link Event} objects the system keeps track of.
   */
  private ArrayList<Event> events = new ArrayList<>();
  /**
   * A {@link String} representation of the filename to and from which this class
   * loads and saves the data.
   */
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

  /**
   * Loads the contents from the data file into the internal {@link ArrayList}.
   */
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

  /**
   * Writes the contents of the internal {@link ArrayList} into the data file.
   */
  public void saveEventData() {
    FileManager.saveBinaryDataToFile(EVENTS_DATA_FILE, events);
  }

  /**
   * Adds an {@link Event} object into the collection and saves the data to file.
   *
   * @param event the {@link Event} object to be added
   */
  public void addEvent(Event event) {
    events.add(event);
    saveEventData();
  }

  /**
   * Removes an {@link Event} object from the collection and saves the data to file.
   *
   * @param event the {@link Event} to be removed from the collection
   */
  public void deleteEvent(Event event) {
    events.remove(event);
    saveEventData();
  }

  /**
   * Sets the {@link EventType} attribute to null for all {@link Event} objects within the
   * system that match the parameter.
   *
   * @param type the {@link EventType} to check against
   */
  public void clearEventsOfType(EventType type) {
    for (Event event : events) {
      if (event instanceof Arrangement) {
        Arrangement arrangement = (Arrangement) event;
        for (Event event1 : arrangement.getSubEvents()) {
          if (event1.getEventType() == type) {
            event1.setEventType(null);
          }
        }
      }
      if (event.getEventType() == type) {
        event.setEventType(null);
      }
    }
  }

  /**
   * Get a collection of {@link Event} objects whose names contain a {@link String} sequence.
   * <p>
   * NOTE: If the current {@link User} doesn't have administrator privileges, only those
   * {@link Event} objects are returned that are organized by the current {@link User}
   *
   * @param name the {@link String} sequence to search for
   * @return the collection of {@link Event} objects that match the query
   */
  public ArrayList<Event> searchByName(String name) {
    ArrayList<Event> result = new ArrayList<>();

    if (UserManager.getInstance().getCurrentUser().isAdmin()) {
      for (Event event : events) {
        if (event.getName().toLowerCase().contains(name.toLowerCase())) {
          result.add(event);
        }
      }
    } else {
      for (Event event : events) {
        if (event.getName().toLowerCase().contains(name.toLowerCase()) &&
            event.getHipHapOrganizer() == UserManager.getInstance().getCurrentUser()) {
          result.add(event);
        }
      }
    }

    return result;
  }

  /**
   * Get a collection of {@link Event} objects whose locations contain a {@link String} sequence.
   * <p>
   * NOTE: If the current {@link User} doesn't have administrator privileges, only those
   * {@link Event} objects are returned that are organized by the current {@link User}
   *
   * @param location the {@link String} sequence to search for
   * @return the collection of {@link Event} objects that match the query
   */
  public ArrayList<Event> searchByLocation(String location) {
    ArrayList<Event> result = new ArrayList<>();

    if (UserManager.getInstance().getCurrentUser().isAdmin()) {
      for (Event event : events) {
        if (event.getLocation().toLowerCase().contains(location.toLowerCase())) {
          result.add(event);
        }
      }
    } else {
      for (Event event : events) {
        if (event.getLocation().toLowerCase().contains(location.toLowerCase()) &&
            event.getHipHapOrganizer() == UserManager.getInstance().getCurrentUser()) {
          result.add(event);
        }
      }
    }

    return result;
  }

  /**
   * Get a collection of {@link Event} objects that are ongoing at a certain point in time.
   * <p>
   * NOTE: If the current {@link User} doesn't have administrator privileges, only those
   * {@link Event} objects are returned that are organized by the current {@link User}
   *
   * @param time the {@link LocalDateTime} moment to check against the event's schedule
   * @return the collection of {@link Event} objects that fulfil the condition
   */
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

  /**
   * Get a collection of {@link Event} objects that have at least one organizer whose
   * name contains a particular {@link String}.
   * <p>
   * NOTE: If the current {@link User} doesn't have administrator privileges, only those
   * {@link Event} objects are returned that are organized by the current {@link User}
   *
   * @param string the {@link String} to search for in the organizer's names
   * @return the collection of {@link Event} objects that fulfil the condition
   */
  public ArrayList<Event> searchByOrganizer(String string) {
    ArrayList<Event> result = new ArrayList<>();

    if (UserManager.getInstance().getCurrentUser().isAdmin()) {
      for (Event event : events) {
        for (Entity entity : event.getOrganizers()) {
          if (entity.containsInName(string.toLowerCase())) {
            result.add(event);
            break;
          }
        }
      }
    } else {
      for (Event event : events) {
        if (event.getHipHapOrganizer() == UserManager.getInstance().getCurrentUser()) {
          for (Entity entity : event.getOrganizers()) {
            if (entity.containsInName(string.toLowerCase())) {
              result.add(event);
              break;
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * Check whether a point in time falls within an {@link Event} object's chronological boundaries.
   *
   * @param time  the time point as a {@link LocalDateTime} to check for
   * @param event the {@link Event} to check against
   * @return true if the time point is within the {@link Event} object's chronological boundaries
   */
  private boolean isTimeInsideEvent(LocalDateTime time, Event event) {
    return (event.getStart() != null && (event.getStart().toLocalDate().
        isBefore(time.toLocalDate()) || event.getStart().toLocalDate().
        isEqual(time.toLocalDate()))) && (event.getFinish() != null &&
        (event.getFinish().toLocalDate().isAfter(time.toLocalDate()) ||
            event.getFinish().toLocalDate().isEqual(time.toLocalDate())));
  }

  /**
   * Check whether a particular event is top-level, ie. is not contained within
   * an {@link Arrangement}.
   *
   * @param event the {@link Event} to search for.
   * @return true if the {@link Event} is top-level
   */
  public boolean isTopLevelEvent(Event event) {
    return this.events.contains(event);
  }
}
