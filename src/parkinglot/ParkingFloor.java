package parkinglot;

public class ParkingFloor {

  private final String floorId;
  private final int level;
  private final long totalSpots;

  public ParkingFloor(String floorId, int level, long totalSpots) {
    this.floorId = floorId;
    this.level = level;
    this.totalSpots = totalSpots;
  }

  public String getFloorId() {
    return floorId;
  }

  public int getLevel() {
    return level;
  }

  public long getTotalSpots() {
    return totalSpots;
  }
}
