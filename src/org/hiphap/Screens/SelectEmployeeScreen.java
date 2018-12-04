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
    String query = clsAndReadString("Enter your search query: ");
    ArrayList<Employee> result;
    switch (input) {
      case "1":
        result = EmployeeManager.getInstance().searchByName(query);
        break;
      case "2":
        result = EmployeeManager.getInstance().searchByPhone(query);
        break;
      case "3":
        result = EmployeeManager.getInstance().searchByEmail(query);
        break;
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
    if (result.isEmpty()) {
      return new Transition(Transition.Type.INVALID, "No employee matched your query.");
    } else {
      return new EmployeeListView(result).show(null);
    }
  }
}