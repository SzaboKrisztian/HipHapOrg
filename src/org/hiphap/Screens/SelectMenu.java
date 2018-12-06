package org.hiphap.Screens;

import org.hiphap.Employee;
import org.hiphap.Event;
import org.hiphap.Organization;
import org.hiphap.Person;

public class SelectMenu extends MenuScreen {
  public SelectMenu() {
    this.setMenuNode(true);
    addMenuOption("1", "Select an event");
    addMenuOption("2", "Select a person");
    addMenuOption("3", "Select an organization");
    addMenuOption("4", "Select an employee");
  }

  Transition handleInput(String input) {
    Transition result;
    switch (input) {
      case "1":
        result = new SelectEventScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Event event = (Event) result.getPayload();
          return new Transition(Transition.Type.SWITCH, new EventView(event));
        } else {
          return result;
        }
      case "2":
        result = new SelectPersonScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Person person = (Person) result.getPayload();
          return new Transition(Transition.Type.SWITCH, new PersonView(person));
        } else {
          return result;
        }
      case "3":
        result = new SelectOrganizationScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Organization organization = (Organization) result.getPayload();
          return new Transition(Transition.Type.SWITCH, new OrganizationView(organization));
        } else {
          return result;
        }
      case "4":
        result = new SelectEmployeeScreen().show(null);
        if (result.getType() == Transition.Type.REPLY) {
          Employee employee = (Employee) result.getPayload();
          return new Transition(Transition.Type.SWITCH, new EmployeeView(employee));
        } else {
          return result;
        }
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
  }

  public void showContent() {
    printPadding();
  }
}