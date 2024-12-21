package parkinglot.domain;

public class Entrance {

  private final String entranceId;

  public Entrance(String entranceId) {
    this.entranceId = entranceId;
  }

  public String getEntranceId() {
    return entranceId;
  }
}
