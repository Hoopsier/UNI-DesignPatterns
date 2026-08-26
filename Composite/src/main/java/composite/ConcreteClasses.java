package composite;

import java.util.ArrayList;

class Worker extends Organization {
  Worker(String name, int depth, float salary) {
    this.name = name;
    this.depth = depth;
  }

  @Override
  ArrayList<String> print() {
    lines.add(StringObj.getText("<name>", depth));
    lines.add(StringObj.getText(name, depth + 1));
    lines.add(StringObj.getText("</name>", depth));
    return lines;
  }
}

class Department {

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
