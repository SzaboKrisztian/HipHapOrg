package org.hiphap;

import java.util.ArrayList;

public class Arrangement extends Event {
  private ArrayList<Event> subEvents = new ArrayList<>();

  public Arrangement(String name) {
    super(name);
  }

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

  public void addSubEvent(Event event) {
    this.subEvents.add(event);
  }

  public ArrayList<Event> getSubEvents() {
    return this.subEvents;
  }

  public void removeEvent(Event event) {
    this.subEvents.remove(event);
  }
}
