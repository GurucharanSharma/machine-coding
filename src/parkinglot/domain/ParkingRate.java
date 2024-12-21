package parkinglot.domain;

import parkinglot.common.VehicleType;

public class ParkingRate {

  private final VehicleType vehicleType;
  private final double hourlyRate;
  private final double baseRate;

  public ParkingRate(VehicleType vehicleType, double hourlyRate, double baseRate) {
    this.vehicleType = vehicleType;
    this.hourlyRate = hourlyRate;
    this.baseRate = baseRate;
  }

  public VehicleType getVehicleType() {
    return vehicleType;
  }

  public double getHourlyRate() {
    return hourlyRate;
  }

  public double getBaseRate() {
    return baseRate;
  }
}
