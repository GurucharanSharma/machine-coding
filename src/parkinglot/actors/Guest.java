package parkinglot.actors;

import java.util.UUID;

public class Guest extends Person {

  private final String guestId;

  public Guest(String firstname, String lastname, String email, String contact) {
    super(firstname, lastname, email, contact);
    this.guestId = "G#" + UUID.randomUUID().toString().substring(0, 8);
  }

  public String getGuestId() {
    return guestId;
  }
}
