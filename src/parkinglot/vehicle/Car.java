package parkinglot.vehicle;

import parkinglot.common.VehicleType;

public class Car implements Vehicle {

  private final String licensePlate;
  private final VehicleType vehicleType;

  public Car(String licensePlate) {
    this.licensePlate = licensePlate;
    this.vehicleType = VehicleType.CAR;
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
