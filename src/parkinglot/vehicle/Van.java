package parkinglot.vehicle;

import parkinglot.common.VehicleType;

public class Van implements Vehicle {

  private final String licensePlate;
  private final VehicleType vehicleType;

  public Van(String licensePlate) {
    this.licensePlate = licensePlate;
    this.vehicleType = VehicleType.VAN;
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
