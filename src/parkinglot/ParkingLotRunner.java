package parkinglot;

import parkinglot.common.ParkingSpotType;
import parkinglot.common.VehicleType;
import parkinglot.parkingspot.ParkingSpot;
import parkinglot.parkingspot.factory.ParkingSpotFactory;
import parkinglot.vehicle.Vehicle;
import parkinglot.vehicle.factory.VehicleFactory;

public class ParkingLotRunner {

  public static void main(String[] args) {
    Vehicle car = VehicleFactory.createVehicle(VehicleType.CAR, ParkingLotUtil.generateLicensePlate());
    ParkingSpot parkingSpot = ParkingSpotFactory.createParkingSpot(ParkingSpotType.COMPACT);
  }
}
