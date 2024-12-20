package hotelmanagement.domain.room;

import hotelmanagement.common.RoomType;

public class DoubleRoomFactory extends RoomFactory {

  @Override
  public Room createRoom(double price) {
    return new Room(RoomType.DOUBLE, price);
  }
}
