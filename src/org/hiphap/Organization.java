package org.hiphap;

public class Organization implements Entity {
  private String name;

  public Organization(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}