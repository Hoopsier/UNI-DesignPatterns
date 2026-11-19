package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/** Controller for the recommendation management view. */
public class RecommendationController {
  @FXML private TextField audienceField;
  @FXML private TextField titleField;
  @FXML private TextField authorField;
  @FXML private TextField genreField;
  @FXML private TextField yearField;
  @FXML private ListView<Recommendation> recommendationList;
  @FXML private ListView<Book> bookList;
  @FXML private Button removeButton;
  @FXML private Label statusLabel;

  /** All recommendations currently held in memory. */
  private final ObservableList<Recommendation> recommendations = FXCollections.observableArrayList();

  /** The recommendation currently being edited. */
  private Recommendation recommendation;

  /** Initializes the view once the FXML has been loaded. */
  @FXML
  private void initialize() {
    recommendationList.setCellFactory(
        list ->
            new ListCell<Recommendation>() {
              @Override
              protected void updateItem(Recommendation item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                  setText(null);
                } else {
                  setText(
                      "#"
                          + (getIndex() + 1)
                          + "  "
                          + item.getTargetAudience()
                          + "  ("
                          + item.getBooks().size()
                          + " book(s))");
                }
              }
            });

    recommendationList.setItems(recommendations);
    recommendationList
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (observable, previous, selected) -> {
              if (selected != null) {
                loadRecommendation(selected);
              }
            });

    recommendation = new Recommendation("General");
    recommendations.add(recommendation);
    recommendationList.getSelectionModel().select(recommendation);

    bookList.setCellFactory(
        list ->
            new ListCell<Book>() {
              @Override
              protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (empty || book == null) {
                  setText(null);
                } else {
                  setText(
                      book.getTitle()
                          + " — "
                          + book.getAuthor()
                          + " ("
                          + book.getPublicationYear()
                          + ")");
                }
              }
            });

    removeButton.disableProperty().bind(bookList.getSelectionModel().selectedItemProperty().isNull());
    refreshBookList();
    setStatus("Ready.", false);
  }

  @FXML
  private void handleAddBook() {
    String title = textOf(titleField);
    String author = textOf(authorField);
    String genre = textOf(genreField);
    String yearText = textOf(yearField);

    if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || yearText.isEmpty()) {
      setStatus("Fill in every field before adding a book.", true);
      return;
    }

    final int publicationYear;
    try {
      publicationYear = Integer.parseInt(yearText);
    } catch (NumberFormatException exception) {
      setStatus("Publication year must be a whole number.", true);
      return;
    }

    if (publicationYear < 1 || publicationYear > 3000) {
      setStatus("Publication year must be between 1 and 3000.", true);
      return;
    }

    Book book = new Book(title, author, genre, publicationYear);
    recommendation.addBook(book);
    refreshBookList();
    recommendationList.refresh();
    bookList.getSelectionModel().select(book);
    clearForm();
    setStatus("Added \"" + title + "\".", false);
  }

  @FXML
  private void handleRemoveBook() {
    Book selected = bookList.getSelectionModel().getSelectedItem();
    if (selected == null) {
      setStatus("Select a book to remove.", true);
      return;
    }

    if (recommendation.removeBook(selected)) {
      refreshBookList();
      recommendationList.refresh();
      setStatus("Removed \"" + selected.getTitle() + "\".", false);
    } else {
      setStatus("That book is not part of this recommendation.", true);
    }
  }

  @FXML
  private void handleUpdateAudience() {
    String audience = textOf(audienceField);
    if (audience.isEmpty()) {
      setStatus("Target audience cannot be empty.", true);
      return;
    }

    recommendation.setTargetAudience(audience);
    recommendationList.refresh();
    setStatus("Target audience changed to \"" + audience + "\".", false);
  }

  @FXML
  private void handleClone() {
    Recommendation clone = recommendation.clone();
    recommendations.add(clone);

    // Selecting the clone loads it into the editor. The original stays in the list.
    recommendationList.getSelectionModel().select(clone);
    setStatus(
        "Cloned \""
            + clone.getTargetAudience()
            + "\" ("
            + clone.getBooks().size()
            + " book(s)). Select a recommendation on the left to load it.",
        false);
  }

  private void loadRecommendation(Recommendation selected) {
    recommendation = selected;
    audienceField.setText(recommendation.getTargetAudience());
    refreshBookList();
    setStatus(
        "Loaded \""
            + recommendation.getTargetAudience()
            + "\" with "
            + recommendation.getBooks().size()
            + " book(s).",
        false);
  }

  private void refreshBookList() {
    bookList.getItems().setAll(recommendation.getBooks());
  }

  private void clearForm() {
    titleField.clear();
    authorField.clear();
    genreField.clear();
    yearField.clear();
    titleField.requestFocus();
  }

  private String textOf(TextField field) {
    return field.getText() == null ? "" : field.getText().trim();
  }

  private void setStatus(String message, boolean error) {
    statusLabel.setText(message);
    statusLabel.setStyle(error ? "-fx-text-fill: #c62828;" : "-fx-text-fill: #2e7d32;");
  }
}
