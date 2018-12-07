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
  protected String name;
  protected LocalDateTime start;
  protected LocalDateTime finish;
  protected String location;
  protected EventType eventType;
  protected LinkedHashMap<Entity, Boolean> organizers;
  protected LinkedHashMap<Entity, Boolean> participants;
  protected ArrayList<EventResource> eventResources;
  protected LinkedHashMap<Employee, Double> staff;
  protected User hipHapOrganizer;
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
    hipHapOrganizer = UserManager.getInstance().getCurrentUser();
  }

  public Event(Arrangement arrangement) {
    this.name = arrangement.name;
    this.start = arrangement.start;
    this.finish = arrangement.finish;
    this.location = arrangement.location;
    this.eventType = arrangement.eventType;
    this.organizers = arrangement.organizers;
    this.participants = arrangement.participants;
    this.eventResources = arrangement.eventResources;
    this.staff = arrangement.staff;
    this.hipHapOrganizer = arrangement.hipHapOrganizer;
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

  public void deleteParticipant(Entity entity) {
    this.participants.remove(entity);
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
      if (resource.getCost() != null) {
        result += resource.getCost();
      }
    }
    return result;
  }

  public Double getHipHapFee() {
    return getResourcesCost() * 0.05 < 1000.0 ? 1000.0 : getResourcesCost() * 0.05;
  }

  public Double getStaffCost() {
    double result = 0.0;
    for (Employee employee: staff.keySet()) {
      result += employee.getHourlyRate() * getHours(employee);
    }

    return result;
  }

  public Double getTotalEventCost() {
    return getResourcesCost() + getHipHapFee() + getStaffCost();
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

  public boolean isWorking(Employee employee) {
    return this.staff.containsKey(employee);
  }

  public void setHipHapOrganizer(User hipHapOrganizer) {
    this.hipHapOrganizer = hipHapOrganizer;
  }

  public User getHipHapOrganizer() {
    return this.hipHapOrganizer;
  }

  public boolean isScheduleOverlap(Event event) {
    if (this.start != null && event.start != null) {
      if (this.finish != null && event.finish != null) {
        return this.start.isAfter(event.start) && this.start.isBefore(event.finish) ||
            this.finish.isAfter(event.start) && this.finish.isBefore(event.finish);
      } else {
        return this.start.toLocalDate().equals(event.start.toLocalDate());
      }
    }
    return false;
  }
}