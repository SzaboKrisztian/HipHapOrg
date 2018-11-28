package org.hiphap;

public class EventResources {
  private String name;
  private Entity provider;
  private double cost;

  public EventResources(String name) {
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

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }
}