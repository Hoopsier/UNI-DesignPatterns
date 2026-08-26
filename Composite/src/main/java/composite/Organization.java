package composite;

import java.util.ArrayList;

public class Organization {
  final int indentScale = 2;
  String name;
  int depth = 0;
  ArrayList<String> lines;

  ArrayList<String> print() {
    return lines;
  }
}
