package application;

import java.util.List;

interface FileSystemVisitor {
  public FileSystemElement visitFile(File element);

  public List<FileSystemElement> visitDirectory(Directory element);
}
