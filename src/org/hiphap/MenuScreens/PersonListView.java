package org.hiphap.MenuScreens;

import org.hiphap.Person;

import java.util.List;

public class PersonListView extends MenuScreen {
  private List<Person> personList;
  private final int MAX_ITEMS = 20;

  public PersonListView(List<Person> personList) {
    this.personList = personList;
    int limit = Math.min(personList.size(), MAX_ITEMS);
    for (int i = 0; i < limit; i++) {
      String name = personList.get(i).getName();
      if (name.length() > 50) {
        name = name.substring(0, 50);
      }
      addMenuOption(Integer.toString(i + 1), name);
    }
  }


  @Override
  void showContent() {
    if (personList.size() > MAX_ITEMS) {
      printPadding(-1);
      System.out.println("Showing first " + MAX_ITEMS + " results; refine your query for more precise results.");
    } else {
      printPadding();
    }
  }

  @Override
  Transition handleInput(String input) {
    int index;
    try {
      index = Integer.parseInt(input) - 1;
      if (index >= 0 && index < personList.size()) {
        return new Transition(Transition.Type.SWITCH, new PersonView(personList.get(index)));
      } else {
        return new Transition(Transition.Type.INVALID, "Invalid choice. Try again: ");
      }
    } catch (NumberFormatException e) {
      return new Transition(Transition.Type.INVALID, "Invalid input. Try again: ");
    }
  }
}
