package org.hiphap;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
  private static UserManager instance;
  private List<User> users = new ArrayList<>();
  private User currentUser;

  private UserManager() {
    users.add(new User("groot", "123", null, true));
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

  public boolean authenticate(String username, String password) {
    for (User user: users) {
      if (user.getUsername().equals(username)) {
        if (user.authenticate(password)) {
          currentUser = user;
          return true;
        }
      }
    }
    return false;
  }

  public boolean isAuthenticated() {
    return currentUser != null;
  }

  public void logout() {
    currentUser = null;
  }
}
