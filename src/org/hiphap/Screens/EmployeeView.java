package org.hiphap.Screens;

import org.hiphap.Employee;
import org.hiphap.EmployeeManager;

public class EmployeeView extends MenuScreen {
  private Employee currentEmployee;
  Transition handleInput(String input) {
    switch (input) {
      case "1":
        String firstName = null;
        do {
          clearScreen();
          System.out.println("Old name: " + currentEmployee.toString());
          if (firstName != null && firstName.equals("")) {
            System.out.println("First name cannot be blank.");
          }
          firstName = readString("Input first name: ");
        } while (firstName.equals(""));
        String lastName = readString("Input last name: ");
        String middleName = readString("Input middle name: ");
        currentEmployee.setFirstName(firstName);
        currentEmployee.setLastName(lastName);
        currentEmployee.setMiddleName(middleName);
        return new Transition(Transition.Type.SUCCESS, "Employee name successfully changed.");
      case "2":
        clearScreen();
        System.out.println("Old email address: " + (currentEmployee.getEmail().equals("") ?
            "N/A" : currentEmployee.getEmail()));
        currentEmployee.setEmail(readString("Input new email address: "));
        return new Transition(Transition.Type.SUCCESS, "Email address successfully changed.");
      case "3":
        clearScreen();
        System.out.println("Old phone number: " + (currentEmployee.getPhone().equals("") ?
            "N/A" : currentEmployee.getPhone()));
        currentEmployee.setPhone(readString("Input new phone number: "));
        return new Transition(Transition.Type.SUCCESS, "Phone number successfully changed.");
      case "4":
        clearScreen();
        System.out.println("Old hourly rate: " + currentEmployee.getHourlyRate());
        Double hourlyRate = readDouble("Enter new hourly rate: ");
        if (hourlyRate == null) {
          return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
        } else {
          currentEmployee.setHourlyRate(hourlyRate);
          return new Transition(Transition.Type.SUCCESS, "Hourly rate successfully changed.");
        }
      case "5":
        if (clsAndReadBoolean("WARNING! Operation cannot be undone. Are you " +
            "sure you wish to delete this employee?")) {
          if (EmployeeManager.getInstance().deleteEmployee(currentEmployee)) {
            return new Transition(Transition.Type.BACK, "Employee successfully deleted.");
          } else {
            return new Transition(Transition.Type.ERROR, "Error deleting person.");
          }
        } else {
          return new Transition(Transition.Type.ERROR, "Operation aborted.");
        }
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
  }

  public EmployeeView(Employee employee) {
    this.currentEmployee = employee;
    this.setMenuNode(true);
    addMenuOption("1", "Edit employee name");
    addMenuOption("2", "Edit email address");
    addMenuOption("3", "Edit phone number");
    addMenuOption("4", "Edit hourly rate");
    addMenuOption("5", "Delete employee");
  }

  public void showContent() {
    clearScreen();
    System.out.println("Employee name: " + currentEmployee.toString());
    System.out.println("Email address: " + (currentEmployee.getEmail().equals("") ?
        "N/A" : currentEmployee.getEmail()));
    System.out.println("Phone number: " + (currentEmployee.getPhone().equals("") ?
        "N/A" : currentEmployee.getPhone()));
    System.out.println("Hourly rate: " + currentEmployee.getHourlyRate());
  }
}
