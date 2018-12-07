package org.hiphap;

public class Person extends Entity {
  private String firstName;
  private String middleName;
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

  @Override
  public boolean containsInName(String string) {
    return this.firstName.toLowerCase().contains(string.toLowerCase()) ||
        this.lastName.toLowerCase().contains(string.toLowerCase()) ||
        this.middleName.toLowerCase().contains(string.toLowerCase());
  }

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

  public String getCsvString() {
    return getFirstName().concat("|").concat(getLastName()).concat("|").concat(getMiddleName()).
        concat("|").concat(getEmail()).concat("|").concat(getPhone());
  }
}
