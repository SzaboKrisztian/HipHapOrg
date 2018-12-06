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
    ArrayList<Employee> result;
    switch (input) {
      case "1":
        query = clsAndReadString("Enter a name to search for: ");
        result = EmployeeManager.getInstance().searchByName(query);
        break;
      case "2":
        query = clsAndReadString("Enter a phone number to search for: ");
        result = EmployeeManager.getInstance().searchByPhone(query);
        break;
      case "3":
        query = clsAndReadString("Enter an email address to search for: ");
        result = EmployeeManager.getInstance().searchByEmail(query);
        break;
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
    if (result.isEmpty()) {
      return new Transition(Transition.Type.ERROR, "No employee matched your query.");
    } else {
      return new EmployeeListView(result).show(null);
    }
  }
}