package application;

public class SizeCalculatorVisitor implements FileSystemVisitor {
  private int totalSize = 0;

  public void visitFile(File element) {
    totalSize += element.getSize();
  }

  public void visitDirectory(Directory element) {
    for (FileSystemElement item : element.getContents()) {
      item.accept(this);
    }
  }

  public int getTotalSize() {
    return totalSize;
  }
}
