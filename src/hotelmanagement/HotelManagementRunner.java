package hotelmanagement;

import hotelmanagement.person.Account;
import hotelmanagement.person.Guest;
import hotelmanagement.person.employee.HouseKeeper;
import hotelmanagement.person.employee.Manager;
import hotelmanagement.person.employee.Receptionist;
import hotelmanagement.person.employee.Server;

public class HotelManagementRunner {

  public static void main(String[] args) {
    Account guestAccount = Account.createAccount("guest", "guest");
    Guest guest = new Guest("A", "A", "a.a@example.com", "9876543210", guestAccount);

    Account managerAccount = Account.createAccount("manager", "manager");
    Manager manager = new Manager("B", "B", "b.b@example.com", "6549873210", managerAccount);

    Account receptionistAccount = Account.createAccount("receptionist", "receptionist");
    Receptionist receptionist = new Receptionist("C", "C", "c.c@example.com", "3216549870", receptionistAccount);

    Account houseKeeperAccount = Account.createAccount("housekeeper", "housekeeper");
    HouseKeeper houseKeeper = new HouseKeeper("D", "D", "d.d@example.com", "7418529630", houseKeeperAccount);

    Account serverAccount = Account.createAccount("server", "server");
    Server server = new Server("E", "E", "e.e@example.com", "8521479630", serverAccount);
  }
}
