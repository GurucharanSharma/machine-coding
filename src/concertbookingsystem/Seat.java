package concertbookingsystem;

import concertbookingsystem.exception.SeatNotAvailableException;

public class Seat {

  private final String id;
  private final String seatNumber;
  private final SeatType seatType;
  private final double price;
  private SeatStatus seatStatus;

  public Seat(String id, String seatNumber, SeatType seatType, double price) {
    this.id = id;
    this.seatNumber = seatNumber;
    this.seatType = seatType;
    this.price = price;
    this.seatStatus = SeatStatus.AVAILABLE;
  }

  public void book() {
    if (seatStatus == SeatStatus.AVAILABLE) {
      seatStatus = SeatStatus.BOOKED;
    } else {
      throw new SeatNotAvailableException("Seat is currently not available");
    }
  }

  public void release() {
    if (seatStatus == SeatStatus.BOOKED) {
      seatStatus = SeatStatus.AVAILABLE;
    }
  }

  public double getPrice() {
    return price;
  }

  public SeatStatus getSeatStatus() {
    return seatStatus;
  }

  public SeatType getSeatType() {
    return seatType;
  }

  public String getSeatNumber() {
    return seatNumber;
  }

  public String getId() {
    return id;
  }
}
