package org.hiphap;

import java.util.ArrayList;

public class EmployeeManager {
  private static EmployeeManager instance;
  private ArrayList<Employee> employees = new ArrayList<>();

  private EmployeeManager() {
    employees.add(new Employee("Bob Bobson"));
    employees.add(new Employee("John Johnson"));
    employees.add(new Employee("Eric Ericson"));
    employees.add(new Employee("Stevey Wonder"));
    employees.add(new Employee("Peter Griffin"));
  }

  public static EmployeeManager getInstance() {
    if (instance == null) {
      instance = new EmployeeManager();
    }
    return instance;
  }

  public ArrayList<Employee> searchByName(String name) {
    ArrayList<Employee> result = new ArrayList<>();

    for (Employee employee: employees) {
      if (employee.getName().toLowerCase().contains(name)) {
        result.add(employee);
      }
    }

    return result;
  }
}