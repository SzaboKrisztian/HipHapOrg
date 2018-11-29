package org.hiphap.Screens;

import org.hiphap.Person;
import org.hiphap.PersonManager;

public class CreatePersonScreen extends Screen {
  public Transition show(String message) {
    Person newPerson;
    String firstName = null;
    do {
      if (firstName != null && firstName.equals("")) {
        System.out.println("This field must be completed");
      }
      firstName = readString("Enter the person's first name: ");
    } while (firstName.equals(""));
    newPerson = new Person(firstName);
    newPerson.setLastName(readString("Enter the person's last name: "));
    newPerson.setMiddleName(readString("Enter the person's middle name: "));
    newPerson.setEmail(readString("Enter the person's email: "));
    newPerson.setPhone(readString("Enter the person's phone number: "));
    PersonManager.getInstance().addPerson(newPerson);
    return new Transition(Transition.Type.BACK, "Person successfully created.");
  }
}
