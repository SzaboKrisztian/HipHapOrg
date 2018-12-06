package org.hiphap.Screens;

import org.hiphap.Person;
import org.hiphap.PersonManager;

import java.util.ArrayList;

public class SelectPersonScreen extends MenuScreen {
  public SelectPersonScreen() {
    addMenuOption("1", "Find by name");
    addMenuOption("2", "Find by phone number");
    addMenuOption("3", "Find by email");
  }

  @Override
  void showContent() {
    clearScreen();
  }

  @Override
  Transition handleInput(String input) {
    String query;
    ArrayList<Person> result;
    switch (input) {
      case "1":
        query = clsAndReadString("Enter a name to search for: ");
        result = PersonManager.getInstance().searchByName(query);
        break;
      case "2":
        query = clsAndReadString("Enter a phone number to search for: ");
        result = PersonManager.getInstance().searchByPhone(query);
        break;
      case "3":
        query = clsAndReadString("Enter an email to search for: ");
        result = PersonManager.getInstance().searchByEmail(query);
        break;
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
    if (result.isEmpty()) {
      return new Transition(Transition.Type.ERROR, "No person matched your query.");
    } else {
      return new PersonListView(result).show(null);
    }
  }
}