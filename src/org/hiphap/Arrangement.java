package org.hiphap;

import java.util.ArrayList;


/**
 * This class inherits the {@link Event} class, and adds the ability to hold other
 * events within itself. It represents the concepts of arrangements of events.
 */
public class Arrangement extends Event {
  private ArrayList<Event> subEvents = new ArrayList<>();

  /**
   * Constructor that creates a new Arrangement object
   *
   * @param name sets the name of the arrangement. Cannot be blank.
   */
  public Arrangement(String name) {
    super(name);
  }

  /**
   * Constructor that creates an arrangement based on another existing event.
   *
   * @param event sets all the attributes to the event's attributes.
   */
  public Arrangement(Event event) {
    super(event.name);
    this.start = event.start;
    this.finish = event.finish;
    this.location = event.location;
    this.eventType = event.eventType;
    this.organizers = event.organizers;
    this.participants = event.participants;
    this.eventResources = event.eventResources;
    this.staff = event.staff;
    this.hipHapOrganizer = event.hipHapOrganizer;
  }

  /**
   * Add an {@link Event} to the arrangement's collection of sub-events.
   *
   * @param event the {@link Event} to be added
   */
  public void addSubEvent(Event event) {
    this.subEvents.add(event);
  }

  /**
   * Get a list of the arrangement's sub-events.
   *
   * @return the collection of sub-events
   */
  public ArrayList<Event> getSubEvents() {
    return this.subEvents;
  }

  /**
   * Deletes an {@link Event} from the arrangement's sub-events collection.
   *
   * @param event the {@link Event} to be removed
   */
  public void removeEvent(Event event) {
    this.subEvents.remove(event);
  }
}
