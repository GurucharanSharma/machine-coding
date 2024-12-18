package cleartripfit.main;

import cleartripfit.Center;
import cleartripfit.User;
import cleartripfit.WorkoutService;
import cleartripfit.WorkoutSlot;
import cleartripfit.common.UserType;
import cleartripfit.common.WorkoutType;
import java.time.LocalTime;
import java.util.List;

public class CleartripFitRunner {

  public static void main(String[] args) {
    WorkoutService service = WorkoutService.getInstance();

    User user1 = new User("1", "A", "989797997878", UserType.ADMIN);
    User user2 = new User("2", "B", "989797997878", UserType.GUEST);
    User user3 = new User("3", "C", "989797997878", UserType.GUEST);
//    User user4 = new User("3", "C", "989797997878", UserType.GUEST);
//    User user5 = new User("3", "C", "989797997878", UserType.GUEST);
//    User user6 = new User("3", "C", "989797997878", UserType.GUEST);
//    User user7 = new User("3", "C", "989797997878", UserType.GUEST);

    List<WorkoutType> workouts = List.of(WorkoutType.YOGA, WorkoutType.WEIGHTS);

    Center center1 = new Center("Koramangala", LocalTime.of(10, 0, 0), LocalTime.of(20, 0, 0), workouts);
    Center center2 = new Center("Bellandur", LocalTime.of(10, 0, 0), LocalTime.of(20, 0, 0), workouts);

    WorkoutSlot workoutSlot1 = WorkoutSlot.createWorkoutSlot(WorkoutType.YOGA, LocalTime.of(10, 0, 0), LocalTime.of(11, 0, 0), 5, center1);
    WorkoutSlot workoutSlot2 = WorkoutSlot.createWorkoutSlot(WorkoutType.WEIGHTS, LocalTime.of(11, 0, 0), LocalTime.of(12, 0, 0), 5, center1);

    WorkoutSlot workoutSlot3 = WorkoutSlot.createWorkoutSlot(WorkoutType.YOGA, LocalTime.of(10, 0, 0), LocalTime.of(11, 0, 0), 5, center2);
    WorkoutSlot workoutSlot4 = WorkoutSlot.createWorkoutSlot(WorkoutType.WEIGHTS, LocalTime.of(10, 0, 0), LocalTime.of(11, 0, 0), 5, center2);

    // Will not allow. Only Admin can add workout slots
    System.out.println("\nAdding workout slots =>");
    service.addWorkoutSlot(user2, workoutSlot1);

    // Will allow as user1 is Admin
    System.out.println("\nAdding workout slots =>");
    service.addWorkoutSlot(user1, workoutSlot1);
    service.addWorkoutSlot(user1, workoutSlot2);
    service.addWorkoutSlot(user1, workoutSlot3);
    service.addWorkoutSlot(user1, workoutSlot4);

    // Print slot status
    System.out.println("\nWorkout Slot Status =>");
    System.out.println(service.viewSlotsByType(WorkoutType.YOGA));
    System.out.println(service.viewSlotsByTypeAndCenter(WorkoutType.YOGA, center1));

    // Reserving a slot for the user
    System.out.println("\nReserving slots for the user =>");
    service.reserveSlotForUser(user2, workoutSlot1);
    service.reserveSlotForUser(user3, workoutSlot1);
    service.reserveSlotForUser(user3, workoutSlot1);
//    service.reserveSlotForUser(user4, workoutSlot1);
//    service.reserveSlotForUser(user5, workoutSlot1);
//    service.reserveSlotForUser(user6, workoutSlot1);
//    service.reserveSlotForUser(user7, workoutSlot1);

    // Print slot status
    System.out.println("\nWorkout Slot Status =>");
    System.out.println(service.viewSlotsByType(WorkoutType.YOGA));
    System.out.println(service.viewSlotsByTypeAndCenter(WorkoutType.YOGA, center1));

    // Cancelling the reserved slot
    System.out.println("\nCancelling slots for the user =>");
    service.cancelSlotForUser(user1, workoutSlot1); // No bookings exist for the user
    service.cancelSlotForUser(user2, workoutSlot2); // Bookings exist but not for the given slots
    service.cancelSlotForUser(user2, workoutSlot1);

    // Print slot status
    System.out.println("\nWorkout Slot Status =>");
    System.out.println(service.viewSlotsByType(WorkoutType.YOGA));
    System.out.println(service.viewSlotsByTypeAndCenter(WorkoutType.YOGA, center1));
  }
}
