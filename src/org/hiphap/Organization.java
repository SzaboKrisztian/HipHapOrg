package org.hiphap;

/**
 * This class represents an organization that the system keeps track of, making it
 * easier for it to be reused under several different events. It inherits from the abstract
 * {@link Entity} base class, adding further attributes and behaviour.
 */
public class Organization extends Entity {
  /**
   * A {@link String} representation of this organization's name
   */
  private String name;
  /**
   * A {@link String} representation of this organization's street address
   */
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

  /**
   * Implementation of abstract method from the superclass. Checks whether this
   * organization contains a {@link String} in its name.
   *
   * @param sequence the {@link String} to look for
   * @return true if the {@link String} is found in the organization's name
   */
  public boolean containsInName(String sequence) {
    return this.name.toLowerCase().contains(sequence.toLowerCase());
  }

  /**
   * Implementation of abstract method from the superclass. Creates a CSV format {@link String}
   * representation of the attribute data.
   *
   * @return a {@link String} representation of CSV formatted attribute data
   */
  public String getCsvString() {
    return getName().concat("|").concat(getAddress()).concat("|").concat(getEmail()).
        concat("|").concat(getPhone());
  }
}
