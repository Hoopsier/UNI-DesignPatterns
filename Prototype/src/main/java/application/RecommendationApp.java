package application;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** JavaFX application entry point. */
public class RecommendationApp extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader loader =
        new FXMLLoader(RecommendationApp.class.getResource("/application/main-view.fxml"));
    Parent root = loader.load();
    stage.setTitle("Recommendation Manager");
    stage.setScene(new Scene(root, 1120, 600));
    stage.show();
  }
}
