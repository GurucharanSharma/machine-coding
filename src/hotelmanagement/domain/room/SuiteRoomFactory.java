package hotelmanagement.domain.room;

import hotelmanagement.common.RoomType;

public class SuiteRoomFactory extends RoomFactory {

  @Override
  public Room createRoom(double price) {
    return new Room(RoomType.SUITE, price);
  }
}
