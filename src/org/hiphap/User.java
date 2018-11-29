package org.hiphap;

import java.io.Serializable;

public class User implements Serializable {
  private String username;
  private String password;
  private boolean admin;
  private Employee userDetails;

  public User(String username, String password, Employee userDetails, boolean admin) {
    this.username = username;
    this.password = password;
    this.admin = admin;
    this.userDetails = userDetails;
  }

  public String getUsername() {
    return this.username;
  }

  public Employee getUserDetails() {
    return this.userDetails;
  }

  public boolean authenticate(String password) {
    return password.equals(this.password);
  }

  public boolean isAdmin() {
    return this.admin;
  }
}
