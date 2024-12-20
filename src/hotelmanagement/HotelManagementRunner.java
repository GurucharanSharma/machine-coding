package hotelmanagement;

import hotelmanagement.catalog.Catalog;
import hotelmanagement.domain.RoomBooking;
import hotelmanagement.domain.RoomHouseKeeping;
import hotelmanagement.domain.room.DeluxeRoomFactory;
import hotelmanagement.domain.room.DoubleRoomFactory;
import hotelmanagement.domain.room.Room;
import hotelmanagement.domain.room.SingleRoomFactory;
import hotelmanagement.domain.room.SuiteRoomFactory;
import hotelmanagement.notification.Notifier;
import hotelmanagement.payment.CardPayment;
import hotelmanagement.person.Account;
import hotelmanagement.person.Guest;
import hotelmanagement.person.employee.HouseKeeper;
import hotelmanagement.person.employee.Manager;
import hotelmanagement.person.employee.Receptionist;
import hotelmanagement.person.employee.Server;
import hotelmanagement.service.FoodService;
import hotelmanagement.service.Service;
import java.time.LocalDateTime;

public class HotelManagementRunner {

  public static void main(String[] args) {
    HotelManagementService system = HotelManagementService.getInstance();
    Catalog catalog = system.getCatalog();
    Notifier notifier = new Notifier();

    SingleRoomFactory singleRoomFactory = new SingleRoomFactory();
    DoubleRoomFactory doubleRoomFactory = new DoubleRoomFactory();
    DeluxeRoomFactory deluxeRoomFactory = new DeluxeRoomFactory();
    SuiteRoomFactory suiteRoomFactory = new SuiteRoomFactory();

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

    System.out.println("\n# Adding observers => ");
    notifier.addObserver(guest);
    notifier.addObserver(manager);
    notifier.addObserver(receptionist);
    notifier.addObserver(houseKeeper);

    System.out.println("\n# Adding employees => ");
    manager.addEmployee(catalog, manager);
    manager.addEmployee(catalog, receptionist);
    manager.addEmployee(catalog, houseKeeper);
    manager.addEmployee(catalog, server);

    System.out.println("\n# Adding guests => ");
    receptionist.addGuests(catalog, guest);

    System.out.println("\n# Printing catalog => ");
    System.out.println("Guests : " + catalog.getGuestsLookup());
    System.out.println("Employees : " + catalog.getEmployeeLookup());
    System.out.println("Rooms : " + catalog.getRoomsLookup());
    System.out.println("Available Keys : " + catalog.getAvailableKeys());
    System.out.println("Assigned Keys : " + catalog.getAssignedKeys());

    System.out.println("\n# Creating room => ");
    Room room = singleRoomFactory.createRoom(100);

    System.out.println("\n# Booking room => ");
    RoomBooking booking = system.bookRoom(guest, room, LocalDateTime.now(), LocalDateTime.now().plusDays(3));

    System.out.println("\n# Checking in => ");
    system.checkIn(booking.getBookingId());

    System.out.println("\n# Sending check-in notification");
    notifier.notifyObservers("Check-in successful");

    System.out.println("\n# Adding house keeping => ");
    RoomHouseKeeping roomHouseKeeping = new RoomHouseKeeping(houseKeeper, LocalDateTime.now().minusMinutes(1), "cleaning");
    room.addHouseKeeping(roomHouseKeeping);

    Service service = new FoodService("Coleslaw sandwich", 35);
    room.addService(service);

    System.out.println("\n# Booking Details => ");
    System.out.println(booking);

//    System.out.println("\n# Cancel Booking => ");
//    service.cancelRoomBooking(booking.getBookingId());

    System.out.println("\n# Checking out => ");
    system.checkOut(booking.getBookingId(), new CardPayment());

    System.out.println("\n# Booking Details => ");
    System.out.println(booking);

    System.out.println("\n# Printing catalog => ");
    System.out.println("Guests : " + catalog.getGuestsLookup());
    System.out.println("Employees : " + catalog.getEmployeeLookup());
    System.out.println("Rooms : " + catalog.getRoomsLookup());
    System.out.println("Available Keys : " + catalog.getAvailableKeys());
    System.out.println("Assigned Keys : " + catalog.getAssignedKeys());
  }
}
