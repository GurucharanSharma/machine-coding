package parkinglot.actors;

import parkinglot.common.PersonType;

public abstract class Person {

  protected String firstname;
  protected String lastname;
  protected String email;
  protected String contact;
  protected PersonType personType;

  public Person(String firstname, String lastname, String email, String contact, PersonType personType) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.contact = contact;
    this.personType = personType;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getContact() {
    return contact;
  }

  public void setContact(String contact) {
    this.contact = contact;
  }

  public PersonType getPersonType() {
    return personType;
  }

  public void setPersonType(PersonType personType) {
    this.personType = personType;
  }

  public abstract String getId();
}
