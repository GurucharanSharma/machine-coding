package cleartripfit;

import cleartripfit.common.WorkoutType;
import cleartripfit.exception.WorkoutSlotCreationException;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

public class WorkoutSlot {

  private final WorkoutType workoutType;
  private final LocalTime startTime;
  private final LocalTime endTime;
  private final int totalSeats;
  private final Center center;
  private final AtomicInteger availableSeats; // Because everyone will be accessing this

  private WorkoutSlot(WorkoutType workoutType, LocalTime startTime, LocalTime endTime, int totalSeats, Center center) {
    this.workoutType = workoutType;
    this.startTime = startTime;
    this.endTime = endTime;
    this.totalSeats = totalSeats;
    this.center = center;
    this.availableSeats = new AtomicInteger(totalSeats);
  }

  public static WorkoutSlot createWorkoutSlot(WorkoutType workoutType, LocalTime startTime, LocalTime endTime, int totalSeats, Center center) {
    if (!isValidWorkoutSlot(workoutType, center)) {
      System.out.println("The workout is not supported by the center");
      throw new WorkoutSlotCreationException("The workout is not supported by the center");
    } else {
      return new WorkoutSlot(workoutType, startTime, endTime, totalSeats, center);
    }
  }

  private static boolean isValidWorkoutSlot(WorkoutType workoutType, Center center) {
    return center.getSupportedWorkouts().contains(workoutType);
  }

  public WorkoutType getWorkoutType() {
    return workoutType;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public int getTotalSeats() {
    return totalSeats;
  }

  public Center getCenter() {
    return center;
  }

  public int getAvailableSeats() {
    return availableSeats.get();
  }

  public boolean bookSlot() {
    if (availableSeats.get() > 0) {
      return availableSeats.getAndDecrement() > 0;
    } else {
      return false;
    }
  }

  public void cancelSlot() {
    availableSeats.incrementAndGet();
  }

  @Override
  public String toString() {
    return "WorkoutSlot{" +
        "workoutType=" + workoutType +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", totalSeats=" + totalSeats +
        ", center=" + center +
        ", availableSeats=" + availableSeats +
        '}';
  }
}
