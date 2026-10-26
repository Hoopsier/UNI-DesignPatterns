package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Controller {
  private Model model;
  private Gui gui;
  private List<IMemento> history; // Memento history
  private List<IMemento> redoHistory = new ArrayList<>();

  public Controller(Gui gui) {
    this.model = new Model();
    this.gui = gui;
    this.history = new ArrayList<>();
  }

  public void setOption(int optionNumber, int choice) {
    saveToHistory();
    model.setOption(optionNumber, choice);
  }

  public int getOption(int optionNumber) {
    return model.getOption(optionNumber);
  }

  public void setIsSelected(boolean isSelected) {
    saveToHistory();
    model.setIsSelected(isSelected);
  }

  public boolean getIsSelected() {
    return model.getIsSelected();
  }

  public void undo() {
    if (!history.isEmpty()) {
      System.out.println("Memento found in history");
      redoHistory.add(model.createMemento());
      IMemento previousState = history.remove(history.size() - 1);
      model.restoreState(previousState);
      gui.updateGui();
    }
  }

  public void restore(int step) {
    if (history.isEmpty()) {
      return;
    }
    for (int i = history.size(); i >= step; i--) {
      IMemento undone = history.remove(history.size() - 1);
      model.restoreState(undone);
      redoHistory.add(undone);
      gui.updateGui();
    }
  }

  public void redo() {
    if (redoHistory.isEmpty()) {
      return;
    }
    history.add(model.createMemento());
    System.out.println("Memento found in history");
    IMemento redoneMemento = redoHistory.remove(redoHistory.size() - 1);
    model.restoreState(redoneMemento);
    gui.updateGui();
  }

  public void clearRedo() {
    redoHistory.clear();
  }

  private void saveToHistory() {
    IMemento currentState = model.createMemento();
    clearRedo();
    history.add(currentState);
  }

  public List<IMemento> getHistory() {
    List<IMemento> combined = new ArrayList<>();
    combined.addAll(history);
    combined.addAll(redoHistory);
    Collections.sort(combined, new MementoComparator());
    return combined;
  }

}
