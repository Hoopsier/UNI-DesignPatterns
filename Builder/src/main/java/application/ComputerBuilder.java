package application;

public interface ComputerBuilder {
  void setRAM(int ram);

  void setCPU(String cpu);

  void setGPU(String gpu);

  void setMotherBoard(String motherboard);

  void setHardDrive(String hardDrive);

  void setPSU(String psu);

}
