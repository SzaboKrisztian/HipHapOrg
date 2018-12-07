package org.hiphap;

import java.io.Serializable;

/**
 * This class represents a good or a service that's purchased or hired for an event
 */
public class EventResource implements Serializable {
  /**
   * A {@link String} representation of this resource's name
   */
  private String name;
  /**
   * A {@link Double} representation of the associated cost. Can be null
   */
  private Double cost;

  public EventResource(String name) {
    this.name = name;
  }

  public EventResource(String name, Double cost) {
    this.name = name;
    this.cost = cost;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getCost() {
    return cost;
  }
}