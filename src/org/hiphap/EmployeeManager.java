package org.hiphap;

import java.io.File;
import java.util.ArrayList;

/**
 * this class create a new instance of employee
 * saves employees' data into a Array list
 */
public class EmployeeManager {
  private static EmployeeManager instance;
  private ArrayList<Employee> employees = new ArrayList<>();
  private final String EMPLOYEES_DATA_FILE = "data".concat(File.separator).concat("employees.dat");

  private EmployeeManager() {
    
  }

  /**
   * this method creats a new instance of employee manager
   * @return employee manager
   */
  public static EmployeeManager getInstance() {
    if (instance == null) {
      instance = new EmployeeManager();
    }
    return instance;
  }

  /**
   * this creates a array list of employees' name
   * @param name searched person
   * @return list employees'
   */
  public ArrayList<Employee> searchByName(String name) {
    ArrayList<Employee> result = new ArrayList<>();

    for (Employee employee: employees) {
      if (employee.getFirstName().toLowerCase().contains(name)
          || employee.getLastName().toLowerCase().contains(name)) {
        result.add(employee);
      }
    }

    return result;
  }

  /**
   * this creates a array list of employees' based on thier phone numbers'
   * @param phoneNo supplied by user
   * @return list of employees
   */
  public ArrayList<Employee> searchByPhone(String phoneNo) {
    ArrayList<Employee> result = new ArrayList<>();

    for (Employee employee: employees) {
      if (employee.getPhone().toLowerCase().contains(phoneNo)) {
        result.add(employee);
      }
    }

    return result;
  }

  /**
   *this creates a array list of employees' based on thier email
   * @param email supplied by user
   * @return list of employees
   */
  public ArrayList<Employee> searchByEmail(String email) {
    ArrayList<Employee> result = new ArrayList<>();

    for (Employee employee: employees) {
      if (employee.getEmail().toLowerCase().contains(email)) {
        result.add(employee);
      }
    }

    return result;
  }

  /**
   * this loads employees' data to array list
   * @return true or false if successfully loaded
   */
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

  /**
   * this saves employees' data to a file
   */
  public void saveEmployeeData() {
    FileManager.saveBinaryDataToFile(EMPLOYEES_DATA_FILE, employees);
  }

  public void addEmployee(Employee employee) {
    employees.add(employee);
    saveEmployeeData();
  }
}