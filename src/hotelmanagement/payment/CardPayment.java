package hotelmanagement.payment;

public class CardPayment implements Payment {

  @Override
  public boolean processPayment(double amount) {
    // Process credit card payment
    return true;
  }
}
