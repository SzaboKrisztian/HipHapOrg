package org.hiphap.Screens;

import org.hiphap.Employee;

import java.util.List;

public class EmployeeListView extends MenuScreen {
  private List<Employee> employeeList;
  private final int MAX_ITEMS = 20;

  public EmployeeListView(List<Employee> employeeList) {
    this.employeeList = employeeList;
    int limit = Math.min(employeeList.size(), MAX_ITEMS);
    for (int i = 0; i < limit; i++) {
      String name = employeeList.get(i).toString();
      if (name.length() > 50) {
        name = name.substring(0, 50);
      }
      addMenuOption(Integer.toString(i + 1), name);
    }
  }


  @Override
  void showContent() {
    if (employeeList.size() > MAX_ITEMS) {
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
      if (index >= 0 && index < employeeList.size()) {
        Transition result = new Transition(Transition.Type.REPLY);
        result.setPayload(employeeList.get(index));
        return result;
      } else {
        return new Transition(Transition.Type.ERROR, "Invalid choice. Try again: ");
      }
    } catch (NumberFormatException e) {
      return new Transition(Transition.Type.ERROR, "Invalid input. Try again: ");
    }
  }
}
