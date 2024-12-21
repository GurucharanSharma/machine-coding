package parkinglot;

import parkinglot.common.VehicleType;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.factory.VehicleFactory;

public class ParkingLotRunner {

  public static void main(String[] args) {
    Vehicle car = VehicleFactory.createVehicle(VehicleType.CAR, ParkingLotUtil.generateLicensePlate());
  }
}
