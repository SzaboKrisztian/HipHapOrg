package org.hiphap;

/**
 * This class represents a HipHapOrg employee within the system, thus making it
 * easier for it to be reused under several different events. It inherits from
 * the {@link Person} class, and further adds the attribute of an hourly rate.
 */
public class Employee extends Person implements CsvPrintable {
  /**
   * A {@link Double} representation of this Employee's hourly rate that is
   * used to calculate the pay for any particular event that the he/she will
   * be engaged in.
   */
  private double hourlyRate;

  /**
   * Constructor that creates a new Employee object
   *
   * @param firstName the first name of the Employee. Cannot be blank.
   */
  public Employee(String firstName) {
    super(firstName);
  }

  /**
   * Constructor that creates a new Employee object based on data
   * contained in another {@link Person} object
   *
   * @param person the {@link Person} object to extract the data from
   */
  public Employee(Person person) {
    super(person.getFirstName());
    this.setLastName(person.getLastName());
    this.setMiddleName(person.getMiddleName());
    this.setEmail(person.getEmail());
    this.setPhone(person.getPhone());
    this.hourlyRate = 0.0;
  }

  public double getHourlyRate() {
    return this.hourlyRate;
  }

  public void setHourlyRate(double hourlyRate) {
    this.hourlyRate = hourlyRate;
  }

  /**
   * Method implementation from the CsvPrintable interface
   *
   * @return the String representation of attribute data in CVS format
   */
  public String getCsvString() {
    return getFirstName().concat("|").concat(getLastName()).concat("|").
        concat(getMiddleName()).concat("|").concat(getEmail()).concat("|").
        concat(getPhone()).concat("|").concat(Double.toString(hourlyRate));
  }
}
