package org.hiphap.Screens;

import org.hiphap.Organization;
import org.hiphap.OrganizationManager;

import java.util.ArrayList;

public class SelectOrganizationScreen extends MenuScreen {
  public SelectOrganizationScreen() {
    addMenuOption("1", "Find by name");
    addMenuOption("2", "Find by address");
    addMenuOption("3", "Find by phone number");
    addMenuOption("4", "Find by email");
  }

  @Override
  void showContent() {
    printPadding();
  }

  @Override
  Transition handleInput(String input) {
    String query = clsAndReadString("Enter your search query: ");
    ArrayList<Organization> result;
    switch (input) {
      case "1":
        result = OrganizationManager.getInstance().searchByName(query);
        break;
      case "2":
        result = OrganizationManager.getInstance().searchByAddress(query);
        break;
      case "3":
        result = OrganizationManager.getInstance().searchByPhone(query);
        break;
      case "4":
        result = OrganizationManager.getInstance().searchByEmail(query);
        break;
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
    if (result.isEmpty()) {
      return new Transition(Transition.Type.INVALID, "No organization matched your query.");
    } else {
      return new OrganizationListView(result).show(null);
    }
  }
}