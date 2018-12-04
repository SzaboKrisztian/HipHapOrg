package org.hiphap;

import java.io.File;
import java.util.ArrayList;

public class OrganizationManager {
  private static OrganizationManager instance;
  private ArrayList<Organization> organizations = new ArrayList<>();
  private final String ORGANIZATIONS_DATA_FILE = "data".concat(File.separator).concat("organizations.dat");

  private OrganizationManager() {
    loadOrganizationData();
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
      if (organization.getName().toLowerCase().contains(name.toLowerCase())) {
        result.add(organization);
      }
    }

    return result;
  }

  public ArrayList<Organization> searchByAddress(String address) {
    ArrayList<Organization> result = new ArrayList<>();

    for (Organization organization: organizations) {
      if (organization.getAddress().toLowerCase().contains(address.toLowerCase())) {
        result.add(organization);
      }
    }

    return result;
  }

  public ArrayList<Organization> searchByEmail(String email) {
    ArrayList<Organization> result = new ArrayList<>();

    for (Organization organization: organizations) {
      if (organization.getEmail().toLowerCase().contains(email.toLowerCase())) {
        result.add(organization);
      }
    }

    return result;
  }

  public ArrayList<Organization> searchByPhone(String phoneNo) {
    ArrayList<Organization> result = new ArrayList<>();

    for (Organization organization: organizations) {
      if (organization.getPhone().toLowerCase().contains(phoneNo.toLowerCase())) {
        result.add(organization);
      }
    }

    return result;
  }

  public boolean loadOrganizationData() {
    Object data = FileManager.loadBinaryDataFromFile(ORGANIZATIONS_DATA_FILE);
    if (data != null) {
      try {
        organizations = (ArrayList<Organization>) data;
        Logger.getInstance().write("Organization data loaded successfully.");
        return true;
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading organization data: " + e.toString());
        return false;
      }
    }
    return false;
  }

  public void saveOrganizationData() {
    FileManager.saveBinaryDataToFile(ORGANIZATIONS_DATA_FILE, organizations);
  }

  public void addOrganization(Organization organization) {
    organizations.add(organization);
    saveOrganizationData();
  }

  public boolean deleteOrganization(Organization organization) {
    boolean result = organizations.remove(organization);
    saveOrganizationData();
    return result;
  }
}
