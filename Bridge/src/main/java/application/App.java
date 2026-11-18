package application;

import application.devices.CRT;
import application.devices.Device;
import application.devices.Radio;
import application.devices.Tv;
import application.remotes.AdvancedRemote;
import application.remotes.BasicRemote;
import application.remotes.OldAssRemote;

public class App {
  public static void main(String[] args) {
    testDevice(new CRT());
    testDevice(new Tv());
    testDevice(new Radio());
  }

  public static void testDevice(Device device) {
    System.out.println("Tests with Old remote.");
    OldAssRemote oldRemote = new OldAssRemote(device);
    oldRemote.power();
    device.printStatus();

    System.out.println("Tests with basic remote.");
    BasicRemote basicRemote = new BasicRemote(device);
    for (int i = 0; i < 5; i++) {
      oldRemote.channelDown();
    }
    basicRemote.power();
    device.printStatus();

    System.out.println("Tests with advanced remote.");
    AdvancedRemote advancedRemote = new AdvancedRemote(device);
    advancedRemote.power();
    advancedRemote.mute();
    device.printStatus();

  }
}
