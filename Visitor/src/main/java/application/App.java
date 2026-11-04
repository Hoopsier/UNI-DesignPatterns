package application;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    FileSystemVisitor search = new SearchVisitor("etc");
    FileSystemElement[] inHome = { new File("Hoopsy", ".me", 1000) };
    FileSystemElement[] inRoot = { new Directory("home", inHome), new Directory("etc") };
    Directory root = new Directory("/", inRoot);
    for (FileSystemElement item : search.visitDirectory(root)) {
      if (item instanceof Directory) {
        System.out.println(item.getName() + "/");
      }
      if (item instanceof File) {
        File file = (File) item;
        System.out.println(item.getName() + file.getExtention());
      }

    }
  }
}
