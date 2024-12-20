package hotelmanagement.domain;

import hotelmanagement.HotelManagementUtil;
import hotelmanagement.common.RoomStatus;
import hotelmanagement.common.RoomType;
import hotelmanagement.exception.RoomBookingException;
import java.util.List;
import java.util.UUID;

public class Room {

  private final double price;
  private final String roomId;
  private final String roomNumber;
  private final RoomType roomType;
  private final List<RoomKey> roomKeys;
  private final List<RoomHouseKeeping> roomHouseKeepings;
  private RoomStatus roomStatus;

  public Room(RoomType roomType, List<RoomKey> roomKeys, List<RoomHouseKeeping> roomHouseKeepings, double price) {
    this.roomId = "R#" + UUID.randomUUID();
    this.roomNumber = HotelManagementUtil.generateRoomNumber();
    this.roomType = roomType;
    this.roomStatus = RoomStatus.AVAILABLE;
    this.roomKeys = roomKeys;
    this.roomHouseKeepings = roomHouseKeepings;
    this.price = price;
  }

  public double getPrice() {
    return price;
  }

  public String getRoomId() {
    return roomId;
  }

  public String getRoomNumber() {
    return roomNumber;
  }

  public RoomType getRoomType() {
    return roomType;
  }

  public List<RoomKey> getRoomKeys() {
    return roomKeys;
  }

  public RoomStatus getRoomStatus() {
    return roomStatus;
  }

  public void setRoomStatus(RoomStatus roomStatus) {
    this.roomStatus = roomStatus;
  }

  public void addHouseKeeping(RoomHouseKeeping roomHouseKeeping) {
    if (roomHouseKeepings.contains(roomHouseKeeping)) {
      System.out.println("The room housekeeping already exists for the room. Unable to add house keeping");
      return;
    }

    roomHouseKeepings.add(roomHouseKeeping);
    System.out.println("Room house keeping added successfully to the room.");
  }

  public void bookRoom() {
    if (roomStatus == RoomStatus.AVAILABLE) {
      this.roomStatus = RoomStatus.BOOKED;
      roomKeys.clear();
      roomHouseKeepings.clear();
    } else {
      System.out.printf("The current room status is %s. Unable to book room.\n", roomStatus);
      throw new RoomBookingException(String.format("The current room status is %s. Unable to book room.", roomStatus));
    }
  }

  public void checkIn() {
    if (roomStatus == RoomStatus.BOOKED) {
      this.roomStatus = RoomStatus.OCCUPIED;
    } else {
      System.out.printf("The current room status is %s. Unable to check-in.\n", roomStatus);
    }
  }

  public void checkOut() {
    if (roomStatus == RoomStatus.OCCUPIED) {
      roomStatus = RoomStatus.AVAILABLE;
      roomKeys.clear();
      roomHouseKeepings.clear();
    } else {
      System.out.printf("The current room status is %s. Unable to check-out.\n", roomStatus);
    }
  }

  public void registerRoomKey(RoomKey roomKey) {
    if (!roomKey.isActive()) {
      if (roomKeys.contains(roomKey)) {
        System.out.println("Room key already assigned. Cannot re-register!");
        return;
      }

      roomKey.setActive(true);
      this.roomKeys.add(roomKey);
      System.out.println("Room key assigned successfully.");
    } else {
      System.out.println("Room key is already active. Cannot register!");
    }
  }
}
