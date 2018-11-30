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
    printPadding();
  }

  @Override
  Transition handleInput(String input) {
    String query;
    switch (input) {
      case "1":
        query = readString("Enter a search query: ");
        ArrayList<Person> result = PersonManager.getInstance().searchByName(query);
        if (result.isEmpty()) {
          return new Transition(Transition.Type.INVALID, "No person name matched your query.");
        } else {
          if (result.size() == 1) {
            return new Transition(Transition.Type.SWITCH, new PersonView(result.get(0)));
          } else {
            return new Transition(Transition.Type.SWITCH, new PersonListView(result));
          }
        }
      case "2":
      case "3":
        return new Transition(Transition.Type.INVALID, "Not implemented yet");
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
  }
}