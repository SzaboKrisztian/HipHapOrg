package org.hiphap;

import java.io.Serializable;

public class EventResource implements Serializable {
  private String name;
  private Entity provider;
  private Double cost;

  public EventResource(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Entity getProvider() {
    return provider;
  }

  public void setProvider(Entity provider) {
    this.provider = provider;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }
}