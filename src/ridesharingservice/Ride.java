package ridesharingservice;

public class Ride {

  private String id;
  private Passenger passenger;
  private Driver driver;
  private Location source;
  private Location destination;
  private double fare;

  public Ride(Location destination, Location source, Driver driver, Passenger passenger, String id, double fare) {
    this.destination = destination;
    this.source = source;
    this.driver = driver;
    this.passenger = passenger;
    this.id = id;
    this.fare = fare;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }
}
