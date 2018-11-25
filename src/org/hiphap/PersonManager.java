package org.hiphap;

import java.util.ArrayList;

public class PersonManager {
  private static PersonManager instance;
  private ArrayList<Person> persons = new ArrayList<>();

  private PersonManager() {
    persons.add(new Person("Raiden Hamilton"));
    persons.add(new Person("Alexandra Stewblink"));
    persons.add(new Person("Remigius Fat P"));
    persons.add(new Person("Theotimos Xander"));
    persons.add(new Person("Walhberct McDougall"));
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
      if (person.getName().toLowerCase().contains(name)) {
        result.add(person);
      }
    }

    return result;
  }
}
