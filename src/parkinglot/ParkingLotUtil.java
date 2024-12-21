package parkinglot;

import java.util.Random;

public class ParkingLotUtil {

  public static String generateLicensePlate() {
    Random random = new Random();
    StringBuilder licensePlate = new StringBuilder();

    // Generate 3 random uppercase letters
    for (int i = 0; i < 3; i++) {
      char randomLetter = (char) ('A' + random.nextInt(26)); // A-Z
      licensePlate.append(randomLetter);
    }

    // Add a hyphen
    licensePlate.append('-');

    // Generate 4 random digits
    for (int i = 0; i < 4; i++) {
      int randomDigit = random.nextInt(10); // 0-9
      licensePlate.append(randomDigit);
    }

    return licensePlate.toString();
  }
}
