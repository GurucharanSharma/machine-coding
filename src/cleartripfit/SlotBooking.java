package cleartripfit;

public class SlotBooking {

  private final User user;
  private final WorkoutSlot workoutSlot;

  SlotBooking(User user, WorkoutSlot workoutSlot) {
    this.user = user;
    this.workoutSlot = workoutSlot;
  }

  public User getUser() {
    return user;
  }

  public WorkoutSlot getWorkoutSlot() {
    return workoutSlot;
  }
}
