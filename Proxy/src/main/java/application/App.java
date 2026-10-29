package application;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    Library lib = new Library();
    User jeff = new User("Jeff");
    User janice = new User("Janice");
    AccessControlService.addPermission(0, "Jeff");
    lib.addDocument("Sweeesh");
    lib.addDocument("Swooosh");
    IDocument document = lib.getDocumentProxy(0);
    IDocument document2 = lib.getDocument(0);
    tryDocument(document, jeff);
    tryDocument(document2, jeff);
    tryDocument(document, janice);
  }

  private static void tryDocument(IDocument doc, User user) {
    try {
      System.out.println(doc.getContent(user));
    } catch (AccessDeniedException e) {
      System.err.println(e);
    }
  }
}
