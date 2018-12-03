package org.hiphap;

import java.io.File;
import java.util.ArrayList;

public class PersonManager {
  private static PersonManager instance;
  private ArrayList<Person> persons = new ArrayList<>();
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

  public ArrayList<Person> searchByName(String name) {
    ArrayList<Person> result = new ArrayList<>();

    for (Person person: persons) {
      if (person.getFirstName().toLowerCase().contains(name)
          || person.getLastName().toLowerCase().contains(name)) {
        result.add(person);
      }
    }

    return result;
  }

  public ArrayList<Person> searchByPhone(String phoneNo) {
    ArrayList<Person> result = new ArrayList<>();

    for (Person person: persons) {
      if (person.getPhone().toLowerCase().contains(phoneNo)) {
        result.add(person);
      }
    }

    return result;
  }

  public ArrayList<Person> searchByEmail(String email) {
    ArrayList<Person> result = new ArrayList<>();

    for (Person person: persons) {
      if (person.getEmail().toLowerCase().contains(email)) {
        result.add(person);
      }
    }

    return result;
  }

  public void addPerson(Person person) {
    persons.add(person);
    savePersonData();
  }

  public boolean deletePerson(Person person) {
    boolean result = persons.remove(person);
    savePersonData();
    return result;
  }

  public boolean loadPersonData() {
    Object data = FileManager.loadBinaryDataFromFile(PERSONS_DATA_FILE);
    if (data != null) {
      try {
        persons = (ArrayList<Person>) data;
        Logger.getInstance().write("Person data loaded successfully.");
        return true;
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading person data: " + e.toString());
        return false;
      }
    }
    return false;
  }

  public void savePersonData() {
    FileManager.saveBinaryDataToFile(PERSONS_DATA_FILE, persons);
  }
}
