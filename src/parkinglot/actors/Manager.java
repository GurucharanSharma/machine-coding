package parkinglot.actors;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import parkinglot.catalog.Catalog;
import parkinglot.common.PersonType;

public class Manager extends Admin {

  private final String id;

  public Manager(String firstname, String lastname, String email, String contact) {
    super(firstname, lastname, email, contact, PersonType.MANAGER);
    this.id = "M#" + this.hashCode();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public void viewAccount(Person person) {
    System.out.println("Viewing account for " + person.getFirstname() + " " + person.getLastname() + " => ");
    System.out.println(person);
  }

  @Override
  public void updateAccount(Person person, Catalog catalog) {
    System.out.println("Updating account for " + person.getFirstname() + " " + person.getLastname() + " => ");

    if (catalog.getPersons().containsKey(person.getPersonType())) {
      if (catalog.getPersons().get(person.getPersonType()).containsKey(person.getId())) {
        Person existingPerson = catalog.getPersons().get(person.getPersonType()).get(person.getId());
        existingPerson.setFirstname(person.getFirstname());
        existingPerson.setLastname(person.getLastname());
        existingPerson.setContact(person.getContact());
        existingPerson.setEmail(person.getEmail());
        existingPerson.setPersonType(person.getPersonType());

        System.out.println("Account updated successfully!");
      } else {
        System.out.printf("No person with id %s found in the catalog!\n", person.getId());
      }
    } else {
      System.out.printf("No person of the type %s found in the catalog!\n", person.getPersonType());
    }
  }

  @Override
  public void addAccount(Person person, Catalog catalog) {
    System.out.println("Adding account for " + person.getFirstname() + " " + person.getLastname() + " => ");

    if (catalog.getPersons().containsKey(person.getPersonType())) {
      System.out.println("Account already exists!");
    } else {
      Map<String, Person> map = catalog.getPersons().getOrDefault(person.getPersonType(), new ConcurrentHashMap<>());
      map.put(person.getId(), person);
      catalog.getPersons().put(person.getPersonType(), map);

      System.out.println("Account added successfully!");
    }
  }
}
