package cleartripfit;

import static java.util.stream.Collectors.groupingBy;

import cleartripfit.common.UserType;
import cleartripfit.common.WorkoutSlotStatus;
import cleartripfit.common.WorkoutType;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class WorkoutService {

  // Singleton pattern
  private static WorkoutService instance;
  private final List<WorkoutSlot> workoutSlots;
  private final Map<String, List<SlotBooking>> userBookings;

  public WorkoutService() {
    this.workoutSlots = new ArrayList<>();
    this.userBookings = new ConcurrentHashMap<>();
  }

  public static synchronized WorkoutService getInstance() {
    if (instance == null) {
      instance = new WorkoutService();
    }

    return instance;
  }

  private static boolean isWorkoutSlotAlreadyBookedByUser(User user, WorkoutSlot workoutSlot, List<SlotBooking> bookings) {
    Optional<Boolean> existingBookingOp = bookings.stream().map(slotBooking -> slotBooking.getWorkoutSlot().equals(workoutSlot)).findAny();
    if (existingBookingOp.isPresent()) {
      System.out.println("Workout slot is already booked for the user " + user.getName());
      return true;
    }
    return false;
  }

  public void addWorkoutSlot(User user, WorkoutSlot workoutSlot) {
    if (user.getUserType() == UserType.ADMIN) {
      workoutSlots.add(workoutSlot);
      System.out.println("Workout slot added successfully");
    } else {
      System.out.printf("User %s does not have access to add workout slots.\n", user.getName());
    }
  }

  public Map<WorkoutSlotStatus, List<WorkoutSlot>> viewSlotsByType(WorkoutType workoutType) {
    List<WorkoutSlot> bookedSlots = userBookings.values().stream()
        .flatMap(slotBookings -> slotBookings.stream().map(SlotBooking::getWorkoutSlot))
        .filter(workoutSlot -> workoutSlot.getWorkoutType() == workoutType)
        .sorted(Comparator.comparing(WorkoutSlot::getStartTime))
        .toList();

    List<WorkoutSlot> availableSlots = workoutSlots.stream()
        .filter(workoutSlot -> workoutSlot.getWorkoutType() == workoutType)
        .filter(workoutSlot -> !bookedSlots.contains(workoutSlot))
        .sorted(Comparator.comparing(WorkoutSlot::getStartTime))
        .toList();

    return Map.of(WorkoutSlotStatus.AVAILABLE, availableSlots, WorkoutSlotStatus.BOOKED, bookedSlots);
  }

  public Map<WorkoutSlotStatus, List<WorkoutSlot>> viewSlotsByTypeAndCenter(WorkoutType workoutType, Center center) {
    List<WorkoutSlot> bookedSlots = userBookings.values().stream()
        .flatMap(slotBookings -> slotBookings.stream().map(SlotBooking::getWorkoutSlot))
        .filter(workoutSlot -> workoutSlot.getWorkoutType() == workoutType)
        .sorted(Comparator.comparing(WorkoutSlot::getStartTime))
        .toList();

    Map<Center, List<WorkoutSlot>> bookedSlotsByCenter = bookedSlots.stream().collect(groupingBy(WorkoutSlot::getCenter));

    Map<Center, List<WorkoutSlot>> availableSlotsByCenter = workoutSlots.stream()
        .filter(workoutSlot -> workoutSlot.getWorkoutType() == workoutType)
        .filter(workoutSlot -> !bookedSlots.contains(workoutSlot))
        .collect(groupingBy(WorkoutSlot::getCenter));

    return Map.of(WorkoutSlotStatus.AVAILABLE, availableSlotsByCenter.getOrDefault(center, new ArrayList<>()), WorkoutSlotStatus.BOOKED,
        bookedSlotsByCenter.getOrDefault(center, new ArrayList<>()));
  }

  public synchronized void reserveSlotForUser(User user, WorkoutSlot workoutSlot) {
    List<SlotBooking> bookings = userBookings.getOrDefault(user.getId(), new ArrayList<>());

    if (isWorkoutSlotAlreadyBookedByUser(user, workoutSlot, bookings)) {
      return;
    }

    if (workoutSlot.bookSlot()) {
      SlotBooking booking = new SlotBooking(user, workoutSlot);
      bookings.add(booking);
      userBookings.put(user.getId(), bookings);

      System.out.println("Workout slot successfully reserved for the user " + user.getName());
    } else {
      System.out.println("No seats available in the given slot. Unable to reserve slot for user");
    }
  }

  public void cancelSlotForUser(User user, WorkoutSlot workoutSlot) {
    if (!userBookings.containsKey(user.getId())) {
      System.out.println("No bookings found for the user");
      return;
    }

    List<SlotBooking> bookings = userBookings.get(user.getId());
    Optional<SlotBooking> bookingOp = bookings.stream().filter(slotBooking -> slotBooking.getWorkoutSlot().equals(workoutSlot)).findAny();
    if (bookingOp.isPresent()) {
      bookings.remove(bookingOp.get());
      workoutSlot.cancelSlot();
      System.out.println("User booking cancelled successfully for the user " + user.getName());
    } else {
      System.out.println("No workout slot found");
    }
  }
}
