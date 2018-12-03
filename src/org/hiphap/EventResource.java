package org.hiphap;

import java.io.Serializable;

public class EventResource implements Serializable {
  private String name;
  private Double cost;

  public EventResource(String name) {
    this.name = name;
  }

  public EventResource(String name, Double cost) {
    this(name);
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

  public void setCost(double cost) {
    this.cost = cost;
  }
}