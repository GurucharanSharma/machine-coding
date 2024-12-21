package parkinglot.actors;

import parkinglot.catalog.Catalog;
import parkinglot.common.PersonType;

public abstract class Admin extends Person {

  public Admin(String firstname, String lastname, String email, String contact, PersonType personType) {
    super(firstname, lastname, email, contact, personType);
  }

  abstract void viewAccount(Person person);

  abstract void updateAccount(Person person, Catalog catalog);

  abstract void addAccount(Person person, Catalog catalog);
}
