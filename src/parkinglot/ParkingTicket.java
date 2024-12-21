package parkinglot;

import java.time.LocalDateTime;
import parkinglot.common.ParkingTicketStatus;
import parkinglot.parkingspot.ParkingSpot;
import parkinglot.payment.Payment;
import parkinglot.vehicle.Vehicle;

public class ParkingTicket {

  private final String ticketId;
  private final LocalDateTime startDateTime;
  private final ParkingTicketStatus status;
  private final ParkingSpot parkingSpot;
  private final Vehicle vehicle;
  private final Entrance entrance;
  private LocalDateTime endDateTime;
  private double totalCost;
  private Exit exit;
  private Payment payment;

  public ParkingTicket(LocalDateTime startDateTime, ParkingSpot parkingSpot, Vehicle vehicle, Entrance entrance) {
    this.ticketId = "TIC#" + parkingSpot.getSpotId() + "#" + startDateTime;
    this.startDateTime = startDateTime;
    this.parkingSpot = parkingSpot;
    this.vehicle = vehicle;
    this.entrance = entrance;
    this.status = ParkingTicketStatus.ACTIVE;
  }

  public String getTicketId() {
    return ticketId;
  }

  public LocalDateTime getStartDateTime() {
    return startDateTime;
  }

  public ParkingTicketStatus getStatus() {
    return status;
  }

  public ParkingSpot getParkingSpot() {
    return parkingSpot;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public Entrance getEntrance() {
    return entrance;
  }

  public LocalDateTime getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(LocalDateTime endDateTime) {
    this.endDateTime = endDateTime;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(double totalCost) {
    this.totalCost = totalCost;
  }

  public Exit getExit() {
    return exit;
  }

  public void setExit(Exit exit) {
    this.exit = exit;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }
}
