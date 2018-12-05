package org.hiphap;

public class Employee extends Person {
  private double hourlyRate;
  public Employee(String name) {
    super(name);
  }

  public double getHourlyRate() {
    return this.hourlyRate;
  }

  public void setHourlyRate(double hourlyRate) {
    this.hourlyRate = hourlyRate;
  }
}
