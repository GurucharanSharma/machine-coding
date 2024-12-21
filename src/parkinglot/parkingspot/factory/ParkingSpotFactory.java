package parkinglot.parkingspot.factory;

import parkinglot.common.ParkingSpotType;
import parkinglot.parkingspot.CompactSpot;
import parkinglot.parkingspot.ElectricSpot;
import parkinglot.parkingspot.HandicappedSpot;
import parkinglot.parkingspot.LargeSpot;
import parkinglot.parkingspot.MotorBikeSpot;
import parkinglot.parkingspot.ParkingSpot;

public class ParkingSpotFactory {

  public static ParkingSpot createParkingSpot(ParkingSpotType parkingSpotType) {
    return switch (parkingSpotType) {
      case HANDICAPPED -> new HandicappedSpot();
      case COMPACT -> new CompactSpot();
      case LARGE -> new LargeSpot();
      case MOTORBIKE -> new MotorBikeSpot();
      case ELECTRIC -> new ElectricSpot();
    };
  }
}
