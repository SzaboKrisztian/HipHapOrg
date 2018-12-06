package org.hiphap;

public class Employee extends Person implements CsvPrintable {
  private double hourlyRate;
  public Employee(String name) {
    super(name);
  }

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

  public String getCsvString() {
    return getFirstName().concat("|").concat(getLastName()).concat("|").
        concat(getMiddleName()).concat("|").concat(getEmail()).concat("|").
        concat(getPhone()).concat("|").concat(Double.toString(hourlyRate));
  }
}
