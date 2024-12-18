import concertbookingsystem.Booking;
import concertbookingsystem.Concert;
import concertbookingsystem.ConcertTicketBookingSystem;
import concertbookingsystem.Seat;
import concertbookingsystem.SeatStatus;
import concertbookingsystem.SeatType;
import concertbookingsystem.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    ConcertTicketBookingSystem system = ConcertTicketBookingSystem.getInstance();

    List<Seat> concert1Seats = generateSeats(100);
    Concert concert1 = new Concert("C001", "Artist 1", "Venue 1", LocalDateTime.now().plusDays(30), concert1Seats);
    system.addConcert(concert1);

    List<Seat> concert2Seats = generateSeats(50);
    Concert concert2 = new Concert("C002", "Artist 2", "Venue 2", LocalDateTime.now().plusDays(60), concert2Seats);
    system.addConcert(concert2);

    User user1 = new User("U001", "John Doe", "john@example.com");
    User user2 = new User("U002", "Jane Smith", "jane@example.com");

    List<Seat> selectedSeats1 = selectSeats(concert1, 3);
    Booking booking1 = system.bookTickets(user1, concert1, selectedSeats1);

    List<Seat> selectedSeats2 = selectSeats(concert2, 2);
    Booking booking2 = system.bookTickets(user2, concert2, selectedSeats2);

    system.cancelBooking(booking1.getId());

    List<Seat> selectedSeats3 = selectSeats(concert1, 2);
    Booking booking3 = system.bookTickets(user1, concert1, selectedSeats3);
  }

  private static List<Seat> generateSeats(int numberOfSeats) {
    List<Seat> seats = new ArrayList<>();
    for (int i = 1; i <= numberOfSeats; i++) {
      String seatNumber = "S" + i;
      SeatType seatType = (i <= 10) ? SeatType.VIP : (i <= 30) ? SeatType.PREMIUM : SeatType.REGULAR;
      double price = (seatType == SeatType.VIP) ? 100.0 : (seatType == SeatType.PREMIUM) ? 75.0 : 50.0;
      seats.add(new Seat(seatNumber, seatNumber, seatType, price));
    }
    return seats;
  }

  private static List<Seat> selectSeats(Concert concert, int numberOfSeats) {
    List<Seat> availableSeats = concert.getSeats().stream()
        .filter(seat -> seat.getSeatStatus() == SeatStatus.AVAILABLE)
        .limit(numberOfSeats)
        .toList();

    return new ArrayList<>(availableSeats);
  }
}