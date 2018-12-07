package org.hiphap;

import java.io.Serializable;

/**
 * This class represents one category of events in the system. Because of this implementation
 * new event types can be added at runtime, by creating new instances of this class.
 */
public class EventType implements Serializable {
  /**
   * A {@link String} representation of a particular event type's name
   */
  private String name;

  public EventType(String name) {
    this.name = name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public String toString() {
    return getName();
  }
}

   
