package hotelmanagement;

import hotelmanagement.catalog.Catalog;
import hotelmanagement.common.BookingStatus;
import hotelmanagement.domain.Room;
import hotelmanagement.domain.RoomBooking;
import hotelmanagement.domain.RoomKey;
import hotelmanagement.exception.HotelManagementException;
import hotelmanagement.payment.Payment;
import hotelmanagement.person.Guest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class HotelManagementService {

  private static HotelManagementService instance;
  private final Catalog catalog;

  private HotelManagementService() {
    this.catalog = new Catalog();
  }

  public synchronized static HotelManagementService getInstance() {
    if (instance == null) {
      instance = new HotelManagementService();
    }

    return instance;
  }

  public Catalog getCatalog() {
    return catalog;
  }

  public synchronized RoomBooking bookRoom(Guest guest, Room room, LocalDateTime checkInDate, LocalDateTime checkOutDate) {
    try {
      room.bookRoom();
      RoomBooking booking = new RoomBooking(LocalDateTime.now(), checkInDate, checkOutDate, guest, room);
      catalog.getBookingsLookup().put(booking.getBookingId(), booking);
      System.out.println("Room booked successfully!");

      return booking;
    } catch (Exception e) {
      System.out.println("Error while booking room: " + e.getMessage());
    }

    return null;
  }

  public synchronized void cancelRoomBooking(String bookingId) {
    if (!catalog.getBookingsLookup().containsKey(bookingId)) {
      System.out.println("The room booking not found!");
      throw new HotelManagementException("The room booking not found!");
    }

    RoomBooking booking = catalog.getBookingsLookup().get(bookingId);
    booking.getRoom().getRoomKeys().forEach(this::unassignKey);
    booking.cancelBooking();
    catalog.getBookingsLookup().remove(booking.getBookingId());
    System.out.println("Room booking cancelled successfully!");
  }

  public synchronized void checkIn(String bookingId) {
    if (!catalog.getBookingsLookup().containsKey(bookingId)) {
      System.out.println("The room booking not found!");
      throw new HotelManagementException("The room booking not found!");
    }

    RoomBooking booking = catalog.getBookingsLookup().get(bookingId);
    if (booking.getBookingStatus() == BookingStatus.CONFIRMED) {
      booking.getRoom().checkIn();
      booking.getRoom().registerRoomKey(assignKey().orElseThrow(() -> new HotelManagementException("Error while assigning key")));
      System.out.println("Checked-in successfully!");
    } else {
      System.out.printf("The booking status is %s. Cannot check-in!\n", booking.getBookingStatus());
    }
  }

  public synchronized void checkOut(String bookingId, Payment payment) {
    if (!catalog.getBookingsLookup().containsKey(bookingId)) {
      System.out.println("The room booking not found!");
      throw new HotelManagementException("The room booking not found!");
    }

    RoomBooking booking = catalog.getBookingsLookup().get(bookingId);
    if (booking.getBookingStatus() == BookingStatus.CONFIRMED) {
      Room room = booking.getRoom();
      double amount = room.getPrice() * ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());
      if (payment.processPayment(amount)) {
        room.checkOut();
        room.getRoomKeys().forEach(this::unassignKey);
        catalog.getBookingsLookup().remove(bookingId);
        System.out.println("Checkout successful!");
      } else {
        throw new HotelManagementException("Payment failed.");
      }
    } else {
      throw new HotelManagementException("Invalid reservation or reservation not confirmed.");
    }
  }

  private Optional<RoomKey> assignKey() {
    if (!catalog.getAvailableKeys().isEmpty()) {
      RoomKey roomKey = catalog.getAvailableKeys().remove(0);
      catalog.getAssignedKeys().add(roomKey);
      return Optional.of(roomKey);
    } else {
      System.out.println("No more keys to assign!");
      return Optional.empty();
    }
  }

  private void unassignKey(RoomKey roomKey) {
    if (!catalog.getAssignedKeys().contains(roomKey)) {
      System.out.println("The key is not registered. Cannot unassign!");
      return;
    }

    roomKey.setActive(false);
    roomKey.setIssuedAt(LocalDateTime.MIN);

    catalog.getAssignedKeys().remove(roomKey);
    catalog.getAvailableKeys().add(roomKey);
  }

}
