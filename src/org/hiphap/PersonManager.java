package org.hiphap;

import java.io.File;
import java.util.ArrayList;

/**
 * This class uses the singleton pattern to allow easy access to the {@link Person} data
 * from any point within the system.
 */
public class PersonManager {
  private static PersonManager instance;
  /**
   * An {@link ArrayList} that holds all {@link Person} objects the system keeps track of.
   */
  private ArrayList<Person> persons = new ArrayList<>();
  /**
   * A {@link String} representation of the filename to and from which this class
   * loads and saves the data.
   */
  private final String PERSONS_DATA_FILE = "data".concat(File.separator).concat("persons.dat");

  private PersonManager() {
    loadPersonData();
  }

  public static PersonManager getInstance() {
    if (instance == null) {
      instance = new PersonManager();
    }
    return instance;
  }

  /**
   * Get a collection of {@link Person} objects whose names contain a {@link String} sequence.
   *
   * @param name the {@link String} sequence to search for
   * @return the collection of {@link Person} objects that match the query
   */
  public ArrayList<Person> searchByName(String name) {
    ArrayList<Person> result = new ArrayList<>();

    for (Person person : persons) {
      if (person.getFirstName().toLowerCase().contains(name.toLowerCase())
          || person.getLastName().toLowerCase().contains(name.toLowerCase())
          || person.getMiddleName().toLowerCase().contains(name.toLowerCase())) {
        result.add(person);
      }
    }

    return result;
  }

  /**
   * Get a collection of {@link Person} objects whose phone numbers contain
   * a {@link String} sequence.
   *
   * @param phoneNo the {@link String} sequence to search for
   * @return the {@link ArrayList} of {@link Person} objects that match the query
   */
  public ArrayList<Person> searchByPhone(String phoneNo) {
    ArrayList<Person> result = new ArrayList<>();

    for (Person person : persons) {
      if (person.getPhone().toLowerCase().contains(phoneNo.toLowerCase())) {
        result.add(person);
      }
    }

    return result;
  }

  /**
   * Get a collection of {@link Person} objects whose email
   * addresses contain a {@link String} sequence.
   *
   * @param email the {@link String} sequence to search for
   * @return the {@link ArrayList} of {@link Person} objects that match the query
   */
  public ArrayList<Person> searchByEmail(String email) {
    ArrayList<Person> result = new ArrayList<>();

    for (Person person : persons) {
      if (person.getEmail().toLowerCase().contains(email.toLowerCase())) {
        result.add(person);
      }
    }

    return result;
  }

  public ArrayList<Person> getPersons() {
    return this.persons;
  }

  /**
   * Adds a {@link Person} object into the collection and saves the data to file.
   *
   * @param person the {@link Person} object to be added
   */
  public void addPerson(Person person) {
    persons.add(person);
    savePersonData();
  }

  /**
   * Removes an {@link Person} object from the collection and saves the data to file.
   *
   * @param person the {@link Person} to be removed from the collection
   */
  public void deletePerson(Person person) {
    persons.remove(person);
    savePersonData();
  }

  /**
   * Loads the contents from the data file into the internal {@link ArrayList}.
   */
  @SuppressWarnings("unchecked")
  public void loadPersonData() {
    Object data = FileManager.loadBinaryDataFromFile(PERSONS_DATA_FILE);
    if (data != null) {
      try {
        persons = (ArrayList<Person>) data;
        Logger.getInstance().write("Person data loaded successfully.");
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading person data: " + e.toString());
      }
    }
  }

  /**
   * Writes the contents of the internal {@link ArrayList} into the data file.
   */
  public void savePersonData() {
    FileManager.saveBinaryDataToFile(PERSONS_DATA_FILE, persons);
  }
}
