package hotelmanagement.person;

import hotelmanagement.notification.Observer;

public abstract class Person implements Observer {

  protected final String firstName;
  protected final String lastName;
  protected final String email;
  protected final String contact;
  protected final Account account;

  public Person(String firstName, String lastName, String email, String contact, Account account) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.contact = contact;
    this.account = account;
  }

  public Account getAccount() {
    return account;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public String getContact() {
    return contact;
  }

  @Override
  public void update(String message) {
    System.out.println("Notification for Guest: " + firstName + " " + lastName + ": " + message);
  }
}
