package application;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.scene.input.KeyCode;

public class Gui extends Application {

  private Controller controller;
  private ColorBox colorBox1;
  private ColorBox colorBox2;
  private ColorBox colorBox3;
  private CheckBox checkBox;

  // Undo history visual
  private Pane historyPane;
  private HBox historyBox;
  private Line historyLine;

  private static final double POINT_SPACING = 30;
  private static final double HISTORY_HEIGHT = 40;
  private static final double HISTORY_WIDTH = 300;

  public void start(Stage stage) {

    controller = new Controller(this);

    Insets insets = new Insets(10, 10, 10, 10);

    colorBox1 = new ColorBox(1, controller);
    colorBox2 = new ColorBox(2, controller);
    colorBox3 = new ColorBox(3, controller);

    checkBox = new CheckBox("Click me!");
    checkBox.setPadding(insets);

    HBox hBox = new HBox(
        colorBox1.getRectangle(),
        colorBox2.getRectangle(),
        colorBox3.getRectangle());
    hBox.setSpacing(10);

    HBox.setMargin(colorBox1.getRectangle(), insets);
    HBox.setMargin(colorBox2.getRectangle(), insets);
    HBox.setMargin(colorBox3.getRectangle(), insets);

    Label label = new Label("Press Ctrl-Z to undo the last change.");
    label.setPadding(insets);

    // Create undo history visual
    historyPane = new Pane();
    historyPane.setPrefHeight(HISTORY_HEIGHT);
    historyPane.setMinHeight(HISTORY_HEIGHT);
    historyPane.setPrefWidth(HISTORY_WIDTH);
    historyPane.setMinWidth(HISTORY_WIDTH);

    historyBox = new HBox(historyPane);
    historyBox.setPadding(insets);

    // Vertical history line
    historyLine = new Line(10, 20, 10, 20);
    historyLine.setStroke(Color.GRAY);
    historyLine.setStrokeWidth(2);

    historyPane.getChildren().add(historyLine);

    // Add the initial history point

    VBox vBox = new VBox(hBox, checkBox, label, historyBox);

    checkBox.setOnAction(event -> {
      controller.setIsSelected(checkBox.isSelected());
      // Empty placeholder for adding history when state changes
      // addHistoryPoint();
    });

    Scene scene = new Scene(vBox);

    // Can not be redeclared, then every declaration fails
    scene.setOnKeyPressed(event -> {
      if (event.isControlDown() && event.getCode() == KeyCode.Z) {
        System.out.println("Undo key combination pressed");

        controller.undo();
      }
      if (event.isControlDown() && event.getCode() == KeyCode.Y) {
        System.out.println("Redo key combination pressed");
        controller.redo();
      }
    });

    reloadHistory();

    stage.setScene(scene);
    stage.setTitle("Memento Pattern Example");
    stage.show();
  }

  // Draw the vertical undo history. Undo/redo only moves the current index, so
  // the points keep their positions until the redo tail is cleared.
  public void reloadHistory() {
    historyPane.getChildren().clear();
    historyPane.getChildren().add(historyLine);

    List<HistoryPoint> history = controller.getHistory();

    double startY = 20;
    double endY = startY;

    for (int i = 0; i < history.size(); i++) {
      HistoryPoint historyPoint = history.get(i);
      double y = startY + i * POINT_SPACING;
      endY = y;

      Circle dot = historyPoint.getDot();
      historyPoint.setPosition(10, y);

      HBox infoBox = historyPoint.getInfoBox();
      infoBox.setLayoutX(25);
      infoBox.setLayoutY(y - 10);

      final int step = i;
      dot.setOnMouseClicked(event -> controller.restore(step));
      infoBox.setOnMouseClicked(event -> controller.restore(step));

      historyPane.getChildren().addAll(dot, infoBox);
    }

    historyLine.setStartX(10);
    historyLine.setStartY(startY);
    historyLine.setEndX(10);
    historyLine.setEndY(endY);

    historyPane.setMinHeight(endY + POINT_SPACING);
    historyPane.setPrefHeight(endY + POINT_SPACING);
    historyPane.setMinWidth(HISTORY_WIDTH);
    historyPane.setPrefWidth(HISTORY_WIDTH);
  }

  public void updateGui() {
    // Called after restoring state from a Memento
    colorBox1.setColor(controller.getOption(1));
    colorBox2.setColor(controller.getOption(2));
    colorBox3.setColor(controller.getOption(3));
    checkBox.setSelected(controller.getIsSelected());
    reloadHistory();
  }
}
