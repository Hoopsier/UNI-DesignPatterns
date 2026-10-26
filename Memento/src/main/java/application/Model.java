package application;

public class Model {
  private int[] options = new int[3];
  private boolean isSelected;

  public void setOption(int optionNumber, int choice) {
    System.out.println("optionNumber: " + optionNumber + " choice: " + choice);
    if (optionNumber >= 1 && optionNumber <= 3) {
      options[optionNumber - 1] = choice;
    }
  }

  public int getOption(int optionNumber) {
    if (optionNumber >= 1 && optionNumber <= 3) {
      return options[optionNumber - 1];
    }
    return -1;
  }

  public void setIsSelected(boolean isSelected) {
    System.out.println("isSelected: " + isSelected);
    this.isSelected = isSelected;
  }

  public boolean getIsSelected() {
    return isSelected;
  }

  // method to save the state of the model
  public IMemento createMemento(String title, String action) {
    return new Memento(options, isSelected, title, action);
  }

  // method to restore the state of the model
  public void restoreState(IMemento memento) {
    Memento selectionMemento = (Memento) memento;
    // Clone so later model changes cannot modify the saved memento.
    options = selectionMemento.getOptions().clone();
    System.out.println("options: " + options[0] + " " + options[1] + " " + options[2]);
    isSelected = selectionMemento.isSelected();
    System.out.println("isSelected: " + isSelected);
    System.out.println("State restored");
  }
}
