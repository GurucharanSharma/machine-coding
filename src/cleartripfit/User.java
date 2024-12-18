package cleartripfit;

import cleartripfit.common.UserType;

public class User {

  private final String id;
  private final String name;
  private final String contact;
  private final UserType userType;

  public User(String id, String name, String contact, UserType userType) {
    this.id = id;
    this.name = name;
    this.contact = contact;
    this.userType = userType;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getContact() {
    return contact;
  }

  public UserType getUserType() {
    return userType;
  }
}
