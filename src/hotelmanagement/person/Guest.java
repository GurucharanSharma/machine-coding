package hotelmanagement.person;

import java.util.UUID;

public class Guest extends Person {

  private final String guestId;

  public Guest(String firstName, String lastName, String email, String contact, Account account) {
    super(firstName, lastName, email, contact, account);
    this.guestId = "G#" + UUID.randomUUID();
  }

  public String getGuestId() {
    return guestId;
  }
}
