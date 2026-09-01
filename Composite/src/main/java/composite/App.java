package composite;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    Organization org = new Organization();
    Department dep1 = new Department("Dep Juan");
    Department dep2 = new Department("Dep Twoan");
    Department dep3 = new Department("Dep Threan");

    Worker wor1 = new Worker("Juan", 15.88f);
    Worker wor2 = new Worker("Twoan", 14.88f);
    Worker wor3 = new Worker("Threan", 16.88f);
    Worker wor4 = new Worker("Foran", 17.88f);
    Worker wor5 = new Worker("Fivan", 17.88f);
    Worker wor6 = new Worker("Sixan", 17.88f);
    Worker wor7 = new Worker("Sevan", 17.88f);

    // Add departments
    org.addChild(dep1);
    org.addChild(dep2);
    dep2.addChild(dep3);

    // Add workers for dep1
    dep1.addChild(wor1);
    dep1.addChild(wor5);
    dep1.addChild(wor7);

    // Add workers for dep2 and 3 #unordered and badly named objects
    dep2.addChild(wor2);
    dep2.addChild(wor6);
    dep3.addChild(wor3);
    dep3.addChild(wor4);

    org.print();
    org.printSalaries();

  }
}
