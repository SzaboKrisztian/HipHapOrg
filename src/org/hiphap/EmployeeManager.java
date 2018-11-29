package org.hiphap;

import java.io.File;
import java.util.ArrayList;

public class EmployeeManager {
  private static EmployeeManager instance;
  private ArrayList<Employee> employees = new ArrayList<>();
  private final String EMPLOYEES_DATA_FILE = "data".concat(File.separator).concat("employees.dat");

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
      if (employee.getFirstName().toLowerCase().contains(name)) {
        result.add(employee);
      }
    }

    return result;
  }

  public boolean loadEmployeeData() {
    Object data = FileManager.loadBinaryDataFromFile(EMPLOYEES_DATA_FILE);
    if (data != null) {
      try {
        employees = (ArrayList<Employee>) data;
        Logger.getInstance().write("Employee data loaded successfully.");
        return true;
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading employee data: " + e.toString());
        return false;
      }
    }
    return false;
  }

  public void saveEmployeeData() {
    FileManager.saveBinaryDataToFile(EMPLOYEES_DATA_FILE, employees);
  }
}