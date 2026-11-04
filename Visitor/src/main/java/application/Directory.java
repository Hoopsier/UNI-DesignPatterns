package application;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystemElement {
  private List<FileSystemElement> contents;
  private String name;

  public Directory(String name, FileSystemElement[] contents) {
    this.name = name;
    this.contents = new ArrayList<>();
    for (FileSystemElement item : contents) {
      this.contents.add(item);
    }
  }

  public Directory(String name) {
    this.name = name;
    contents = new ArrayList<>();
  }

  public List<FileSystemElement> getContents() {
    return contents;
  }

  public String getName() {
    return name;
  }

  public void accept(FileSystemVisitor visitor) {
    visitor.visitDirectory(this);
  }
}
