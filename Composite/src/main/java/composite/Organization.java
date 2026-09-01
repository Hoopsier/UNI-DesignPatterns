package composite;

import java.util.ArrayList;

public class Organization {
  ArrayList<Corpo> corpoObjects;

  Organization() {
    corpoObjects = new ArrayList<>();
  }

  void printSalaries() {
    float sum = 0;
    for (Corpo corpoObject : corpoObjects) {
      sum += corpoObject.getSalary();
    }
    System.out.printf("Total salaries: %.2f\n", sum);
  }

  void print() {
    System.out.println("<Organization>");
    for (Corpo corpo : corpoObjects) {
      if (corpo.getClass() == Department.class) {
        corpo.print(corpo.getChildren(), 1);
      }
    }
    System.out.println("</Organization>");
  }

  void addChild(Department dep) {
    this.corpoObjects.add(dep);
  }

  void removeChild(Department dep) {
    this.corpoObjects.remove(dep);
  }
}

abstract class Corpo {
  public String name;

  public abstract float getSalary();

  public void print(ArrayList<Corpo> children, int depth) {
    indent(depth);
    System.out.printf("<%s", name);
    if (children == null) {
      System.out.println("/>");
      return;
    }
    if (children.isEmpty()) {
      System.out.println("/>");
      return;
    }
    System.out.println(">");

    for (Corpo corpo : children) {
      corpo.print(corpo.getChildren(), depth + 1);
    }

    indent(depth);

    System.out.printf("</%s>\n", name);
  }

  public ArrayList<Corpo> getChildren() {
    return null;
  }

  public void indent(int depth) {
    for (int i = 0; i < depth * 4; i++) {
      System.out.print(" ");
    }
  }
}
