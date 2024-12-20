package hotelmanagement.service;

import java.time.LocalDateTime;

public class RoomService extends Service {

  public RoomService(String description, double price) {
    this.description = description;
    this.price = price;
    this.requestedAt = LocalDateTime.now();
  }
}