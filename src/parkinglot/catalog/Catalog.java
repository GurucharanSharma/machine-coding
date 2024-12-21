package parkinglot.catalog;

import java.util.List;
import java.util.Map;
import parkinglot.ParkingFloor;
import parkinglot.actors.Person;
import parkinglot.common.ParkingSpotType;
import parkinglot.common.PersonType;
import parkinglot.parkingspot.ParkingSpot;

public class Catalog {

  private Map<String, ParkingFloor> parkingFloors;
  private Map<PersonType, Map<String, Person>> persons;
  private Map<Boolean, Map<ParkingSpotType, List<ParkingSpot>>> parkingSpots;
}
