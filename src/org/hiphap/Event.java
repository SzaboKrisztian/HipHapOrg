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

/**
 * This class represents within the system a singular event organized by HipHapOrg
 * on behalf of one or more clients.
 */
public class Event implements Serializable {
  /**
   * A {@link String} representation of this event's name. Should never be null or blank.
   */
  protected String name;
  /**
   * A {@link LocalDateTime} representation of the event's starting date and time. Can be null
   */
  protected LocalDateTime start;
  /**
   * A {@link LocalDateTime} representation of the event's ending date and time. Can be null
   */
  protected LocalDateTime finish;
  /**
   * A {@link String} representation of the location where the event takes place. Usually
   * meant to hold the actual street address. Can be blank.
   */
  protected String location;
  /**
   * A {@link EventType} representation of the category that this event falls under.
   * eg. Wedding, Party, etc. Can be blank.
   */
  protected EventType eventType;
  /**
   * A {@link LinkedHashMap} that holds the event's organizers and associates each of
   * them to a flag representing whether they wish to receive notifications from the system.
   */
  protected LinkedHashMap<Entity, Boolean> organizers;
  /**
   * A {@link LinkedHashMap} that holds the event's participants and associates each of
   * them to a flag representing whether they wish to receive notifications from the system.
   */
  protected LinkedHashMap<Entity, Boolean> participants;
  /**
   * An {@link ArrayList} that holds all the {@link EventResource} objects that represent
   * all the goods and services to be purchased/hired for the event.
   */
  protected ArrayList<EventResource> eventResources;
  /**
   * A {@link LinkedHashMap} that holds all the HipHapOrg {@link Employee}s that have been
   * hired to work at this particular event. Associated with each is a {@link Double}
   * representation of the number of hours they are scheduled to work.
   */
  protected LinkedHashMap<Employee, Double> staff;
  /**
   * The {@link User} that created and maintains this event.
   */
  protected User hipHapOrganizer;
  /**
   * A {@link DateTimeFormatter} object to help with writing to and parsing from a {@link String}
   * representation of any particular {@link LocalDateTime}, to keep formatting consistent.
   */
  public static final DateTimeFormatter DT_FORMAT = new DateTimeFormatterBuilder()
      .appendPattern("yyyy-MM-dd[ HH][:mm][:ss]")
      .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
      .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
      .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
      .toFormatter();


  /**
   * Creates a blank Event object, apart from the name attribute. Also
   * initializes the collection attributes, and sets the creating user.
   *
   * @param name the {@link String} representation of this event's name
   */
  public Event(String name) {
    this.setName(name);
    organizers = new LinkedHashMap<>();
    participants = new LinkedHashMap<>();
    eventResources = new ArrayList<>();
    staff = new LinkedHashMap<>();
    hipHapOrganizer = UserManager.getInstance().getCurrentUser();
  }

  /**
   * Creates a Event object based on the data contained within another {@link Arrangement} object.
   *
   * @param arrangement the {@link Arrangement} to extract the data from
   */
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

  /**
   * Get a {@link String} representation of the event's start date/time
   *
   * @return the {@link String} representation of the event's start date/time
   */
  public String getStartAsString() {
    return this.start == null ? "N/A" : this.start.format(DT_FORMAT);
  }

  public void setFinish(LocalDateTime finish) {
    this.finish = finish;
  }

  public LocalDateTime getFinish() {
    return this.finish;
  }

  /**
   * Get a {@link String} representation of the event's end date/time
   *
   * @return the {@link String} representation of the event's end date/time
   */
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

  /**
   * Add an {@link Entity} as an organizer of this event.
   *
   * @param entity                    the {@link Entity} to be added to the list
   * @param subscribeForNotifications whether this {@link Entity} would like to subscribe to notifications
   */
  public void addOrganizer(Entity entity, boolean subscribeForNotifications) {
    this.organizers.put(entity, subscribeForNotifications);
  }

  /**
   * Remove an {@link Entity} from the organizer list.
   *
   * @param entity the {@link Entity} to be removed from the list
   */
  public void deleteOrganizer(Entity entity) {
    this.organizers.remove(entity);
  }

  /**
   * Get an {@link ArrayList} of the event's organizers
   *
   * @return the {@link ArrayList} of the event's organizers
   */
  public ArrayList<Entity> getOrganizers() {
    return new ArrayList<>(this.organizers.keySet());
  }

  /**
   * Get a set of key-value pairs of the event's organizers and the flag representing
   * whether they wish to be notified by the system or not.
   *
   * @return a set of key-value pairs of organizers and associated notification flags
   */
  public Set<Map.Entry<Entity, Boolean>> getOrganizersAsEntrySet() {
    return this.organizers.entrySet();
  }

  /**
   * Add an {@link Entity} as a participant of this event.
   *
   * @param entity                    the {@link Entity} to be added to the list
   * @param subscribeForNotifications whether this {@link Entity} would like to subscribe to notifications
   */
  public void addParticipant(Entity entity, boolean subscribeForNotifications) {
    this.participants.put(entity, subscribeForNotifications);
  }

  /**
   * Remove an {@link Entity} from the participants list.
   *
   * @param entity the {@link Entity} to be removed from the list
   */
  public void deleteParticipant(Entity entity) {
    this.participants.remove(entity);
  }

  /**
   * Get an {@link ArrayList} of the event's participants
   *
   * @return the {@link ArrayList} of the event's participants
   */
  public ArrayList<Entity> getParticipants() {
    return new ArrayList<>(this.participants.keySet());
  }

  /**
   * Get a set of key-value pairs of the event's participants and the flag representing
   * whether they wish to be notified by the system or not.
   *
   * @return a set of key-value pairs of participants and associated notification flags
   */
  public Set<Map.Entry<Entity, Boolean>> getParticipantsAsEntrySet() {
    return this.participants.entrySet();
  }

  /**
   * Add an {@link EventResource} to the collection.
   *
   * @param eventResource the {@link EventResource} object to be added
   */
  public void addEventResource(EventResource eventResource) {
    this.eventResources.add(eventResource);
  }

  /**
   * Remove an {@link EventResource} from the collection.
   *
   * @param eventResource the {@link EventResource} object to be removed
   * @return true if the {@link EventResource} was found and removed
   */
  public boolean deleteEventResource(EventResource eventResource) {
    return this.eventResources.remove(eventResource);
  }

  /**
   * Get an {@link ArrayList} of all the {@link EventResource} objects associated with this event.
   *
   * @return the {@link ArrayList} containing the associated {@link EventResource}s
   */
  public ArrayList<EventResource> getEventResources() {
    return this.eventResources;
  }

  /**
   * Get an {@link ArrayList} of the {@link EventResource} objects associated with this event
   * that also contain a {@link String} in their name.
   *
   * @param name the {@link String} to check against in the names
   * @return an {@link ArrayList} of matching {@link EventResource} objects
   */
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

  /**
   * Get the cost as {@link Double} of all the {@link EventResource} objects associated
   * with this event.
   *
   * @return the total cost of all {@link EventResource} objects associated with this event
   */
  public Double getResourcesCost() {
    Double result = 0.0;
    for (EventResource resource : eventResources) {
      if (resource.getCost() != null) {
        result += resource.getCost();
      }
    }
    return result;
  }

  /**
   * Get the fee that HipHapOrg will charge for organizing this event. This is calculated
   * as 5% of the total {@link EventResource} objects cost, with a minimum of 1000 kr.
   *
   * @return a {@link Double} representation of HipHapOrg's organization fee
   */
  public Double getHipHapFee() {
    return getResourcesCost() * 0.05 < 1000.0 ? 1000.0 : getResourcesCost() * 0.05;
  }

  /**
   * Get the total cost of all the HipHapOrg staff hired for this event, based
   * on each {@link Employee}'s hourly rate and the scheduled number of hours.
   *
   * @return the total cost of all staff scheduled to work for this event
   */
  public Double getStaffCost() {
    double result = 0.0;
    for (Employee employee : staff.keySet()) {
      result += employee.getHourlyRate() * getHours(employee);
    }

    return result;
  }

  /**
   * Get a grand total of this event's cost.
   *
   * @return a {@link Double} representation of this event's total cost
   */
  public Double getTotalEventCost() {
    return getResourcesCost() + getHipHapFee() + getStaffCost();
  }

  /**
   * Add an {@link Employee} to the list of staff hired to work at this event.
   *
   * @param employee the {@link Employee} to be added.
   * @param numHours a {@link Double} representation of the number of hours the {@link Employee}
   *                 to be added is scheduled to work at this event
   */
  public void addStaff(Employee employee, Double numHours) {
    this.staff.put(employee, numHours);
  }

  /**
   * Remove an {@link Employee} from the list of staff hired to work at this event.
   *
   * @param employee the {@link Employee} to be removed from the list
   */
  public void deleteStaff(Employee employee) {
    this.staff.remove(employee);
  }

  /**
   * Get an {@link ArrayList} of the staff hired to work at this event.
   *
   * @return the {@link ArrayList} of the staff hired to work at this event
   */
  public ArrayList<Employee> getStaff() {
    return new ArrayList<>(this.staff.keySet());
  }

  /**
   * Get the number of hours a particular {@link Employee} is scheduled to work at this event.
   *
   * @param employee the {@link Employee} whose scheduled number of hours are to be returned
   * @return a {@link Double} representation of the number of hours the {@link Employee} is
   * scheduled to work. Can return null if {@link Employee} object is not found within the
   * staff collection
   */
  public Double getHours(Employee employee) {
    return this.staff.get(employee);
  }

  /**
   * Set the number of hours a particular {@link Employee} is scheduled to work at this event.
   * If the {@link Employee} is not found, a new entry is added to the collection.
   *
   * @param employee the {@link Employee} whose scheduled working hours are to be set
   * @param numHours the number of hours the {@link Employee} is scheduled to work
   */
  public void setHours(Employee employee, double numHours) {
    this.staff.put(employee, numHours);
  }

  /**
   * Check whether a particular employee is scheduled to work at this event.
   *
   * @param employee the {@link Employee} to search for
   * @return true if the {@link Employee} has been found in the collection
   */
  public boolean isWorking(Employee employee) {
    return this.staff.containsKey(employee);
  }

  public void setHipHapOrganizer(User hipHapOrganizer) {
    this.hipHapOrganizer = hipHapOrganizer;
  }


  public User getHipHapOrganizer() {
    return this.hipHapOrganizer;
  }

  /**
   * Checks whether this event's schedule overlaps with another event's schedule. It checks
   * the actual times only if both starting and ending times are set for both events. If either
   * event is missing the finishing time, it only checks if the starting dates are the same.
   * Furthermore, if either event is also missing a start date, it always returns null.
   *
   * @param event the event against which to check starting and ending dates/times
   * @return true if there is an overlap in the events' schedules
   */
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