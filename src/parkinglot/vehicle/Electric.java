package parkinglot.vehicle;

import parkinglot.common.VehicleType;

public class Electric implements Vehicle {

  private final String licensePlate;
  private final VehicleType vehicleType;

  public Electric(String licensePlate) {
    this.licensePlate = licensePlate;
    this.vehicleType = VehicleType.ELECTRIC;
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
