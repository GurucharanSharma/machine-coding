package hotelmanagement.catalog;

import hotelmanagement.HotelManagementUtil;
import hotelmanagement.domain.room.Room;
import hotelmanagement.domain.RoomBooking;
import hotelmanagement.domain.RoomKey;
import hotelmanagement.common.RoomStatus;
import hotelmanagement.common.RoomType;
import hotelmanagement.person.Guest;
import hotelmanagement.person.employee.Employee;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Catalog implements Search {

  private final Map<String, Room> roomsLookup;
  private final Map<String, Guest> guestsLookup;
  private final Map<String, Employee> employeeLookup;
  private final Map<String, RoomBooking> bookingsLookup;
  private final List<RoomKey> availableKeys;
  private final List<RoomKey> assignedKeys;

  public Catalog() {
    this.roomsLookup = new ConcurrentHashMap<>();
    this.guestsLookup = new ConcurrentHashMap<>();
    this.employeeLookup = new ConcurrentHashMap<>();
    this.bookingsLookup = new ConcurrentHashMap<>();
    this.availableKeys = new ArrayList<>();
    this.assignedKeys = new ArrayList<>();

    generateRoomKeys();
  }

  public Map<String, RoomBooking> getBookingsLookup() {
    return bookingsLookup;
  }

  public Map<String, Employee> getEmployeeLookup() {
    return employeeLookup;
  }

  public Map<String, Guest> getGuestsLookup() {
    return guestsLookup;
  }

  public Map<String, Room> getRoomsLookup() {
    return roomsLookup;
  }

  public List<RoomKey> getAvailableKeys() {
    return availableKeys;
  }

  public List<RoomKey> getAssignedKeys() {
    return assignedKeys;
  }

  @Override
  public List<Employee> searchEmployee(String id, String email, String contact) {
    return employeeLookup.values().stream()
        .filter(employee -> ((id != null && !id.isBlank() && employee.getEmployeeId().equals(id)) || (email != null && !email.isBlank()
            && employee.getEmail().equals(email)) || (contact != null && !contact.isBlank() && employee.getContact().equals(contact)))
        ).toList();
  }

  @Override
  public List<Room> searchRoom(String id, String number, RoomType type, RoomStatus status) {
    return roomsLookup.values().stream()
        .filter(room -> ((id != null && !id.isBlank() && room.getRoomId().equals(id)) ||
            (number != null && !number.isBlank() && room.getRoomNumber().equals(number)) ||
            (type != null && room.getRoomType() == type) ||
            (status != null && room.getRoomStatus() == status))
        ).toList();
  }

  @Override
  public List<RoomBooking> searchBooking(String bookingId, String guestId) {
    return bookingsLookup.values().stream()
        .filter(roomBooking -> (bookingId != null && !bookingId.isBlank() && roomBooking.getBookingId().equals(bookingId)) ||
            (guestId != null && !guestId.isBlank() && roomBooking.getGuest().getGuestId().equals(guestId))
        ).toList();
  }

  private void generateRoomKeys() {
    for (int i = 0; i < 5; i++) {
      availableKeys.add(new RoomKey(HotelManagementUtil.generateBarcode(10), false, false, LocalDateTime.MIN, "SYSTEM"));
    }
  }
}
