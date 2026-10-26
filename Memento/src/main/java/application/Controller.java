package application;

import java.util.ArrayList;
import java.util.List;

public class Controller {
  private Model model;
  private Gui gui;
  private List<HistoryPoint> history = new ArrayList<>();
  private int currentStep; // index of the state currently shown by the model

  public Controller(Gui gui) {
    this.model = new Model();
    this.gui = gui;
    // The initial state is the first point in the timeline.
    history.add(new HistoryPoint(model.createMemento("Root", "Start point")));
    currentStep = 0;
  }

  public void setOption(int optionNumber, int choice) {
    model.setOption(optionNumber, choice);
    saveToHistory("Square", "Color " + choice);
    gui.updateGui();
  }

  public int getOption(int optionNumber) {
    return model.getOption(optionNumber);
  }

  public void setIsSelected(boolean isSelected) {
    model.setIsSelected(isSelected);
    saveToHistory("Checkbox", "Checked " + isSelected);
    gui.updateGui();
  }

  public boolean getIsSelected() {
    return model.getIsSelected();
  }

  public void undo() {
    if (currentStep == 0) {
      return;
    }
    currentStep--;
    model.restoreState(history.get(currentStep).getMemento());
    gui.updateGui();
  }

  public void redo() {
    if (currentStep >= history.size() - 1) {
      return;
    }
    currentStep++;
    model.restoreState(history.get(currentStep).getMemento());
    gui.updateGui();
  }

  // Used when clicking a point in the history timeline.
  public void restore(int step) {
    if (step < 0 || step >= history.size() || step == currentStep) {
      return;
    }
    currentStep = step;
    model.restoreState(history.get(currentStep).getMemento());
    gui.updateGui();
  }

  // Removes the states after the current point. Called when a new change is made.
  public void clearRedo() {
    if (currentStep < history.size() - 1) {
      history.subList(currentStep + 1, history.size()).clear();
    }
  }

  private void saveToHistory(String title, String action) {
    clearRedo();
    history.add(new HistoryPoint(model.createMemento(title, action)));
    currentStep = history.size() - 1;
  }

  public List<HistoryPoint> getHistory() {
    return List.copyOf(history);
  }

  public int getCurrentStep() {
    return currentStep;
  }

}
