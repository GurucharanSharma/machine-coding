package hotelmanagement.service;

import java.time.LocalDateTime;

public class FoodService extends Service {

  public FoodService(String description, double price) {
    this.description = description;
    this.price = price;
    this.requestedAt = LocalDateTime.now();
  }
}