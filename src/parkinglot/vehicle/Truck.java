package parkinglot.vehicle;

import parkinglot.common.VehicleType;

public class Truck implements Vehicle {

  private final String licensePlate;
  private final VehicleType vehicleType;

  public Truck(String licensePlate) {
    this.licensePlate = licensePlate;
    this.vehicleType = VehicleType.TRUCK;
  }

  @Override
  public String getLicensePlate() {
    return licensePlate;
  }

  @Override
  public VehicleType getVehicleType() {
    return vehicleType;
  }
}
