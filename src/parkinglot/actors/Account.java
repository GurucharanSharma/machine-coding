package parkinglot.actors;

import parkinglot.common.AccountStatus;

public class Account {

  private final String username;
  private final String password;
  private final AccountStatus accountStatus;

  public Account(String username, String password) {
    this.username = username;
    this.password = password;
    this.accountStatus = AccountStatus.ACTIVE;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }
}
