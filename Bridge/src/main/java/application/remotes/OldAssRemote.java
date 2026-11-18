package application.remotes;

import application.devices.Device;

public class OldAssRemote extends BasicRemote {

  public OldAssRemote() {
  }

  public OldAssRemote(Device device) {
    this.device = device;
  }

  public void volumeDown() {
    System.out.println("Remote: volume down");
    device.setVolume(device.getVolume() - 1);
  }

  @Override
  public void volumeUp() {
    System.out.println("Remote: volume up");
    device.setVolume(device.getVolume() + 1);
  }
}
