package hotelmanagement;

import hotelmanagement.catalog.Catalog;
import hotelmanagement.common.BookingStatus;
import hotelmanagement.domain.Invoice;
import hotelmanagement.domain.RoomBooking;
import hotelmanagement.domain.RoomKey;
import hotelmanagement.domain.room.Room;
import hotelmanagement.exception.HotelManagementException;
import hotelmanagement.payment.Payment;
import hotelmanagement.person.Guest;
import hotelmanagement.service.Service;
import java.time.LocalDateTime;
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
      double roomCharge = room.getPrice() * booking.getDuration();
      double invoice = booking.getInvoices().stream().map(Invoice::getAmount).mapToDouble(Double::doubleValue).sum();
      double serviceCharge = room.getServices().stream().map(Service::getPrice).mapToDouble(Double::doubleValue).sum();
      double amount = invoice + roomCharge + serviceCharge;

      ///// Print Invoice ///////////////////
      System.out.println("===== Invoice =====");
      System.out.println("Room Charge : " + room.getPrice() + " X " + booking.getDuration() + " = " + roomCharge);
      for (Invoice i : booking.getInvoices()) {
        System.out.printf("%s : %f\n", i.getDescription(), i.getAmount());
      }
      for (Service s : room.getServices()) {
        System.out.printf("%s : %f\n", s.getDescription(), s.getPrice());
      }
      System.out.println("Total : " + amount);
      ///////////////////////////////////////

      if (payment.processPayment(amount)) {
        room.getRoomKeys().forEach(this::unassignKey);
        room.checkOut();
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
