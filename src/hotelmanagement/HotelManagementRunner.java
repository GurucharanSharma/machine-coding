package hotelmanagement;

import hotelmanagement.catalog.Catalog;
import hotelmanagement.common.RoomType;
import hotelmanagement.domain.Room;
import hotelmanagement.domain.RoomBooking;
import hotelmanagement.domain.RoomHouseKeeping;
import hotelmanagement.payment.CardPayment;
import hotelmanagement.person.Account;
import hotelmanagement.person.Guest;
import hotelmanagement.person.employee.HouseKeeper;
import hotelmanagement.person.employee.Manager;
import hotelmanagement.person.employee.Receptionist;
import hotelmanagement.person.employee.Server;
import java.time.LocalDateTime;

public class HotelManagementRunner {

  public static void main(String[] args) {
    HotelManagementService service = HotelManagementService.getInstance();
    Catalog catalog = service.getCatalog();

    System.out.println("# Creating Accounts => ");
    Account guestAccount = Account.createAccount("guest", "guest");
    Account managerAccount = Account.createAccount("manager", "manager");
    Account receptionistAccount = Account.createAccount("receptionist", "receptionist");
    Account houseKeeperAccount = Account.createAccount("housekeeper", "housekeeper");
    Account serverAccount = Account.createAccount("server", "server");

    System.out.println("\n# Creating Actors => ");
    Guest guest = new Guest("A", "A", "a.a@example.com", "9876543210", guestAccount);
    Manager manager = new Manager("B", "B", "b.b@example.com", "6549873210", managerAccount);
    Receptionist receptionist = new Receptionist("C", "C", "c.c@example.com", "3216549870", receptionistAccount);
    HouseKeeper houseKeeper = new HouseKeeper("D", "D", "d.d@example.com", "7418529630", houseKeeperAccount);
    Server server = new Server("E", "E", "e.e@example.com", "8521479630", serverAccount);

    System.out.println("\n# Adding employees => ");
    manager.addEmployee(catalog, manager);
    manager.addEmployee(catalog, receptionist);
    manager.addEmployee(catalog, houseKeeper);
    manager.addEmployee(catalog, server);

    System.out.println("\n# Adding guests => ");
    receptionist.addGuests(catalog, guest);

    System.out.println("\n# Printing catalog => ");
    System.out.println(catalog.getGuestsLookup());
    System.out.println(catalog.getEmployeeLookup());
    System.out.println(catalog.getRoomsLookup());
    System.out.println(catalog.getAvailableKeys());
    System.out.println(catalog.getAssignedKeys());

    System.out.println("\n# Creating room => ");
    Room room = new Room(RoomType.SINGLE, 100.0);

    System.out.println("\n# Booking room => ");
    RoomBooking booking = service.bookRoom(guest, room, LocalDateTime.now(), LocalDateTime.now().plusDays(3));

    System.out.println("\n# Checking in => ");
    service.checkIn(booking.getBookingId());

    RoomHouseKeeping roomHouseKeeping = new RoomHouseKeeping(houseKeeper, LocalDateTime.now().minusMinutes(1), "cleaning");
    room.addHouseKeeping(roomHouseKeeping);

    System.out.println("\n# Booking Details => ");
    System.out.println(booking);

//    System.out.println("\n# Cancel Booking => ");
//    service.cancelRoomBooking(booking.getBookingId());

    System.out.println("\n# Checking out => ");
    service.checkOut(booking.getBookingId(), new CardPayment());

    System.out.println("\n# Booking Details => ");
    System.out.println(booking);

    System.out.println("\n# Printing catalog => ");
    System.out.println(catalog.getGuestsLookup());
    System.out.println(catalog.getEmployeeLookup());
    System.out.println(catalog.getRoomsLookup());
    System.out.println(catalog.getAvailableKeys());
    System.out.println(catalog.getAssignedKeys());
  }
}
