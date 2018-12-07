package org.hiphap;

import java.io.File;
import java.util.ArrayList;

/**
 * This class uses the singleton pattern to allow easy access to the {@link Organization} data
 * from any point within the system.
 */
public class OrganizationManager {
  private static OrganizationManager instance;
  /**
   * An {@link ArrayList} that holds all {@link Organization} objects the system keeps track of.
   */
  private ArrayList<Organization> organizations = new ArrayList<>();
  /**
   * A {@link String} representation of the filename to and from which this class
   * loads and saves the data.
   */
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

  /**
   * Get a collection of {@link Organization} objects whose names contain a {@link String} sequence.
   *
   * @param name the {@link String} sequence to search for
   * @return the collection of {@link Organization} objects that match the query
   */
  public ArrayList<Organization> searchByName(String name) {
    ArrayList<Organization> result = new ArrayList<>();

    for (Organization organization : organizations) {
      if (organization.getName().toLowerCase().contains(name.toLowerCase())) {
        result.add(organization);
      }
    }

    return result;
  }

  /**
   * Get a collection of {@link Organization} objects whose addresses
   * contain a {@link String} sequence.
   *
   * @param address the {@link String} sequence to search for
   * @return the collection of {@link Organization} objects that match the query
   */
  public ArrayList<Organization> searchByAddress(String address) {
    ArrayList<Organization> result = new ArrayList<>();

    for (Organization organization : organizations) {
      if (organization.getAddress().toLowerCase().contains(address.toLowerCase())) {
        result.add(organization);
      }
    }

    return result;
  }

  /**
   * Get a collection of {@link Organization} objects whose email
   * addresses contain a {@link String} sequence.
   *
   * @param email the {@link String} sequence to search for
   * @return the {@link ArrayList} of {@link Organization} objects that match the query
   */
  public ArrayList<Organization> searchByEmail(String email) {
    ArrayList<Organization> result = new ArrayList<>();

    for (Organization organization : organizations) {
      if (organization.getEmail().toLowerCase().contains(email.toLowerCase())) {
        result.add(organization);
      }
    }

    return result;
  }

  /**
   * Get a collection of {@link Organization} objects whose phone numbers contain
   * a {@link String} sequence.
   *
   * @param phoneNo the {@link String} sequence to search for
   * @return the {@link ArrayList} of {@link Organization} objects that match the query
   */
  public ArrayList<Organization> searchByPhone(String phoneNo) {
    ArrayList<Organization> result = new ArrayList<>();

    for (Organization organization : organizations) {
      if (organization.getPhone().toLowerCase().contains(phoneNo.toLowerCase())) {
        result.add(organization);
      }
    }

    return result;
  }

  public ArrayList<Organization> getOrganizations() {
    return this.organizations;
  }

  /**
   * Loads the contents from the data file into the internal {@link ArrayList}.
   */
  @SuppressWarnings("unchecked")
  public void loadOrganizationData() {
    Object data = FileManager.loadBinaryDataFromFile(ORGANIZATIONS_DATA_FILE);
    if (data != null) {
      try {
        organizations = (ArrayList<Organization>) data;
        Logger.getInstance().write("Organization data loaded successfully.");
      } catch (ClassCastException e) {
        Logger.getInstance().write("Error loading organization data: " + e.toString());
      }
    }
  }

  /**
   * Writes the contents of the internal {@link ArrayList} into the data file.
   */
  public void saveOrganizationData() {
    FileManager.saveBinaryDataToFile(ORGANIZATIONS_DATA_FILE, organizations);
  }

  /**
   * Adds an {@link Organization} object into the collection and saves the data to file.
   *
   * @param organization the {@link Organization} object to be added
   */
  public void addOrganization(Organization organization) {
    organizations.add(organization);
    saveOrganizationData();
  }

  /**
   * Removes an {@link Organization} object from the collection and saves the data to file.
   *
   * @param organization the {@link Organization} to be removed from the collection
   */
  public void deleteOrganization(Organization organization) {
    organizations.remove(organization);
    saveOrganizationData();
  }
}
