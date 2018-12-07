package org.hiphap;

import java.io.Serializable;

/**
 * A class to represent a username and password combination used to authenticate on the
 * system. Also holds a flag whether the user has administrator privileges.
 */
public class User implements Serializable {
  /**
   * A {@link String} representation of the username
   */
  private String username;
  /**
   * A {@link String} representation of the password
   */
  private String password;
  /**
   * A boolean value describing whether the user has administrator privileges
   */
  private boolean admin;

  /**
   * Create a new user with the required data
   *
   * @param username A {@link String} representation of the username
   * @param password A {@link String} representation of the password
   * @param admin    A boolean value describing whether the user has
   *                 administrator privileges
   */
  public User(String username, String password, boolean admin) {
    this.username = username;
    this.password = password;
    this.admin = admin;
  }

  public String getUsername() {
    return this.username;
  }

  /**
   * Checks whether the {@link String} argument passed matches this user's stored password.
   *
   * @param password the {@link String} password to check against
   * @return true if the password matches the supplied argument
   */
  public boolean authenticate(String password) {
    return password.equals(this.password);
  }

  /**
   * Change the user's password if the first parameter matches the stored password, and
   * if the second and third parameters match each other.
   *
   * @param oldPassword    the {@link String} to check against currently set password
   * @param newPassword    the new password to change to
   * @param repeatPassword the password confirmation to guard against typos
   * @return true if password was successfully changed
   */
  public boolean changePassword(String oldPassword, String newPassword, String repeatPassword) {
    if (authenticate(oldPassword)) {
      if (newPassword.equals(repeatPassword)) {
        this.password = newPassword;
        return true;
      }
    }
    return false;
  }

  /**
   * Checks whether the user has administrator privileges
   *
   * @return true if the user has administrator privileges
   */
  public boolean isAdmin() {
    return this.admin;
  }
}
