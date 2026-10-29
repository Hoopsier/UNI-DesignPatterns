package application;

public class DocumentProxy implements IDocument {
  private final Document service;
  private long creationDate = -1;

  public DocumentProxy(Document doc) {
    service = doc;
  }

  public String getContent(User user) throws AccessDeniedException {
    if (!AccessControlService.isAllowed(service.id, user.username)) {
      throw new AccessDeniedException("User " + user.username + " not authorized for this action.");
    }
    return service.content;
  }

  public long getCreationDate() {
    if (creationDate == -1) {
      creationDate = service.getCreationDate();
    }
    return creationDate;
  }
}

class AccessDeniedException extends Exception {
  public AccessDeniedException(String m) {
    super("No Access: " + m);
  }
}
