package application;

import java.util.ArrayList;
import java.util.List;

/** Recommendations per targetAudience. */
public class Recommendation implements Prototype {
  private List<Book> books;
  private String targetAudience;

  Recommendation(String targetAudience) {
    this.books = new ArrayList<>();
    this.targetAudience = targetAudience;
  }

  Recommendation(Recommendation target) {
    this.books = new ArrayList<>();
    for (Book book : target.getBooks()) {
      this.books.add(book.clone());
    }
    this.targetAudience = target.getTargetAudience();
  }

  @Override
  public String toString() {
    String returnee = "Target: " + getTargetAudience();
    int i = 1;
    for (Book book : getBooks()) {
      returnee = returnee.concat("\n" + i + ". " + book.getTitle());
      i++;
    }
    return returnee;
  }

  @Override
  public Recommendation clone() {
    return new Recommendation(this);
  }

  public List<Book> getBooks() {
    return books;
  }

  /** Add a book to this recommendation. */
  public void addBook(Book book) {
    books.add(book);
  }

  /** Remove a book from this recommendation. */
  public boolean removeBook(Book book) {
    return books.remove(book);
  }

  public String getTargetAudience() {
    return targetAudience;
  }

  public void setTargetAudience(String targetAudience) {
    this.targetAudience = targetAudience;
  }
}
