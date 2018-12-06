package org.hiphap.Screens;

import org.hiphap.Organization;
import org.hiphap.OrganizationManager;

public class CreateOrganizationScreen extends Screen {
  public Transition show(String message) {
    Organization newOrganization;
    String firstName = null;
    do {
      if (firstName != null) {
        System.out.println("This field must be completed");
      }
      firstName = clsAndReadString("Enter the organization's name: ");
    } while (firstName.equals(""));
    newOrganization = new Organization(firstName);
    newOrganization.setAddress(clsAndReadString("Enter the organization's address: "));
    newOrganization.setEmail(clsAndReadString("Enter the organization's email: "));
    newOrganization.setPhone(clsAndReadString("Enter the organization's phone number: "));
    OrganizationManager.getInstance().addOrganization(newOrganization);
    return new Transition(Transition.Type.BACK, "Organization successfully created.");
  }
}
