package org.hiphap.Screens;

import org.hiphap.Organization;
import java.util.List;

public class OrganizationListView extends MenuScreen {
  private List<Organization> organizationList;
  private final int MAX_ITEMS = 20;

  public OrganizationListView(List<Organization> organizationList) {
    this.organizationList = organizationList;
    int limit = Math.min(organizationList.size(), MAX_ITEMS);
    for (int i = 0; i < limit; i++) {
      String name = organizationList.get(i).getName();
      if (name.length() > 50) {
        name = name.substring(0, 50);
      }
      addMenuOption(Integer.toString(i + 1), name);
    }
  }


  @Override
  void showContent() {
    if (organizationList.size() > MAX_ITEMS) {
      clearScreen();
      System.out.println("Showing first " + MAX_ITEMS + " results; refine your query for more precise results.");
    } else {
      clearScreen();
    }
  }

  @Override
  Transition handleInput(String input) {
    int index;
    try {
      index = Integer.parseInt(input) - 1;
      if (index >= 0 && index < organizationList.size()) {
        Transition result = new Transition(Transition.Type.REPLY);
        result.setPayload(organizationList.get(index));
        return result;
      } else {
        return new Transition(Transition.Type.ERROR, "Invalid choice. Try again: ");
      }
    } catch (NumberFormatException e) {
      return new Transition(Transition.Type.ERROR, "Invalid input. Try again: ");
    }
  }
}
