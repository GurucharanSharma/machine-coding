package ridesharingservice;

import ridesharingservice.common.DriverStatus;

public class Driver {

  private final String id;
  private final String name;
  private final String contact;
  private final String licenseNo;
  private Location location;
  private DriverStatus status;

  public Driver(String id, String name, String contact, String licenseNo, Location location) {
    this.id = id;
    this.name = name;
    this.contact = contact;
    this.licenseNo = licenseNo;
    this.location = location;
    this.status = DriverStatus.AVAILABLE;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getContact() {
    return contact;
  }

  public String getLicenseNo() {
    return licenseNo;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public DriverStatus getStatus() {
    return status;
  }

  public void setStatus(DriverStatus status) {
    this.status = status;
  }
}
