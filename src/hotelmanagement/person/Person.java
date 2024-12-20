package hotelmanagement.person;

public abstract class Person {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final String contact;
  private final Account account;

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
}
