package hotelmanagement.domain;

import java.util.UUID;

public class Invoice {

  private final String id;
  private final String description;
  private double amount;
  public Invoice(String description, double amount) {
    this.id = "I#" + UUID.randomUUID().toString().substring(0, 5);
    this.description = description;
    this.amount = amount;
  }

  public String getDescription() {
    return description;
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

  @Override
  public String toString() {
    return "Invoice{" +
        "id='" + id + '\'' +
        ", amount=" + amount +
        '}';
  }
}
