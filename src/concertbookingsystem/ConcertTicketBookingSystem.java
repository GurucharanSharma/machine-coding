package concertbookingsystem;

import concertbookingsystem.exception.ConcertAdditionException;
import concertbookingsystem.exception.ConcertRetrievalException;
import concertbookingsystem.exception.ConcertTicketBookingException;
import concertbookingsystem.exception.ConcertTicketCancellationException;
import concertbookingsystem.exception.PaymentProcessingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ConcertTicketBookingSystem {

  private static ConcertTicketBookingSystem instance;
  private final Map<String, Booking> bookingLookup;
  private final Map<String, Concert> concertLookup;
  private final ReentrantLock lock;

  private ConcertTicketBookingSystem() {
    bookingLookup = new ConcurrentHashMap<>();
    concertLookup = new ConcurrentHashMap<>();
    lock = new ReentrantLock();
  }

  public static synchronized ConcertTicketBookingSystem getInstance() {
    if (instance == null) {
      instance = new ConcertTicketBookingSystem();
    }

    return instance;
  }

  public void addConcert(Concert concert) {
    if (concertLookup.containsKey(concert.getId())) {
      throw new ConcertAdditionException(String.format("Concert with id %s already exists. Unable to add concert.", concert.getId()));
    }

    concertLookup.put(concert.getId(), concert);
  }

  public Concert getConcert(String id) {
    if (concertLookup.containsKey(id)) {
      return concertLookup.get(id);
    }

    throw new ConcertRetrievalException("No concert found with the id " + id);
  }

  public List<Concert> searchConcert(String artist, String venue, LocalDateTime dateTime) {
    return concertLookup.values().stream()
        .filter(concert -> (!Objects.isNull(artist) && !artist.isBlank() && concert.getArtist().matches(artist)) &&
            (!Objects.isNull(venue) && !venue.isBlank() && concert.getVenue().matches(venue)) &&
            (!Objects.isNull(dateTime) && concert.getTimestamp().equals(dateTime))
        ).toList();
  }

  public Booking bookTickets(User user, Concert concert, List<Seat> seats) {
    lock.lock();

    try {
      seats.forEach(Seat::book);
      String bookingId = generateBookingId();
      Booking booking = new Booking(bookingId, user, concert, seats);
      bookingLookup.put(bookingId, booking);

      if (processPayment(booking)) {
        booking.confirm();
        System.out.printf("Booking with id %s confirmed. Booking details: %s\n", bookingId, booking);

        return booking;
      } else {
        booking.rollback();
        throw new PaymentProcessingException("Error while processing payment for the booking " + bookingId);
      }
    } catch (Exception e) {
      System.out.println("Error while booking tickets: " + e.getMessage());
      throw new ConcertTicketBookingException(e.getMessage());
    } finally {
      lock.unlock();
    }
  }

  public void cancelBooking(String id) {
    if (bookingLookup.containsKey(id)) {
      Booking booking = bookingLookup.remove(id);
      booking.cancel();
    } else {
      throw new ConcertTicketCancellationException("Error while cancelling the booking with id " + id);
    }
  }

  private boolean processPayment(Booking booking) {
    return true;
  }

  private String generateBookingId() {
    return UUID.randomUUID().toString();
  }
}
