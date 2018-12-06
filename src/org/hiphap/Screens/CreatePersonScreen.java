package org.hiphap.Screens;

import org.hiphap.Person;
import org.hiphap.PersonManager;

public class CreatePersonScreen extends Screen {
  public Transition show(String message) {
    Person newPerson;
    String firstName = null;
    do {
      if (firstName != null) {
        System.out.println("This field must be completed");
      }
      firstName = clsAndReadString("Enter the person's first name: ");
    } while (firstName.equals(""));
    newPerson = new Person(firstName);
    newPerson.setLastName(clsAndReadString("Enter the person's last name: "));
    newPerson.setMiddleName(clsAndReadString("Enter the person's middle name: "));
    newPerson.setEmail(clsAndReadString("Enter the person's email: "));
    newPerson.setPhone(clsAndReadString("Enter the person's phone number: "));
    PersonManager.getInstance().addPerson(newPerson);
    return new Transition(Transition.Type.BACK, "Person successfully created.");
  }
}
