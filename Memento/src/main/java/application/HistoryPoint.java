package application;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * A single entry in the undo/redo history.
 *
 * Each point owns its memento, the dot on the vertical timeline, and an HBox
 * that will later show the point's details.
 */
public class HistoryPoint {
  private final IMemento memento;
  private final Circle dot;
  private final HBox infoBox;
  private final Label titleLabel;
  private final Label actionLabel;
  private final Label timestamp;

  public HistoryPoint(IMemento memento) {
    this.memento = memento;
    this.dot = new Circle(5, Color.ORANGERED);

    // Empty for now; these will hold the history point details later.
    titleLabel = new Label(memento.getTitle());
    actionLabel = new Label(memento.getAction());
    timestamp = new Label(memento.getTimestamp().toString());
    infoBox = new HBox(10, titleLabel, new Label(":"), actionLabel, new Label("|"), timestamp);
  }

  public IMemento getMemento() {
    return memento;
  }

  public Circle getDot() {
    return dot;
  }

  public HBox getInfoBox() {
    return infoBox;
  }

  public Label getTitleLabel() {
    return titleLabel;
  }

  public Label getActionLabel() {
    return actionLabel;
  }

  public void setPosition(double x, double y) {
    dot.setCenterX(x);
    dot.setCenterY(y);
  }
}
