package org.hiphap;

import java.io.File;
import java.util.ArrayList;

public class EmployeeManager {
  private static EmployeeManager instance;
  private ArrayList<Employee> employees = new ArrayList<>();
  private final String EMPLOYEES_DATA_FILE = "data".concat(File.separator).concat("employees.dat");

  private EmployeeManager() {
    
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
      if (employee.getFirstName().toLowerCase().contains(name.toLowerCase())
          || employee.getLastName().toLowerCase().contains(name.toLowerCase())) {
        result.add(employee);
      }
    }

    return result;
  }

  public ArrayList<Employee> searchByPhone(String phoneNo) {
    ArrayList<Employee> result = new ArrayList<>();

    for (Employee employee: employees) {
      if (employee.getPhone().toLowerCase().contains(phoneNo.toLowerCase())) {
        result.add(employee);
      }
    }

    return result;
  }

  public ArrayList<Employee> searchByEmail(String email) {
    ArrayList<Employee> result = new ArrayList<>();

    for (Employee employee: employees) {
      if (employee.getEmail().toLowerCase().contains(email.toLowerCase())) {
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

  public void addEmployee(Employee employee) {
    employees.add(employee);
    saveEmployeeData();
  }

  public boolean deleteEmployee(Employee employee) {
    boolean result = employees.remove(employee);
    saveEmployeeData();
    return result;
  }
}