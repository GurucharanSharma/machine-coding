package hotelmanagement.notification;

import java.util.ArrayList;
import java.util.List;

public class Notifier {

  private final List<Observer> observers = new ArrayList<>();

  public void addObserver(Observer observer) {
    observers.add(observer);
  }

  public void removeObserver(Observer observer) {
    observers.remove(observer);
  }

  public void notifyObservers(String message) {
    observers.forEach(observer -> observer.update(message));
  }
}
