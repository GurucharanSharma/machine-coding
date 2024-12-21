package hotelmanagement.person;

public abstract class Admin extends Person {

  abstract void addAccount(Account account);

  public Admin(String firstName, String lastName, String email, String contact, Account account) {
    super(firstName, lastName, email, contact, account);
  }
}
