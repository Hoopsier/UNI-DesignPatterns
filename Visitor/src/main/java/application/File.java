package application;

public class File implements FileSystemElement {
  private String name;
  private String extention;
  private int size;

  public File(String name, String extention, int size) {
    this.name = name;
    this.extention = extention;
    this.size = size;
  }

  public int getSize() {
    return size;
  }

  public String getName() {
    return name;
  }

  public String getExtention() {
    return extention;
  }

  public void accept(FileSystemVisitor visitor) {
    visitor.visitFile(this);
  }
}
