package hotelmanagement;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class HotelManagementUtil {

  private static final AtomicInteger roomNo = new AtomicInteger(100);

  public synchronized static String generateRoomNumber() {
    return "RNO#" + roomNo.getAndIncrement();
  }

  public static String generateBarcode(int length) {
    Random random = new Random();
    StringBuilder barcode = new StringBuilder();

    for (int i = 0; i < length; i++) {
      int digit = random.nextInt(10);
      barcode.append(digit);
    }

    return barcode.toString();
  }
}
