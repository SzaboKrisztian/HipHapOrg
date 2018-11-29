package org.hiphap;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.List;


public class Event implements Serializable {
  private String name;
  private LocalDateTime start;
  private LocalDateTime finish;
  private String location;
  private EventType eventType;
  private List<Entity> organizedFor;
  private List<Entity> attendees;
  private List<EventResources> eventResources;
  private List<Employee> staff;
  private User organizer;
  public static final DateTimeFormatter DT_FORMAT = new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd[ HH][:mm][:ss]")
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
      .toFormatter();


  public Event(String name) {
    this.setName(name);
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
    return this.start.format(DT_FORMAT);
  }

  public void setFinish(LocalDateTime finish) {
    this.finish = finish;
  }

  public LocalDateTime getFinish() {
    return this.finish;
  }

  public String getFinishAsString() {
    return this.finish.format(DT_FORMAT);
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

  public void setOrganizedFor(List<Entity> organizedFor) {
    this.organizedFor = organizedFor;
  }

  public List<Entity> getOrganizedFor() {
    return this.organizedFor;
  }

  public void setAttendees(List<Entity> attendees) {
    this.attendees = attendees;
  }

  public List<Entity> getAttendees() {
    return this.attendees;
  }

  public void setEventResources(List<EventResources> eventResources) {
    this.eventResources = eventResources;
  }

  public List<EventResources> getEventResources() {
    return this.eventResources;
  }

  public void setStaff(List<Employee> staff) {
    this.staff = staff;
  }

  public List<Employee> getStaff() {
    return this.staff;
  }

  public void setOrganizer(User organizer) {
    this.organizer = organizer;
  }

  public User getOrganizer() {
    return this.organizer;
  }


}