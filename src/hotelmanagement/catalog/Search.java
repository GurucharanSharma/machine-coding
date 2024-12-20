package hotelmanagement.catalog;

import hotelmanagement.domain.room.Room;
import hotelmanagement.domain.RoomBooking;
import hotelmanagement.common.RoomStatus;
import hotelmanagement.common.RoomType;
import hotelmanagement.person.employee.Employee;
import java.util.List;

public interface Search {

  List<Employee> searchEmployee(String id, String email, String contact);

  List<Room> searchRoom(String id, String number, RoomType type, RoomStatus status);

  List<RoomBooking> searchBooking(String bookingId, String guestId);
}
