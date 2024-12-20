package hotelmanagement.domain;

import hotelmanagement.common.BookingStatus;
import hotelmanagement.person.Guest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class RoomBooking {

  private final String bookingId;
  private final LocalDateTime createdDate;
  private final LocalDateTime checkInDate;
  private final LocalDateTime checkOutDate;
  private final Guest guest;
  private final Room room;
  private final Invoice invoice;
  private final long duration;
  private BookingStatus bookingStatus;

  public RoomBooking(LocalDateTime createdDate, LocalDateTime checkInDate, LocalDateTime checkOutDate, Guest guest, Room room) {
    this.bookingId = "B#" + UUID.randomUUID();
    this.createdDate = createdDate;
    this.checkInDate = checkInDate;
    this.checkOutDate = checkOutDate;
    this.duration = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    this.guest = guest;
    this.room = room;
    this.invoice = new Invoice(room.getPrice());
    this.bookingStatus = BookingStatus.CONFIRMED;
  }

  public void cancelBooking() {
    if (bookingStatus == BookingStatus.CONFIRMED) {
      bookingStatus = BookingStatus.CANCELLED;
      room.checkOut();
      System.out.println("Room booking has been cancelled successfully.");
    } else {
      System.out.printf("The current room booking status is %s. Unable to cancel booking.", bookingStatus);
    }
  }

  public BookingStatus getBookingStatus() {
    return bookingStatus;
  }

  public void setBookingStatus(BookingStatus bookingStatus) {
    this.bookingStatus = bookingStatus;
  }

  public String getBookingId() {
    return bookingId;
  }

  public LocalDateTime getCreatedDate() {
    return createdDate;
  }

  public LocalDateTime getCheckInDate() {
    return checkInDate;
  }

  public LocalDateTime getCheckOutDate() {
    return checkOutDate;
  }

  public Guest getGuest() {
    return guest;
  }

  public Room getRoom() {
    return room;
  }

  public Invoice getInvoice() {
    return invoice;
  }

  public long getDuration() {
    return duration;
  }
}
