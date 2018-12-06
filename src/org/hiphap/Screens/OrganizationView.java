package org.hiphap.Screens;

import org.hiphap.Organization;
import org.hiphap.OrganizationManager;

public class OrganizationView extends MenuScreen {
  private Organization currentOrganization;
  Transition handleInput(String input) {
    switch (input) {
      case "1":
        String name = null;
        do {
          clearScreen();
          System.out.println("Old name: " + currentOrganization.toString());
          if (name != null) {
            System.out.println("Organization name cannot be blank.");
          }
          name = readString("Input the organization's name: ");
        } while (name.equals(""));
        currentOrganization.setName(name);
        return new Transition(Transition.Type.SUCCESS, "Organization name successfully changed.");
      case "2":
        clearScreen();
        System.out.println("Old address: " + (currentOrganization.getAddress().equals("") ?
            "N/A" : currentOrganization.getAddress()));
        currentOrganization.setAddress(readString("Input new address: "));
        return new Transition(Transition.Type.SUCCESS, "Address successfully changed.");
      case "3":
        clearScreen();
        System.out.println("Old email address: " + (currentOrganization.getEmail().equals("") ?
            "N/A" : currentOrganization.getEmail()));
        currentOrganization.setEmail(readString("Input new email address: "));
        return new Transition(Transition.Type.SUCCESS, "Email address successfully changed.");
      case "4":
        clearScreen();
        System.out.println("Old phone number: " + (currentOrganization.getPhone().equals("") ?
            "N/A" : currentOrganization.getPhone()));
        currentOrganization.setPhone(readString("Input new phone number: "));
        return new Transition(Transition.Type.SUCCESS, "Phone number successfully changed.");
      case "5":
        if (clsAndReadBoolean("WARNING! Operation cannot be undone. Are you " +
            "sure you wish to delete this organization?")) {
          if (OrganizationManager.getInstance().deleteOrganization(currentOrganization)) {
            return new Transition(Transition.Type.BACK, "Organization successfully deleted.");
          } else {
            return new Transition(Transition.Type.ERROR, "Error deleting organization.");
          }
        } else {
          return new Transition(Transition.Type.ERROR, "Operation aborted.");
        }
      default:
        return new Transition(Transition.Type.ERROR, "Invalid input; try again.");
    }
  }

  public OrganizationView(Organization organization) {
    this.currentOrganization = organization;
    this.setMenuNode(true);
    addMenuOption("1", "Edit organization name");
    addMenuOption("2", "Edit address");
    addMenuOption("3", "Edit email address");
    addMenuOption("4", "Edit phone number");
    addMenuOption("5", "Delete organization");
  }

  public void showContent() {
    clearScreen();
    System.out.println("Organization name: " + currentOrganization.getName());
    System.out.println("Address: " + (currentOrganization.getAddress().equals("") ?
        "N/A" : currentOrganization.getAddress()));
    System.out.println("Email address: " + (currentOrganization.getEmail().equals("") ?
        "N/A" : currentOrganization.getEmail()));
    System.out.println("Phone number: " + (currentOrganization.getPhone().equals("") ?
        "N/A" : currentOrganization.getPhone()));
  }
}
