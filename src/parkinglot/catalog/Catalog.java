package parkinglot.catalog;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import parkinglot.ParkingFloor;
import parkinglot.actors.Person;
import parkinglot.common.ParkingSpotType;
import parkinglot.common.PersonType;
import parkinglot.parkingspot.ParkingSpot;

public class Catalog {

  private final Map<String, ParkingFloor> parkingFloors;
  private final Map<PersonType, Map<String, Person>> persons;
  private final Map<Boolean, Map<ParkingSpotType, List<ParkingSpot>>> parkingSpots;

  public Catalog() {
    this.parkingFloors = new ConcurrentHashMap<>();
    this.persons = new ConcurrentHashMap<>();
    this.parkingSpots = new ConcurrentHashMap<>();
  }

  public Map<String, ParkingFloor> getParkingFloors() {
    return parkingFloors;
  }

  public Map<PersonType, Map<String, Person>> getPersons() {
    return persons;
  }

  public Map<Boolean, Map<ParkingSpotType, List<ParkingSpot>>> getParkingSpots() {
    return parkingSpots;
  }
}
