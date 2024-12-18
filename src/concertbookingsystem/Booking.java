package concertbookingsystem;

import concertbookingsystem.common.BookingStatus;
import concertbookingsystem.exception.BookingCancellationException;
import concertbookingsystem.exception.BookingConfirmationException;
import concertbookingsystem.exception.BookingRollbackException;
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
    } else {
      throw new BookingConfirmationException(String.format("The booking %s is in %s state. Unable to confirm booking.", id, bookingStatus));
    }
  }

  public void cancel() {
    if (bookingStatus == BookingStatus.CONFIRMED) {
      bookingStatus = BookingStatus.CANCELLED;
      seats.forEach(Seat::release);
      System.out.println("Booking " + id + " has been cancelled");
    } else {
      throw new BookingCancellationException(String.format("The booking %s is in %s state. Unable to cancel booking.", id, bookingStatus));
    }
  }

  public void rollback() {
    if (bookingStatus == BookingStatus.PENDING) {
      bookingStatus = BookingStatus.DECLINED;
      seats.forEach(Seat::release);
    } else {
      throw new BookingRollbackException(String.format("The booking %s is in %s state. Unable to decline booking.", id, bookingStatus));
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

  @Override
  public String toString() {
    return "Booking{" +
        "id='" + id + '\'' +
        ", user=" + user +
        ", concert=" + concert +
        ", seats=" + seats +
        ", totalPrice=" + totalPrice +
        ", bookingStatus=" + bookingStatus +
        '}';
  }
}
