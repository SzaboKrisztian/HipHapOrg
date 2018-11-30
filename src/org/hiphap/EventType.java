package org.hiphap;

import java.io.Serializable;

public class EventType implements Serializable {
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

   
