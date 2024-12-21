package parkinglot.parkingspot;

import parkinglot.common.ParkingSpotType;
import parkinglot.vehicle.Vehicle;

public class HandicappedSpot implements ParkingSpot {

  private String spotId;
  private Vehicle vehicle;
  private boolean isAvailable;

  @Override
  public String getSpotId() {
    return spotId;
  }

  @Override
  public Vehicle getVehicle() {
    return vehicle;
  }

  @Override
  public boolean isAvailable() {
    return isAvailable;
  }

  @Override
  public void parkVehicle(Vehicle vehicle) {
    if (isAvailable) {
      this.vehicle = vehicle;
      isAvailable = false;
    } else {
      System.out.println("Spot is not available!");
    }
  }

  @Override
  public void removeVehicle(Vehicle vehicle) {
    if (this.vehicle == null) {
      System.out.println("No vehicle parked here!");
      return;
    }

    if (this.vehicle.equals(vehicle)) {
      this.vehicle = null;
      isAvailable = true;
    } else {
      System.out.println("Vehicle is not parked here!");
    }
  }

  @Override
  public ParkingSpotType getParkingSpotType() {
    return ParkingSpotType.HANDICAPPED;
  }
}
