package composite;

import java.util.ArrayList;

class Worker extends Corpo {
  private float salary;

  Worker(String name, float salary) {
    this.name = name;
    this.salary = salary;
  }

  public float getSalary() {
    return salary;
  }
}

class Department extends Corpo {
  private ArrayList<Corpo> children;

  Department(String name) {
    this.name = name;
    children = new ArrayList<>();
  }

  public float getSalary() {
    float sum = 0;
    for (Corpo corpo : children) {
      sum += corpo.getSalary();
    }
    return sum;
  }

  void addChild(Corpo corpo) {
    this.children.add(corpo);
  }

  void removeChild(Corpo corpo) {
    this.children.remove(corpo);
  }

  @Override
  public ArrayList<Corpo> getChildren() {
    return children;
  }
}

class StringObj {
  static String getText(String text, int depth) {
    String indent = "";
    for (int i = 0; i < depth; i++) {
      indent += " ";
    }
    return indent + text;
  }
}
