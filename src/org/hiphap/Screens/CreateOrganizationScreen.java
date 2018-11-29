package org.hiphap.Screens;

import org.hiphap.Organization;
import org.hiphap.OrganizationManager;

public class CreateOrganizationScreen extends Screen {
  public Transition show(String message) {
    Organization newOrganization;
    String firstName = null;
    do {
      if (firstName != null && firstName.equals("")) {
        System.out.println("This field must be completed");
      }
      firstName = readString("Enter the organization's first name: ");
    } while (firstName.equals(""));
    newOrganization = new Organization(firstName);
    newOrganization.setAddress(readString("Enter the organization's address: "));
    newOrganization.setEmail(readString("Enter the organization's email: "));
    newOrganization.setPhone(readString("Enter the organization's phone number: "));
    OrganizationManager.getInstance().addOrganization(newOrganization);
    return new Transition(Transition.Type.BACK, "Organization successfully created.");
  }
}
