package hotelmanagement.person.employee;

import hotelmanagement.person.Account;
import hotelmanagement.person.Person;
import java.util.UUID;

public abstract class Employee extends Person {

  private final String employeeId;

  public Employee(String firstName, String lastName, String email, String contact, Account account) {
    super(firstName, lastName, email, contact, account);
    this.employeeId = "E#" + UUID.randomUUID().toString().substring(0, 8);
  }

  public String getEmployeeId() {
    return employeeId;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", contact='" + contact + '\'' +
        ", account=" + account + '\'' +
        ", employeeId='" + employeeId +
        '}';
  }
}
