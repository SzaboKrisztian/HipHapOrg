package org.hiphap.MenuScreens;

import org.hiphap.Organization;
import org.hiphap.OrganizationManager;

import java.util.ArrayList;

public class SelectOrganizationScreen extends MenuScreen {
  public SelectOrganizationScreen() {
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
        ArrayList<Organization> result = OrganizationManager.getInstance().searchByName(query);
        if (result.isEmpty()) {
          return new Transition(Transition.Type.INVALID, "No organization name matched your query.");
        } else {
          if (result.size() == 1) {
            return new Transition(Transition.Type.SWITCH, new OrganizationView(result.get(0)));
          } else {
            return new Transition(Transition.Type.SWITCH, new OrganizationListView(result));
          }
        }
      case "2":
      case "3":
        return new Transition(Transition.Type.INVALID, "Not implemented yet");
      default:
        return null;
    }
  }
}