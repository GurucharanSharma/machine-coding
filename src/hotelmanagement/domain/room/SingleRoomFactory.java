package hotelmanagement.domain.room;

import hotelmanagement.common.RoomType;

public class SingleRoomFactory extends RoomFactory {

  @Override
  public Room createRoom(double price) {
    return new Room(RoomType.SINGLE, price);
  }
}
