package org.hiphap.Screens;

import org.hiphap.*;

import java.util.ArrayList;

public class ManageStaff extends MenuScreen {
  private Event currentEvent;

  public ManageStaff(Event event) {
    this.currentEvent = event;
    addMenuOption("1", "View staff");
    addMenuOption("2", "Add staff from employee database");
    addMenuOption("3", "Delete staff");
  }


  @Override
  void showContent() {
    clearScreen();
  }

  @Override
  Transition handleInput(String input) {
    Transition result;
    switch (input) {
      case "1":
        ArrayList<Employee> list = filterEmployees();
        for (Employee item: list) {
          System.out.printf(" - %s%n", item);
        }
        return new Transition(Transition.Type.SUCCESS);
      case "2":
        result = new SelectEmployeeScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Employee employee = (Employee) result.getPayload();
          currentEvent.addStaff(employee);
          return new Transition(Transition.Type.SUCCESS, "Employee successfully added to staff list.");
        } else {
          return result;
        }
      case "3":
        ArrayList<Employee> filteredEmployees = filterEmployees();
        clearScreen();
        int index = 1;
        for (Employee employee: filteredEmployees) {
          System.out.printf("[%d] %s%n", index++, employee);
        }
        Integer choice = readInteger("Select which employee to remove: ");
        if (choice != null) {
          if (choice >= 1 && choice <= filteredEmployees.size()) {
            boolean success = currentEvent.deleteStaff(filteredEmployees.get(choice - 1));
            if (success) {
              return new Transition(Transition.Type.SUCCESS, "Employee successfully removed from list.");
            } else {
              return new Transition(Transition.Type.INVALID, "Error removing employee from list.");
            }
          } else {
            return new Transition(Transition.Type.INVALID, "Invalid selection; try again.");
          }
        } else {
          return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
        }
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
  }

  private ArrayList<Employee> filterEmployees() {
    String query = clsAndReadString("Enter a search query: ");
    ArrayList<Employee> filteredEmployees = new ArrayList<>();
    for (Employee employee: currentEvent.getStaff()) {
      if (employee.getFirstName().toLowerCase().contains(query.toLowerCase())
          || employee.getMiddleName().toLowerCase().contains(query.toLowerCase())
          || employee.getLastName().toLowerCase().contains(query.toLowerCase())) {
        filteredEmployees.add(employee);
      }
    }
    return filteredEmployees;
  }
}
