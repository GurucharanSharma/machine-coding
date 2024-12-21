package parkinglot.vehicle.factory;

import parkinglot.common.VehicleType;
import parkinglot.vehicle.Car;
import parkinglot.vehicle.Electric;
import parkinglot.vehicle.MotorBike;
import parkinglot.vehicle.Truck;
import parkinglot.vehicle.Van;
import parkinglot.vehicle.Vehicle;

public class VehicleFactory {

  public static Vehicle createVehicle(VehicleType vehicleType, String licensePlate) {
    return switch (vehicleType) {
      case CAR -> new Car(licensePlate);
      case TRUCK -> new Truck(licensePlate);
      case ELECTRIC -> new Electric(licensePlate);
      case VAN -> new Van(licensePlate);
      case MOTORBIKE -> new MotorBike(licensePlate);
    };
  }
}
