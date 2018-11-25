package org.hiphap;

public class User {
  private String username;
  private String password;
  private Employee userDetails;

  public User(String username, String password, Employee userDetails) {
    this.username = username;
    this.password = password;
    this.userDetails = userDetails;
  }

  public String getUsername() {
    return this.username;
  }

  public Employee getUserDetails() {
    return this.userDetails;
  }
}
