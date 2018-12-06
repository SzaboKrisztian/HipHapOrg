package org.hiphap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
  private static UserManager instance;
  private List<User> users = new ArrayList<>();
  private User currentUser;
  private final String USERS_DATA_FILE = "data".concat(File.separator).concat("users.dat");

  private UserManager() {
    if (!loadUserData()) {
      users.add(new User("admin", "123", true));
    }
  }

  public static UserManager getInstance() {
    if (instance == null) {
      instance = new UserManager();
    }
    return instance;
  }

  public ArrayList<String> getUsernames() {
    ArrayList<String> result = new ArrayList<>();
    for (User user: users) {
      result.add(user.getUsername());
    }
    return result;
  }

  public void addUser(User user) {
    users.add(user);
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

  public boolean isUsernameTaken(String username) {
    for (User user: users) {
      if (username.equals(user.getUsername())) {
        return true;
      }
    }
    return false;
  }

  public boolean deleteUser(String username) {
    for (User user: users) {
      if (user.getUsername().equals(username)) {
        return users.remove(user);
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

  public boolean loadUserData() {
    Object data = FileManager.loadBinaryDataFromFile(USERS_DATA_FILE);
    if (data != null) {
      try {
        users = (ArrayList<User>) data;
        Logger.getInstance().write("User data loaded successfully.");
        return true;
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading user data: " + e.toString());
        return false;
      }
    }
    return false;
  }

  public void saveUserData() {
    FileManager.saveBinaryDataToFile(USERS_DATA_FILE, users);
  }

  public ArrayList<User> searchByName(String name) {
    ArrayList<User> result = new ArrayList<>();

    for (User user: users) {
      if (user.getUsername().toLowerCase().contains(name)) {
        result.add(user);
      }
    }

    return result;
  }
}
