package org.hiphap;

import java.util.ArrayList;

public class OrganizationManager {
  private static OrganizationManager instance;
  private ArrayList<Organization> organizations = new ArrayList<>();

  private OrganizationManager() {
    organizations.add(new Organization("SimCorp"));
    organizations.add(new Organization("Skynet"));
    organizations.add(new Organization("Nakatomi Corp"));
    organizations.add(new Organization("Microshaft"));
    organizations.add(new Organization("C.A.B.A.L."));
  }

  public static OrganizationManager getInstance() {
    if (instance == null) {
      instance = new OrganizationManager();
    }
    return instance;
  }

  public ArrayList<Organization> searchByName(String name) {
    ArrayList<Organization> result = new ArrayList<>();

    for (Organization organization: organizations) {
      if (organization.getName().toLowerCase().contains(name)) {
        result.add(organization);
      }
    }

    return result;
  }
}
