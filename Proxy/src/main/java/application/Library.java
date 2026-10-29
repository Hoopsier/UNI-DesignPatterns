package application;

import java.util.HashMap;
import java.util.Map;

public class Library {
  private static int _id = 0;
  private Map<Integer, Document> contents;
  private Map<Integer, DocumentProxy> proxies;

  Library() {
    this.contents = new HashMap<>();
    this.proxies = new HashMap<>();
  }

  public Document getDocument(int id) {
    return contents.get(id);
  }

  public DocumentProxy getDocumentProxy(int id) {
    return proxies.get(id);
  }

  public void addDocument(String content) {
    contents.put(_id, new Document(content));
    proxies.put(_id, new DocumentProxy(getDocument(_id)));
    _id++;
  }
}
