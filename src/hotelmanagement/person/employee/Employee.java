package hotelmanagement.person.employee;

import hotelmanagement.person.Account;
import hotelmanagement.person.Person;
import java.util.UUID;

public abstract class Employee extends Person {

  private final String employeeId;

  public Employee(String firstName, String lastName, String email, String contact, Account account) {
    super(firstName, lastName, email, contact, account);
    this.employeeId = "E#" + UUID.randomUUID();
  }

  public String getEmployeeId() {
    return employeeId;
  }
}
