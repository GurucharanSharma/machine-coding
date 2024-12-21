package parkinglot.payment;

import java.time.LocalDateTime;
import parkinglot.common.PaymentStatus;

public abstract class Payment {

  protected final String paymentId;
  protected final double amount;
  protected final LocalDateTime paymentDateTime;
  protected PaymentStatus status;

  public Payment(double amount) {
    this.paymentId = "PAY#" + LocalDateTime.now();
    this.amount = amount;
    this.paymentDateTime = LocalDateTime.now();
    this.status = PaymentStatus.PENDING;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public double getAmount() {
    return amount;
  }

  public LocalDateTime getPaymentDateTime() {
    return paymentDateTime;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public abstract boolean makePayment(double amount);
}
