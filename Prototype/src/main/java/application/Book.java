package application;

/** Book. */
public class Book implements Prototype {
  private String title;
  private String author;
  private String genre;
  private int publicationYear;

  Book(String title, String author, String genre, int publicationYear) {
    setAuthor(author);
    setGenre(genre);
    setPublicationYear(publicationYear);
    setTitle(title);
  }

  Book(Book target) {
    setAuthor(target.getAuthor());
    setGenre(target.getGenre());
    setPublicationYear(target.getPublicationYear());
    setTitle(target.getTitle());
  }

  /** Clone the book. */
  @Override
  public Book clone() {
    Book book = new Book(this);
    return book;
  }

  @Override
  public String toString() {
    return "Author: "
        + getAuthor()
        + ", Title: "
        + getTitle()
        + ", Genre: "
        + getGenre()
        + ", Publication Year: "
        + getPublicationYear();
  }

  public String getAuthor() {
    return author;
  }

  public String getGenre() {
    return genre;
  }

  public int getPublicationYear() {
    return publicationYear;
  }

  public String getTitle() {
    return title;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }

  public void setPublicationYear(int publicationYear) {
    this.publicationYear = publicationYear;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
