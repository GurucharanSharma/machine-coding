package cleartripfit;

import cleartripfit.common.WorkoutType;
import java.time.LocalTime;
import java.util.List;

public class Center {

  private final String name;
  private final LocalTime openTiming;
  private final LocalTime closeTiming;
  private final List<WorkoutType> supportedWorkoutTypes;

  public Center(String name, LocalTime openTiming, LocalTime closeTiming, List<WorkoutType> supportedWorkoutTypes) {
    this.name = name;
    this.openTiming = openTiming;
    this.closeTiming = closeTiming;
    this.supportedWorkoutTypes = supportedWorkoutTypes;
  }

  public String getName() {
    return name;
  }

  public List<WorkoutType> getSupportedWorkouts() {
    return supportedWorkoutTypes;
  }

  public LocalTime getOpenTiming() {
    return openTiming;
  }

  public LocalTime getCloseTiming() {
    return closeTiming;
  }

  public boolean isWorkoutSupported(WorkoutType workoutType) {
    return supportedWorkoutTypes.contains(workoutType); // Can be called during add user slot to validate whether the center supports the workout
  }

  public boolean isWithinTiming(LocalTime start, LocalTime end) {
    return ((!start.isAfter(openTiming)) && (!end.isBefore(closeTiming)));
  }

  public void addSupportedWorkouts(WorkoutType workoutType) {
    supportedWorkoutTypes.add(workoutType);
  }

  public void removeSupportedWorkouts(WorkoutType workoutType) {
    supportedWorkoutTypes.remove(workoutType);
  }

  @Override
  public String toString() {
    return "Center{" +
        "name='" + name + '\'' +
        ", openTiming=" + openTiming +
        ", closeTiming=" + closeTiming +
        ", supportedWorkouts=" + supportedWorkoutTypes +
        '}';
  }
}
