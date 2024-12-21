package parkinglot.payment;

import parkinglot.common.PaymentType;

public class CardPayment extends Payment {

  private final String cardNumber;
  private final String cardHolderName;
  private final String expiryDate;
  private final int cvv;
  private final PaymentType paymentType;

  public CardPayment(double amount, String cardNumber, String cardHolderName,
      String expiryDate, int cvv, PaymentType paymentType) {
    super(amount);
    this.cardNumber = cardNumber;
    this.cardHolderName = cardHolderName;
    this.expiryDate = expiryDate;
    this.cvv = cvv;
    this.paymentType = paymentType;
  }

  @Override
  public boolean makePayment(double amount) {
    // logic to make payment using card
    return true;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public String getCardHolderName() {
    return cardHolderName;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public int getCvv() {
    return cvv;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }
}
