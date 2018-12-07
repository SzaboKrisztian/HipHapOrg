package org.hiphap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This class uses the singleton pattern to allow easy {@link User} authentication,
 * and management from any point within the system.
 */
public class UserManager {
  private static UserManager instance;
  /**
   * An {@link ArrayList} that holds all {@link User} objects within the system.
   */
  private List<User> users = new ArrayList<>();
  /**
   * A {@link User} object that represents the currently logged in user. The main
   * method takes care that only the login screen can be displayed if this is null.
   */
  private User currentUser;
  /**
   * A {@link String} representation of the filename to and from which this class
   * loads and saves the data.
   */
  private final String USERS_DATA_FILE = "data".concat(File.separator).concat("users.dat");

  /**
   * If the system loses its {@link User} data, it creates a new user called
   * "admin", with the password set to "123", and administrator privileges, thus
   * preventing being locked out of the system.
   */
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

  /**
   * Gets an {@link ArrayList} of all the usernames registered on the system
   *
   * @return an {@link ArrayList} of all usernames
   */
  public ArrayList<String> getUsernames() {
    ArrayList<String> result = new ArrayList<>();
    for (User user: users) {
      result.add(user.getUsername());
    }
    return result;
  }

  /**
   * Adds a new {@link User} object to the system.
   *
   * @param user the {@link User} object to be added
   */
  public void addUser(User user) {
    users.add(user);
  }

  /**
   * Gets the currently logged in {@link User}
   *
   * @return the currently logged in {@link User}. Can only be null on the login screen
   */
  public User getCurrentUser() {
    return this.currentUser;
  }

  /**
   * Checks a username and password combination against the ones stored in the system.
   * If a match is found, that {@link User} is set as the currently logged in user.
   *
   * @param username a {@link String} username to search for
   * @param password a {@link String} password to authenticate with
   * @return true if login was successful
   */
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

  /**
   * Check whether the username is already registered on the system
   *
   * @param username a {@link String} representation of the username
   * @return true if the username is already registered on the system
   */
  public boolean isUsernameTaken(String username) {
    for (User user: users) {
      if (username.equals(user.getUsername())) {
        return true;
      }
    }
    return false;
  }

  /**
   * Removes a {@link User} from the system by searching for a matching username
   *
   * @param username a {@link String} representation of the username to be removed
   * @return true if the {@link User} was successfully removed.
   */
  public boolean deleteUser(String username) {
    for (User user: users) {
      if (user.getUsername().equals(username)) {
        return users.remove(user);
      }
    }
    return false;
  }

  /**
   * Check whether any {@link User} is currently logged in
   *
   * @return true if a {@link User} is logged in
   */
  public boolean isAuthenticated() {
    return currentUser != null;
  }

  /**
   * Log the current user out of the system
   */
  public void logout() {
    currentUser = null;
  }

  /**
   * Loads the contents from the data file into the internal {@link ArrayList}.
   *
   * @return true if data was successfully loaded
   */
  @SuppressWarnings("unchecked")
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

  /**
   * Writes the contents of the internal {@link ArrayList} into the data file.
   */
  public void saveUserData() {
    FileManager.saveBinaryDataToFile(USERS_DATA_FILE, users);
  }
}
