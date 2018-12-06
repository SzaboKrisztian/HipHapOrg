package org.hiphap;

import java.io.Serializable;

public abstract class Entity implements Serializable, CsvPrintable {
  private String email;
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

  public abstract String toString();
}