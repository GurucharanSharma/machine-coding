package hotelmanagement.domain;

import java.util.UUID;

public class Invoice {

  private final String id;
  private double amount;

  public Invoice(double amount) {
    this.id = "I#" + UUID.randomUUID().toString().substring(0, 5);
    this.amount = amount;
  }

  public String getId() {
    return id;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
