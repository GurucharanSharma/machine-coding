package hotelmanagement.person.employee;

import hotelmanagement.catalog.Catalog;
import hotelmanagement.domain.Room;
import hotelmanagement.person.Account;
import hotelmanagement.person.Guest;

public abstract class Admin extends Employee {

  public Admin(String firstName, String lastName, String email, String contact, Account account) {
    super(firstName, lastName, email, contact, account);
  }

  public void addRoom(Catalog catalog, Room room) {
    if (catalog.getRoomsLookup().containsKey(room.getRoomId())) {
      System.out.println("The room already exists. Unable to add room!");
      return;
    }

    catalog.getRoomsLookup().put(room.getRoomId(), room);
    System.out.println("Room added successfully!");
  }

  public void removeRoom(Catalog catalog, Room room) {
    if (!catalog.getRoomsLookup().containsKey(room.getRoomId())) {
      System.out.println("The room does not exists. Unable to remove room!");
      return;
    }

    catalog.getRoomsLookup().remove(room.getRoomId());
    System.out.println("Room removed successfully!");
  }

  public void addGuests(Catalog catalog, Guest guest) {
    if (catalog.getGuestsLookup().containsKey(guest.getGuestId())) {
      System.out.println("The guest already exists. Unable to add guest!");
      return;
    }

    catalog.getGuestsLookup().put(guest.getGuestId(), guest);
    System.out.println("Guest added successfully!");
  }

  public void removeGuests(Catalog catalog, Guest guest) {
    if (!catalog.getGuestsLookup().containsKey(guest.getGuestId())) {
      System.out.println("The guest does not exists. Unable to remove guest!");
      return;
    }

    catalog.getGuestsLookup().remove(guest.getGuestId());
    System.out.println("Guest removed successfully!");
  }
}
