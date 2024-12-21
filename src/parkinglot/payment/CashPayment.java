package parkinglot.payment;

import parkinglot.common.PaymentStatus;

public class CashPayment extends Payment {

  private final double cashTendered;

  public CashPayment(double amount, double cashTendered) {
    super(amount);
    this.cashTendered = cashTendered;
  }

  @Override
  public boolean makePayment(double amount) {
    if (cashTendered >= amount) {
      this.status = PaymentStatus.PENDING;
      return true;
    }

    this.status = PaymentStatus.DECLINED;
    return false;
  }
}
