package application;

public interface FileSystemVisitor {
  public void visitFile(File element);

  public void visitDirectory(Directory element);
}
