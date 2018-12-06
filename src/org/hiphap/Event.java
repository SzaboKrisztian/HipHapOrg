package org.hiphap;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class Event implements Serializable {
  private String name;
  private LocalDateTime start;
  private LocalDateTime finish;
  private String location;
  private EventType eventType;
  private LinkedHashMap<Entity, Boolean> organizers;
  private LinkedHashMap<Entity, Boolean> participants;
  private ArrayList<EventResource> eventResources;
  private LinkedHashMap<Employee, Double> staff;
  private User hipHapOrganizer;
  public static final DateTimeFormatter DT_FORMAT = new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd[ HH][:mm][:ss]")
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
      .toFormatter();


  public Event(String name) {
    this.setName(name);
    organizers = new LinkedHashMap<>();
    participants = new LinkedHashMap<>();
    eventResources = new ArrayList<>();
    staff = new LinkedHashMap<>();
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
  
  public void addOrganizer(Entity entity, boolean subscribeForNotifications) {
    this.organizers.put(entity, subscribeForNotifications);
  }
  
  public boolean deleteOrganizer(Entity entity) {
    return this.organizers.remove(entity);
  }

  public ArrayList<Entity> getOrganizers() {
    return new ArrayList<>(this.organizers.keySet());
  }

  public Set<Map.Entry<Entity, Boolean>> getOrganizersAsEntrySet() {
    return this.organizers.entrySet();
  }

  public void addParticipant(Entity entity, boolean subscribeForNotifications) {
    this.participants.put(entity, subscribeForNotifications);
  }

  public boolean deleteParticipant(Entity entity) {
    return this.participants.remove(entity);
  }

  public ArrayList<Entity> getParticipants() {
    return new ArrayList<>(this.participants.keySet());
  }

  public Set<Map.Entry<Entity, Boolean>> getParticipantsAsEntrySet() {
    return this.participants.entrySet();
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
        if (resource.getName().toLowerCase().contains(name.toLowerCase())) {
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

  public void addStaff(Employee employee, Double numHours) {
    this.staff.put(employee, numHours);
  }

  public boolean deleteStaff(Employee employee) {
    Double value = this.staff.remove(employee);
    return value != null;
  }

  public ArrayList<Employee> getStaff() {
    return new ArrayList<>(this.staff.keySet());
  }

  public Double getHours(Employee employee) {
    return this.staff.get(employee);
  }

  public void setHours(Employee employee, double numHours) {
    this.staff.put(employee, numHours);
  }

  public void setHipHapOrganizer(User hipHapOrganizer) {
    this.hipHapOrganizer = hipHapOrganizer;
  }

  public User getHipHapOrganizer() {
    return this.hipHapOrganizer;
  }


}