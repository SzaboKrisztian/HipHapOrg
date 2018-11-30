package org.hiphap.Screens;

import org.hiphap.Employee;

public class EmployeeView extends MenuScreen {
  private Employee currentEmployee;
  Transition handleInput(String input) {
    switch (input) {
      case "1":
      case "2":
        return new Transition(Transition.Type.INVALID, "Not implemented yet");
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
  }

  public EmployeeView(Employee employee) {
    this.currentEmployee = employee;
    this.setMenuNode(true);
    addMenuOption("1", "Edit employee details");
    addMenuOption("2", "Delete employee");
  }

  public void showContent() {
    printPadding();
    System.out.println("Employee name: " + currentEmployee.getFirstName());
  }
}
