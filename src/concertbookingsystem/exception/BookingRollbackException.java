package concertbookingsystem.exception;

public class BookingRollbackException extends RuntimeException {

  public BookingRollbackException(String message) {
    super(message);
  }
}
