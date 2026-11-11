package application;

public class GamingComputerBuilder implements ComputerBuilder {
  private String GPU;
  private int RAM;
  private String CPU;
  private String HardDrive;
  private String MotherBoard;
  private String PSU;

  public void setCPU(String cpu) {
    CPU = cpu;
  }

  public void setGPU(String gpu) {
    GPU = gpu;
  }

  public void setHardDrive(String hardDrive) {
    HardDrive = hardDrive;
  }

  public void setMotherBoard(String motherBoard) {
    MotherBoard = motherBoard;
  }

  public void setPSU(String psu) {
    PSU = psu;
  }

  public void setRAM(int ram) {
    RAM = ram;
  }

  public GamingComputer getResult() {
    return new GamingComputer(RAM, GPU, CPU, PSU, MotherBoard, HardDrive);
  }
}
