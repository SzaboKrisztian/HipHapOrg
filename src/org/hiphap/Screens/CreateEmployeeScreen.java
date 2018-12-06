package org.hiphap.Screens;

import org.hiphap.Employee;
import org.hiphap.EmployeeManager;
import org.hiphap.Person;

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
        Transition result = new SelectPersonScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Person person = (Person) result.getPayload();
          Employee newEmployee = new Employee(person);
          Double hourlyRate = clsAndReadDouble("Enter employee's hourly rate: ");
          if (hourlyRate == null) {
            hourlyRate = 0.0;
          }
          newEmployee.setHourlyRate(hourlyRate);
          EmployeeManager.getInstance().addEmployee(newEmployee);
          return new Transition(Transition.Type.BACK, "Employee successfully created.");
        } else {
          return result;
        }
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input. Try again: ");

    }
  }

  private Transition createFromScratch() {
    Employee newEmployee;
    String firstName = null;
    do {
      if (firstName != null) {
        System.out.println("This field must be completed");
      }
      firstName = clsAndReadString("Enter the employee's first name: ");
    } while (firstName.equals(""));
    newEmployee = new Employee(firstName);
    newEmployee.setLastName(clsAndReadString("Enter the employee's last name: "));
    newEmployee.setMiddleName(clsAndReadString("Enter the employee's middle name: "));
    newEmployee.setEmail(clsAndReadString("Enter the employee's email: "));
    newEmployee.setPhone(clsAndReadString("Enter the employee's phone number: "));
    Double hourlyRate = clsAndReadDouble("Enter the employee's hourly rate: ");
    if (hourlyRate == null) {
      hourlyRate = 0.0;
    }
    newEmployee.setHourlyRate(hourlyRate);
    EmployeeManager.getInstance().addEmployee(newEmployee);
    return new Transition(Transition.Type.BACK, "Employee successfully created.");
  }

  @Override
  void showContent() {
    clearScreen();
  }
}
