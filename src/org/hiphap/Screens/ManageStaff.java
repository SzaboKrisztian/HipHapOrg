package org.hiphap.Screens;

import org.hiphap.*;

import java.util.ArrayList;

public class ManageStaff extends MenuScreen {
  private Event currentEvent;

  public ManageStaff(Event event) {
    this.currentEvent = event;
    addMenuOption("1", "View and edit staff");
    addMenuOption("2", "Add staff from employee database");
    addMenuOption("3", "Remove staff");
  }


  @Override
  void showContent() {
    clearScreen();
  }

  @Override
  Transition handleInput(String input) {
    Transition result;
    int index;
    switch (input) {
      case "1":
        ArrayList<Employee> list = filterEmployees();
        index = 1;
        for (Employee item: list) {
          System.out.printf("[%d] %s - %.2f hours%n", index++, item, currentEvent.getHours(item));
        }
        String answer = readString("Pick entry to edit, anything else to go back: ");
        try {
          int option = Integer.parseInt(answer);
          if (option >= 1 && option <= list.size()) {
            Employee selectedEmployee = list.get(option - 1);
            clearScreen();
            System.out.printf("Employee was previously scheduled to work %.2f hours.%n", currentEvent.getHours(selectedEmployee));
            Double numHours = readDouble("Enter new amount of hours: ");
            if (numHours == null) {
              numHours = 0.0;
            }
            currentEvent.setHours(selectedEmployee, numHours);
            return new Transition(Transition.Type.SUCCESS);
          } else {
            return new Transition(Transition.Type.SUCCESS);
          }
        } catch (NumberFormatException e) {
          return new Transition(Transition.Type.SUCCESS);
        }
      case "2":
        result = new SelectEmployeeScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Employee employee = (Employee) result.getPayload();
          if (currentEvent.getStaff().contains(employee)) {
            return new Transition(Transition.Type.ERROR, "Cannot add the same employee twice.");
          }
          if (isThereScheduleConflict(employee)) {
            readString("Any input to continue...");
            return new Transition(Transition.Type.ERROR, "Cannot add employee because of scheduling conflict.");
          }
          Double numHours = clsAndReadDouble("Enter number of hours the employee is scheduled to work: ");
          if (numHours == null) {
            numHours = 0.0;
          }
          currentEvent.addStaff(employee, numHours);
          return new Transition(Transition.Type.SUCCESS, "Employee successfully added to staff list.");
        } else {
          return result;
        }
      case "3":
        ArrayList<Employee> filteredEmployees = filterEmployees();
        clearScreen();
        index = 1;
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
              return new Transition(Transition.Type.ERROR, "Error removing employee from list.");
            }
          } else {
            return new Transition(Transition.Type.ERROR, "Invalid selection; try again.");
          }
        } else {
          return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
        }
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
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

  private boolean isThereScheduleConflict(Employee employee) {
    ArrayList<Event> workingIn = new ArrayList<>();
    for (Event event: getAllEvents(EventManager.getInstance().searchByName(""))) {
      if (event.isWorking(employee)) {
        workingIn.add(event);
      }
    }

    for (Event event: workingIn) {
      if (currentEvent.isScheduleOverlap(event)) {
        System.out.println("Schedule overlap with event " + event.getName());
        return true;
      }
    }

    return false;
  }

  private ArrayList<Event> getAllEvents(ArrayList<Event> events) {
    ArrayList<Event> result = new ArrayList<>();
    for (Event event: events) {
      result.add(event);
      if (event instanceof Arrangement) {
        result.addAll(getAllEvents(((Arrangement) event).getSubEvents()));
      }
    }
    return result;
  }
}
