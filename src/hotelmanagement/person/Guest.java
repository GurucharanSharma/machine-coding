package hotelmanagement.person;

import java.util.UUID;

public class Guest extends Person {

  private final String guestId;

  public Guest(String firstName, String lastName, String email, String contact, Account account) {
    super(firstName, lastName, email, contact, account);
    this.guestId = "G#" + UUID.randomUUID().toString().substring(0, 8);
  }

  public String getGuestId() {
    return guestId;
  }

  @Override
  public String toString() {
    return "Guest{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", contact='" + contact + '\'' +
        ", account=" + account + '\'' +
        ", guestId='" + guestId +
        '}';
  }
}
