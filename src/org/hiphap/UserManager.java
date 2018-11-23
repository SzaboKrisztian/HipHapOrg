package org.hiphap;

public class UserManager {
  private static UserManager instance;
  private User currentUser;

  private UserManager() {
    currentUser = new User();
  }

  public static UserManager getInstance() {
    if (instance == null) {
      instance = new UserManager();
    }
    return instance;
  }

  public User getCurrentUser() {
    return this.currentUser;
  }
}
