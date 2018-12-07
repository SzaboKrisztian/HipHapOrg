package org.hiphap;

import java.io.File;
import java.util.ArrayList;

/**
 * This class uses the singleton pattern to allow easy access to the {@link Employee} data
 * from any point within the system.
 */
public class EmployeeManager {
  private static EmployeeManager instance;
  /**
   * An {@link ArrayList} that holds all {@link Employee} objects the system keeps track of.
   */
  private ArrayList<Employee> employees = new ArrayList<>();
  /**
   * A {@link String} representation of the filename to and from which this class
   * loads and saves the data.
   */
  private final String EMPLOYEES_DATA_FILE = "data".concat(File.separator).concat("employees.dat");

  private EmployeeManager() {
    loadEmployeeData();
  }

  public static EmployeeManager getInstance() {
    if (instance == null) {
      instance = new EmployeeManager();
    }
    return instance;
  }

  /**
   * Get a collection of {@link Employee} objects whose names contain a {@link String} sequence.
   *
   * @param name the {@link String} sequence to search for
   * @return the collection of {@link Employee} objects that match the query
   */
  public ArrayList<Employee> searchByName(String name) {
    ArrayList<Employee> result = new ArrayList<>();

    for (Employee employee : employees) {
      if (employee.getFirstName().toLowerCase().contains(name.toLowerCase())
          || employee.getLastName().toLowerCase().contains(name.toLowerCase())
          || employee.getMiddleName().toLowerCase().contains(name.toLowerCase())) {
        result.add(employee);
      }
    }

    return result;
  }

  /**
   * Get a collection of {@link Employee} objects whose phone numbers contain
   * a {@link String} sequence.
   *
   * @param phoneNo the {@link String} sequence to search for
   * @return the {@link ArrayList} of {@link Employee} objects that match the query
   */
  public ArrayList<Employee> searchByPhone(String phoneNo) {
    ArrayList<Employee> result = new ArrayList<>();

    for (Employee employee : employees) {
      if (employee.getPhone().toLowerCase().contains(phoneNo.toLowerCase())) {
        result.add(employee);
      }
    }

    return result;
  }

  /**
   * Get a collection of {@link Employee} objects whose emails contain a {@link String} sequence.
   *
   * @param email the {@link String} sequence to search for
   * @return the {@link ArrayList} of {@link Employee} objects that match the query
   */
  public ArrayList<Employee> searchByEmail(String email) {
    ArrayList<Employee> result = new ArrayList<>();

    for (Employee employee : employees) {
      if (employee.getEmail().toLowerCase().contains(email.toLowerCase())) {
        result.add(employee);
      }
    }

    return result;
  }

  public ArrayList<Employee> getEmployees() {
    return this.employees;
  }

  /**
   * Loads the contents from the data file into the internal {@link ArrayList}.
   */
  @SuppressWarnings("unchecked")
  public void loadEmployeeData() {
    Object data = FileManager.loadBinaryDataFromFile(EMPLOYEES_DATA_FILE);
    if (data != null) {
      try {
        employees = (ArrayList<Employee>) data;
        Logger.getInstance().write("Employee data loaded successfully.");
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading employee data: " + e.toString());
      }
    }
  }

  /**
   * Writes the contents of the internal {@link ArrayList} into the data file.
   */
  public void saveEmployeeData() {
    FileManager.saveBinaryDataToFile(EMPLOYEES_DATA_FILE, employees);
  }

  /**
   * Adds an {@link Employee} object into the collection and saves the data to file.
   *
   * @param employee the {@link Employee} object to be added
   */
  public void addEmployee(Employee employee) {
    employees.add(employee);
    saveEmployeeData();
  }

  /**
   * Removes an {@link Employee} object from the collection and saves the data to file.
   *
   * @param employee the {@link Employee} to be removed from the collection
   */
  public void deleteEmployee(Employee employee) {
    employees.remove(employee);
    saveEmployeeData();
  }
}