package org.hiphap.Screens;

import org.hiphap.Event;
import org.hiphap.EventManager;
import java.util.ArrayList;

public class SelectEventScreen extends MenuScreen {
  public SelectEventScreen() {
    addMenuOption("1", "Find by name");
    addMenuOption("2", "Find by date");
    addMenuOption("3", "Find by location");
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
        ArrayList<Event> result = EventManager.getInstance().searchByName(query);
        if (result.isEmpty()) {
          return new Transition(Transition.Type.INVALID, "No event name matched your query.");
        } else {
          if (result.size() == 1) {
            return new Transition(Transition.Type.SWITCH, new EventView(result.get(0)));
          } else {
            return new Transition(Transition.Type.SWITCH, new EventListView(result));
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