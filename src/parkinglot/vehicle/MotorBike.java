package parkinglot.vehicle;

import parkinglot.common.VehicleType;

public class MotorBike implements Vehicle {

  private final String licensePlate;
  private final VehicleType vehicleType;

  public MotorBike(String licensePlate) {
    this.licensePlate = licensePlate;
    this.vehicleType = VehicleType.MOTORBIKE;
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
