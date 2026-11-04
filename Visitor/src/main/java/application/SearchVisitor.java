package application;

import java.util.ArrayList;
import java.util.List;

public class SearchVisitor implements FileSystemVisitor {
  private String searchingFor = "";
  private List<FileSystemElement> returnees = new ArrayList<>();

  public SearchVisitor(String searchFor) {
    searchingFor = searchFor;
  }

  public List<FileSystemElement> visitDirectory(Directory element) {
    if (element instanceof Directory) {
      if (element.getName().contains(searchingFor)) {
        returnees.add(element);
      }
      for (FileSystemElement item : element.getContents()) {
        if (item instanceof Directory) {
          visitDirectory((Directory) item);
        }
        if (item instanceof File) {
          visitFile((File) item);
        }
      }
    }
    return returnees;
  }

  public FileSystemElement visitFile(File element) {
    if (element.getName().contains(searchingFor)) {
      returnees.add(element);
    }
    if (element.getExtention().contains(searchingFor)) {
      returnees.add(element);
    }
    return null;
  }
}
