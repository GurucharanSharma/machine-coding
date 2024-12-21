package parkinglot.catalog;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import parkinglot.actors.Person;
import parkinglot.common.ParkingSpotType;
import parkinglot.common.PersonType;
import parkinglot.common.VehicleType;
import parkinglot.domain.Entrance;
import parkinglot.domain.Exit;
import parkinglot.domain.ParkingFloor;
import parkinglot.domain.ParkingRate;
import parkinglot.domain.ParkingTicket;
import parkinglot.parkingspot.ParkingSpot;

public class Catalog {

  private final Map<String, ParkingFloor> parkingFloors;
  private final Map<PersonType, Map<String, Person>> persons;
  private final Map<Boolean, Map<ParkingSpotType, List<ParkingSpot>>> parkingSpots;
  private final Map<String, ParkingTicket> parkingTickets;
  private final Map<String, Entrance> entrances;
  private final Map<String, Exit> exits;
  private final Map<VehicleType, ParkingRate> parkingRates;

  public Catalog() {
    this.parkingFloors = new ConcurrentHashMap<>();
    this.persons = new ConcurrentHashMap<>();
    this.parkingSpots = new ConcurrentHashMap<>();
    this.parkingTickets = new ConcurrentHashMap<>();
    this.entrances = new ConcurrentHashMap<>();
    this.exits = new ConcurrentHashMap<>();
    this.parkingRates = new ConcurrentHashMap<>();
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

  public Map<String, ParkingTicket> getParkingTickets() {
    return parkingTickets;
  }

  public Map<String, Entrance> getEntrances() {
    return entrances;
  }

  public Map<String, Exit> getExits() {
    return exits;
  }

  public Map<VehicleType, ParkingRate> getParkingRates() {
    return parkingRates;
  }
}
