package concertbookingsystem;

import java.time.LocalDateTime;
import java.util.List;

public class Concert {

  private final String id;
  private final String artist;
  private final String venue;
  private final LocalDateTime timestamp;
  private final List<Seat> seats;

  public Concert(String id, String artist, String venue, LocalDateTime timestamp, List<Seat> seats) {
    this.id = id;
    this.artist = artist;
    this.venue = venue;
    this.timestamp = timestamp;
    this.seats = seats;
  }

  public String getId() {
    return id;
  }

  public String getArtist() {
    return artist;
  }

  public String getVenue() {
    return venue;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public List<Seat> getSeats() {
    return seats;
  }
}
