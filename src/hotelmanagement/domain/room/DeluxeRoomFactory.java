package hotelmanagement.domain.room;

import hotelmanagement.common.RoomType;

public class DeluxeRoomFactory extends RoomFactory {

  @Override
  public Room createRoom(double price) {
    return new Room(RoomType.DELUXE, price);
  }
}
