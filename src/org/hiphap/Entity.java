package org.hiphap;

import java.io.Serializable;

/**
 * Abstract base class for {@link Person} and {@link Organization} to hold common
 * attributes and behaviour, and allow for polymorphic handling of the subclasses.
 */
public abstract class Entity implements Serializable, CsvPrintable {
  /**
   * The {@link String} representation of this entity's email address
   */
  private String email;
  /**
   * The {@link String} representation of this entity's phone number.
   * <p>
   * This data type was chosen to allow phone numbers to be entered with spaces and
   * also to allow special characters such as +, and other alphabetical characters that,
   * for example, some US companies use to make their phone number easier to remember.
   */
  private String phone;

  public Entity() {
    this.email = "";
    this.phone = "";
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  /**
   * Get a short human-readable {@link String} representation of the object.
   * <p>
   * The behaviour is meant to be implemented by each particular subclass.
   *
   * @return the {@link String} representation of the object
   */
  public abstract String toString();

  /**
   * Check whether a particular object contains a {@link String} in its name.
   * <p>
   * The Concrete behaviour is meant to be implemented by each particular subclass.
   *
   * @param sequence the {@link String} to look for
   * @return true if the {@link String} is found in the name
   */
  public abstract boolean containsInName(String sequence);
}