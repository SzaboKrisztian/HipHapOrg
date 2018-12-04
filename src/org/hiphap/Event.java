package org.hiphap;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class Event implements Serializable {
  private String name;
  private LocalDateTime start;
  private LocalDateTime finish;
  private String location;
  private EventType eventType;
  private ArrayList<Entity> organizedFor;
  private LinkedHashMap<Entity, Boolean> attendees;
  private ArrayList<EventResource> eventResources;
  private ArrayList<Employee> staff;
  private User organizer;
  public static final DateTimeFormatter DT_FORMAT = new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd[ HH][:mm][:ss]")
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
      .toFormatter();


  public Event(String name) {
    this.setName(name);
    organizedFor = new ArrayList<>();
    attendees = new LinkedHashMap<>();
    eventResources = new ArrayList<>();
    staff = new ArrayList<>();
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public LocalDateTime getStart() {
    return this.start;
  }

  public String getStartAsString() {
    return this.start == null ? "N/A" : this.start.format(DT_FORMAT);
  }

  public void setFinish(LocalDateTime finish) {
    this.finish = finish;
  }

  public LocalDateTime getFinish() {
    return this.finish;
  }

  public String getFinishAsString() {
    return this.finish == null ? "N/A" : this.finish.format(DT_FORMAT);
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return this.location;
  }

  public void setEventType(EventType eventType) {
    this.eventType = eventType;
  }

  public EventType getEventType() {
    return this.eventType;
  }
  
  public void addToOrganizedFor(Entity entity) {
    this.organizedFor.add(entity);
  }
  
  public void deleteFromOrganizedFor(Entity entity) {
    this.organizedFor.remove(entity);
  }

  public ArrayList<Entity> getOrganizedFor() {
    return this.organizedFor;
  }

  public void addAttendee(Entity entity, boolean subscribeForNotifications) {
    this.attendees.put(entity, subscribeForNotifications);
  }

  public boolean deleteFromAttendee(Entity entity) {
    return this.attendees.remove(entity);
  }

  public ArrayList<Entity> getAttendees() {
    return new ArrayList<Entity>(this.attendees.keySet());
  }

  public void addEventResource(EventResource eventResource) {
    this.eventResources.add(eventResource);
  }

  public boolean deleteEventResource(EventResource eventResource) {
    return this.eventResources.remove(eventResource);
  }

  public ArrayList<EventResource> getEventResources() {
    return this.eventResources;
  }

  public ArrayList<EventResource> getEventResources(String name) {
    ArrayList<EventResource> result = new ArrayList<>();
    if (eventResources != null) {
      for (EventResource resource : eventResources) {
        if (resource.getName().toLowerCase().contains(name)) {
          result.add(resource);
        }
      }
    }
    return result;
  }

  public Double getResourcesCost() {
    Double result = 0.0;
    for (EventResource resource: eventResources) {
      result += resource.getCost();
    }
    return result;
  }

  public void addStaff(Employee employee) {
    this.staff.add(employee);
  }

  public void deleteStaff(Employee employee) {
    this.staff.remove(employee);
  }

  public ArrayList<Employee> getStaff() {
    return this.staff;
  }

  public void setOrganizer(User organizer) {
    this.organizer = organizer;
  }

  public User getOrganizer() {
    return this.organizer;
  }


}