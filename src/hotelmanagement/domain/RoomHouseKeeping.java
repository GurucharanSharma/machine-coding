package hotelmanagement.domain;

import hotelmanagement.person.employee.HouseKeeper;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RoomHouseKeeping {

  private final HouseKeeper houseKeeper;
  private final LocalDateTime startedAt;
  private final long duration;
  private final String comments;

  public RoomHouseKeeping(HouseKeeper houseKeeper, LocalDateTime startedAt, String comments) {
    this.houseKeeper = houseKeeper;
    this.startedAt = startedAt;
    this.duration = ChronoUnit.MINUTES.between(startedAt, LocalDateTime.now());
    this.comments = comments;
  }

  public HouseKeeper getHouseKeeper() {
    return houseKeeper;
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public long getDuration() {
    return duration;
  }

  public String getComments() {
    return comments;
  }
}
