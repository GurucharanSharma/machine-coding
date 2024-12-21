package parkinglot.actors;

import java.util.UUID;
import parkinglot.common.PersonType;

public class Guest extends Person {

  private final String id;

  public Guest(String firstname, String lastname, String email, String contact) {
    super(firstname, lastname, email, contact, PersonType.GUEST);
    this.id = "G#" + UUID.randomUUID().toString().substring(0, 8);
  }

  @Override
  public String getId() {
    return id;
  }
}
