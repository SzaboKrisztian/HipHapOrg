package org.hiphap;

import java.io.Serializable;

public class User implements Serializable {
  private String username;
  private String password;
  private boolean admin;

  public User(String username, String password, boolean admin) {
    this.username = username;
    this.password = password;
    this.admin = admin;
  }

  public String getUsername() {
    return this.username;
  }

  public boolean authenticate(String password) {
    return password.equals(this.password);
  }

  public boolean changePassword(String oldPassword, String newPassword, String repeatPassword) {
    if (authenticate(oldPassword)) {
      if (newPassword.equals(repeatPassword)) {
        this.password = newPassword;
        return true;
      }
    }
    return false;
  }

  public boolean isAdmin() {
    return this.admin;
  }
}
