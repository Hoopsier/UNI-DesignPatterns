package application;

/**
 * I decided to make this actually a thing that can and should be done with
 * Builder, instead of making the director's job meaningless by making hardcoded
 * variables in the Builder methods.
 */
public class App {
  public static void main(String[] args) {
    ComputerDirector director = new ComputerDirector();
    GamingComputerBuilder gamingComputerBuilder = new GamingComputerBuilder();
    OfficeComputerBuilder officeComputerBuilder = new OfficeComputerBuilder();
    director.buildGamingPC(gamingComputerBuilder);
    GamingComputer gamingComputer = gamingComputerBuilder.getResult();
    director.buildOfficePC(officeComputerBuilder);
    OfficeComputer officeComputer = officeComputerBuilder.getResult();
    System.out.println(gamingComputer.toString());
    System.out.println(officeComputer.toString());
  }
}
