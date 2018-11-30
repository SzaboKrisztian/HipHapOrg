package org.hiphap.Screens;

import org.hiphap.Employee;
import org.hiphap.EmployeeManager;

public class CreateEmployeeScreen extends MenuScreen {
  public CreateEmployeeScreen() {
    addMenuOption("1", "From scratch");
    addMenuOption("2", "From existing person");
  }

  @Override
  Transition handleInput(String input) {
    switch (input) {
      case "1":
        return createFromScratch();
      case "2":
        return new Transition(Transition.Type.INVALID, "Not implemented yet");
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input. Try again: ");

    }
  }

  public Transition createFromScratch() {
    Employee newEmployee;
    String firstName = null;
    do {
      if (firstName != null && firstName.equals("")) {
        System.out.println("This field must be completed");
      }
      firstName = readString("Enter the employee's first name: ");
    } while (firstName.equals(""));
    newEmployee = new Employee(firstName);
    newEmployee.setLastName(readString("Enter the employee's last name: "));
    newEmployee.setMiddleName(readString("Enter the employee's middle name: "));
    newEmployee.setEmail(readString("Enter the employee's email: "));
    newEmployee.setPhone(readString("Enter the employee's phone number: "));
    EmployeeManager.getInstance().addEmployee(newEmployee);
    return new Transition(Transition.Type.BACK, "Employee successfully created.");
  }

  @Override
  void showContent() {

  }
}
