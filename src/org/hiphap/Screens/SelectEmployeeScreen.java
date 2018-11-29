package org.hiphap.Screens;

import org.hiphap.Employee;
import org.hiphap.EmployeeManager;

import java.util.ArrayList;

public class SelectEmployeeScreen extends MenuScreen {
  public SelectEmployeeScreen() {
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
        ArrayList<Employee> result = EmployeeManager.getInstance().searchByName(query);
        if (result.isEmpty()) {
          return new Transition(Transition.Type.INVALID, "No employee name matched your query.");
        } else {
          if (result.size() == 1) {
            return new Transition(Transition.Type.SWITCH, new EmployeeView(result.get(0)));
          } else {
            return new Transition(Transition.Type.SWITCH, new EmployeeListView(result));
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