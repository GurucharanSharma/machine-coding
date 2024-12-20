package hotelmanagement.person.employee;

import hotelmanagement.catalog.Catalog;
import hotelmanagement.person.Account;

public class Manager extends Admin {

  public Manager(String firstName, String lastName, String email, String contact, Account account) {
    super(firstName, lastName, email, contact, account);
  }

  public void addEmployee(Catalog catalog, Employee employee) {
    if (catalog.getEmployeeLookup().containsKey(employee.getEmployeeId())) {
      System.out.println("The employee already exists. Unable to add employee!");
      return;
    }

    catalog.getEmployeeLookup().put(employee.getEmployeeId(), employee);
    System.out.println("Employee added successfully!");
  }

  public void removeEmployee(Catalog catalog, Employee employee) {
    if (!catalog.getEmployeeLookup().containsKey(employee.getEmployeeId())) {
      System.out.println("The employee does not exists. Unable to remove employee!");
      return;
    }

    catalog.getEmployeeLookup().remove(employee.getEmployeeId());
    System.out.println("Employee removed successfully!");
  }
}
