package application;

import java.util.*;
import java.util.Random;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    WeatherStation wt = new WeatherStation();
    WeatherSubscriber ws1 = new WeatherSubscriber("Ooh the temp changed to");
    WeatherSubscriber ws2 = new WeatherSubscriber("Oh no, the temp changed to");
    WeatherSubscriber ws3 = new WeatherSubscriber("Ooh boy! The temp changed to");
    WeatherSubscriber ws4 = new WeatherSubscriber("Ohohoo! The temp changed to");
    wt.subscribe(ws1);
    wt.subscribe(ws2);
    wt.subscribe(ws3);
    wt.subscribe(ws4);
    wt.start();
  }
}

class WeatherStation extends Thread {
  int temperature;
  List<WeatherSubscriber> subs = new ArrayList<>();

  public void run() {
    Random rnd = new Random();
    temperature = rnd.nextInt(-15, 15);
    try {
      while (true) {
        sleep(1000);
        updateTemperature();
        notifySubs();
      }
    } catch (Exception e) {
      // TODO: handle exception
    }

  }

  private void updateTemperature() {
    Random rnd = new Random();
    boolean i = rnd.nextBoolean();
    // supposedly the way to clamp things in java, since there is no built in method
    // like every other language
    temperature = i ? Math.max(-25, Math.min(25, temperature + 1)) : Math.max(-25, Math.min(25, temperature - 1));
  }

  public void notifySubs() {
    for (WeatherSubscriber weatherSubscriber : subs) {
      weatherSubscriber.recieve(temperature);
    }
  }

  public void subscribe(WeatherSubscriber sub) {
    subs.add(sub);
  }

  public void unsubscribe(WeatherSubscriber sub) {
    subs.remove(sub);
  }
}

class WeatherSubscriber {
  String message;

  public WeatherSubscriber(String _message) {
    message = _message;
  }

  public synchronized void recieve(int temp) {
    System.out.printf("%s: %d\n", message, temp);
  }
}
