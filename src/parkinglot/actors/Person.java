package parkinglot.actors;

import parkinglot.common.PersonType;

public abstract class Person {

  private final String firstname;
  private final String lastname;
  private final String email;
  private final String contact;
  private PersonType personType;

  public Person(String firstname, String lastname, String email, String contact) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.contact = contact;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getEmail() {
    return email;
  }

  public String getContact() {
    return contact;
  }

  public PersonType getPersonType() {
    return personType;
  }
}
