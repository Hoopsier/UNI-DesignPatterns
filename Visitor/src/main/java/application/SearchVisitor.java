package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchVisitor implements FileSystemVisitor {
  private String searchingFor;
  private List<FileSystemElement> returnees = new ArrayList<>();

  public SearchVisitor(String searchFor) {
    searchingFor = searchFor;
  }

  public void visitDirectory(Directory element) {
    if (element.getName().contains(searchingFor)) {
      returnees.add(element);
    }
    for (FileSystemElement item : element.getContents()) {
      item.accept(this);
    }
  }

  public void visitFile(File element) {
    if (element.getName().contains(searchingFor)
        || element.getExtention().contains(searchingFor)) {
      returnees.add(element);
    }
  }

  public List<FileSystemElement> getResults() {
    return Collections.unmodifiableList(returnees);
  }
}
