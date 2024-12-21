package parkinglot.parkingspot;

import parkinglot.common.ParkingSpotType;
import parkinglot.vehicle.Vehicle;

public interface ParkingSpot {

  String getSpotId();

  Vehicle getVehicle();

  boolean isAvailable();

  void parkVehicle(Vehicle vehicle);

  void removeVehicle(Vehicle vehicle);

  ParkingSpotType getParkingSpotType();
}
