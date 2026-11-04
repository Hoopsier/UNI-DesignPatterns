package application;

public class App {
  public static void main(String[] args) {
    FileSystemElement[] inHome = { new File("Hoopsy", ".me", 1000) };
    FileSystemElement[] inRoot = { new Directory("home", inHome), new Directory("etc") };
    Directory root = new Directory("/", inRoot);

    SearchVisitor search = new SearchVisitor("etc");
    root.accept(search);
    for (FileSystemElement item : search.getResults()) {
      if (item instanceof Directory) {
        System.out.println(item.getName() + "/");
      }
      if (item instanceof File) {
        File file = (File) item;
        System.out.println(item.getName() + file.getExtention());
      }
    }

    SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
    root.accept(sizeCalculator);
    System.out.println("Total size: " + sizeCalculator.getTotalSize());
  }
}
