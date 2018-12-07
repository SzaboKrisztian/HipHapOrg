package org.hiphap;

/**
 * This class represents a person that the system keeps track of, making it easier for the
 * associated data to be reused under several different events. It inherits from the abstract
 * {@link Entity} base class, adding further attributes and behaviour.
 */
public class Person extends Entity {
  /**
   * A {@link String} representation of this person's first name
   */
  private String firstName;
  /**
   * A {@link String} representation of this person's middle name
   */
  private String middleName;
  /**
   * A {@link String} representation of this person's last name
   */
  private String lastName;

  public Person(String firstName) {
    super();
    this.firstName = firstName;
    this.middleName = "";
    this.lastName = "";
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Get a human-friendly {@link String} representation of this person's name, in abbreviated form
   *
   * @return the {@link String} representation of the person's name
   */
  public String toString() {
    String result = this.firstName;
    if (!this.lastName.equals("")) {
      result = this.lastName + ", " + result;
    }
    if (!this.middleName.equals("")) {
      result += " " + this.middleName.substring(0, 1) + ".";
    }
    return result;
  }

  /**
   * Implementation of abstract method from the superclass. Checks whether
   * this person contains a {@link String} in its name.
   *
   * @param sequence the {@link String} to look for
   * @return true if the {@link String} is found in the person's name
   */
  public boolean containsInName(String sequence) {
    return this.firstName.toLowerCase().contains(sequence.toLowerCase()) ||
        this.lastName.toLowerCase().contains(sequence.toLowerCase()) ||
        this.middleName.toLowerCase().contains(sequence.toLowerCase());
  }

  /**
   * Get a full-length {@link String} representaion of the person's name
   *
   * @return the {@link String} representation of the person's full name
   */
  public String toFullString() {
    String result = this.firstName;
    if (!this.middleName.equals("")) {
      result = result.concat(" ").concat(middleName);
    }
    if (!this.lastName.equals("")) {
      result = result.concat(" ").concat(lastName);
    }
    return result;
  }

  /**
   * Implementation of abstract method from the superclass. Creates a CSV format {@link String}
   * representation of the attribute data.
   *
   * @return a {@link String} representation of CSV formatted attribute data
   */
  public String getCsvString() {
    return getFirstName().concat("|").concat(getLastName()).concat("|").concat(getMiddleName()).
        concat("|").concat(getEmail()).concat("|").concat(getPhone());
  }
}
