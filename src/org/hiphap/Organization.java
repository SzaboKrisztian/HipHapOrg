package org.hiphap;

public class Organization extends Entity {
  private String name;
  private String address;

  public Organization(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String toString() {
    return this.name;
  }
}
