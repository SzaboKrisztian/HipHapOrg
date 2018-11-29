package org.hiphap;

public class Person implements Entity {
  private String firstName;
  private String middleName;
  private String lastName;
  private String email;
  private String phone;
  

  public Person(String firstName) {
    this.firstName = firstName;
    this.middleName = "";
    this.lastName = "";
    this.email = "";
    this.phone = "";
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
}
