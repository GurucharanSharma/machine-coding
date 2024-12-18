package concertbookingsystem;

import java.util.List;

public class Booking {

  private final String id;
  private final User user;
  private final Concert concert;
  private final List<Seat> seats;
  private final double totalPrice;
  private BookingStatus bookingStatus;

  public Booking(String id, User user, Concert concert, List<Seat> seats) {
    this.id = id;
    this.user = user;
    this.concert = concert;
    this.bookingStatus = BookingStatus.PENDING;
    this.seats = seats;
    this.totalPrice = calculateTotalPrice();
  }

  private double calculateTotalPrice() {
    return seats.stream().mapToDouble(Seat::getPrice).sum();
  }

  public void confirm() {
    if (bookingStatus == BookingStatus.PENDING) {
      this.bookingStatus = BookingStatus.CONFIRMED;
    } else if (bookingStatus == BookingStatus.CONFIRMED) {
      System.out.println("Booking " + id + " is already confirmed");
    } else {
      System.out.println("Booking " + id + " has been cancelled. Cannot confirm the booking.");
    }
  }

  public void cancel() {
    if (bookingStatus == BookingStatus.CONFIRMED) {
      bookingStatus = BookingStatus.CANCELLED;
      seats.forEach(Seat::release);
      System.out.println("Booking " + id + " has been cancelled");
    }
  }

  public String getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public Concert getConcert() {
    return concert;
  }

  public BookingStatus getBookingStatus() {
    return bookingStatus;
  }

  public void setBookingStatus(BookingStatus bookingStatus) {
    this.bookingStatus = bookingStatus;
  }

  public List<Seat> getSeats() {
    return seats;
  }

  public double getTotalPrice() {
    return totalPrice;
  }
}
