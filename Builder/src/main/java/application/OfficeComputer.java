package application;

public class OfficeComputer {
  // private String GPU;
  private int RAM;
  private String CPU;
  private String HardDrive;
  // private String MotherBoard;
  // private String PSU;

  // public Computer(int ram, String gpu, String cpu, String psu, String
  // motherboard, String hardDrive) {
  // RAM = ram;
  // GPU = gpu;
  // CPU = cpu;
  // PSU = psu;
  // MotherBoard = motherboard;
  // HardDrive = hardDrive;
  // }
  public OfficeComputer(int ram, String cpu, String hardDrive) {
    RAM = ram;
    CPU = cpu;
    HardDrive = hardDrive;
  }

  public String getCPU() {
    return CPU;
  }

  // public String getGPU() {
  // return GPU;
  // }

  public String getHardDrive() {
    return HardDrive;
  }

  // public String getMotherBoard() {
  // return MotherBoard;
  // }

  // public String getPSU() {
  // return PSU;
  // }

  public int getRAM() {
    return RAM;
  }

  public String toString() {
    String returnedString = "OfficeComputer:";
    returnedString = returnedString.concat("\nCPU: " + CPU);
    returnedString = returnedString.concat("\nRAM: " + RAM);
    returnedString = returnedString.concat("\nHardDrive: " + HardDrive);
    returnedString = returnedString.concat("\n");
    return returnedString;
  }
}
