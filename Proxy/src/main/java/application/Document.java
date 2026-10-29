package application;

public class Document implements IDocument {
  private static int _id;
  public final int id;
  private final long creationDate;
  protected String content;

  public Document(String content) {
    id = _id++;
    creationDate = System.currentTimeMillis();
    this.content = content;
  }

  public String getContent(User user) throws AccessDeniedException {
    throw new AccessDeniedException("Invalid way to access content");
  }

  public long getCreationDate() {
    return creationDate;
  }
}

interface IDocument {
  String getContent(User user) throws AccessDeniedException;

  long getCreationDate();
}
