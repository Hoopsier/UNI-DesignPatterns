package application.devices;

public class CRT extends Tv {
  private int volume = 3;
  private boolean snowing = false;

  @Override
  public void setVolume(int volume) {
    if (volume > 8) {
      this.volume = 8;
    } else if (volume < 0) {
      this.volume = 0;
    } else {
      this.volume = volume;
    }
  }

  @Override
  public void setChannel(int channel) {
    if (channel <= 0 || channel > 15)
      snowing = true;
    else
      snowing = false;
    this.channel = channel;
  }

  @Override
  public void printStatus() {
    System.out.println("------------------------------------");
    System.out.println("| I'm CRT set.");
    System.out.println("| I'm " + status());
    System.out.println("| Current volume is " + volume / 8 * 100 + "%");
    System.out.println("| Current channel is " + channel);
    System.out.println("------------------------------------\n");
  }

  private String status() {
    if (!on)
      return "disabled";
    if (snowing)
      return "snowing";
    return "enabled";
  }
}
