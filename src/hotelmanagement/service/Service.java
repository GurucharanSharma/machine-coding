package hotelmanagement.service;

import java.time.LocalDateTime;

public abstract class Service {

  protected double price;
  protected String description;
  protected LocalDateTime issuedAt;
  protected LocalDateTime requestedAt;

  public double getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  public LocalDateTime getIssuedAt() {
    return issuedAt;
  }

  public void setIssuedAt(LocalDateTime issuedAt) {
    this.issuedAt = issuedAt;
  }

  public void setRequestedAt(LocalDateTime requestedAt) {
    this.requestedAt = requestedAt;
  }

  @Override
  public String toString() {
    return "Service{" +
        "price=" + price +
        ", description='" + description + '\'' +
        ", issuedAt=" + issuedAt +
        ", requestedAt=" + requestedAt +
        '}';
  }
}
