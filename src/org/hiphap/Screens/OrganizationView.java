package org.hiphap.Screens;

import org.hiphap.Organization;

public class OrganizationView extends MenuScreen {
  private Organization currentOrganization;
  Transition handleInput(String input) {
    switch (input) {
      case "1":
      case "2":
        return new Transition(Transition.Type.INVALID, "Not implemented yet");
      default:
        return new Transition(Transition.Type.INVALID, "Invalid input; try again.");
    }
  }

  public OrganizationView(Organization organization) {
    this.currentOrganization = organization;
    this.setMenuNode(true);
    addMenuOption("1", "Edit person details");
    addMenuOption("2", "Delete person");
  }

  public void showContent() {
    printPadding();
    System.out.println("Organization name: " + currentOrganization.getName());
  }
}
