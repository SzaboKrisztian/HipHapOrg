package org.hiphap.MenuScreens;

import org.hiphap.Employee;

public class EmployeeView extends MenuScreen {
  private Employee currentEmployee;
  Transition handleInput(String input) {
    Transition result;
    switch (input) {
      case "1":
      case "2":
      default:
        result = new Transition(Transition.Type.INVALID, "Not implemented yet");
    }
    return result;
  }

  public EmployeeView(Employee employee) {
    this.currentEmployee = employee;
    addMenuOption("1", "Edit employee details");
    addMenuOption("2", "Delete employee");
  }

  public void showContent() {
    printPadding(1);
    System.out.println("Employee name: " + currentEmployee.getName());
  }
}
