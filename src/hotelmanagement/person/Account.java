package hotelmanagement.person;

import hotelmanagement.common.AccountStatus;
import hotelmanagement.exception.AccountCreationException;

public class Account {

  private final String username;
  private final String password;
  private final AccountStatus accountStatus;

  private Account(String username, String password, AccountStatus accountStatus) {
    this.username = username;
    this.password = password;
    this.accountStatus = accountStatus;
  }

  public static Account createAccount(String username, String password) {
    if (username == null || username.isBlank() || password == null || password.isBlank()) {
      System.out.println("Invalid username or password. Username/Password cannot be blank.");
      throw new AccountCreationException("Invalid username or password. Username/Password cannot be blank.");
    }

    return new Account(username, password, AccountStatus.ACTIVE);
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  @Override
  public String toString() {
    return "Account{" +
        "username='" + "XXXXX" + '\'' +
        ", password='" + "XXXXX" + '\'' +
        ", accountStatus=" + accountStatus +
        '}';
  }
}
