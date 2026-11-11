package application;

public class ComputerDirector {
  public void buildGamingPC(ComputerBuilder builder) {
    builder.setCPU("AMD Thread Ripper Pro 9000 series");
    builder.setGPU("AMD RX 9070 XTX");
    builder.setMotherBoard("ASUS PRIME B670 +Bluetooth +WI-FI");
    builder.setRAM(64);
    builder.setHardDrive("Samsung 990 PRO 2T");
    builder.setPSU("ASUS Golden Hour 800W Power Supply 80+ Platinum");
  }

  public void buildOfficePC(ComputerBuilder builder) {

    builder.setCPU("Intel i3 7100f");
    builder.setRAM(8);
    builder.setHardDrive("Kingston 4T Hard Disk Drive");
    // Stuff OfficePC can't even see
    builder.setGPU("AMD 3300");
    builder.setMotherBoard("ASUS 420 +WI-FI");
    builder.setPSU("Shitty PSU that will set the office on fire.");
  }
}
