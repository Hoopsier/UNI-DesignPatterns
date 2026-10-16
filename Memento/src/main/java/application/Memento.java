package application;

public class Memento implements IMemento {
  private String title;
  private String action;
  private int[] options;
  private boolean isSelected;

  public Memento(int[] options, boolean isSelected, String title, String action) {
    this.title = title;
    this.action = action;
    this.options = options.clone(); // Copy options array
    this.isSelected = isSelected;
    System.out.println("Memento created");
  }

  public int[] getOptions() {
    return options;
  }

  public boolean isSelected() {
    return isSelected;
  }

  public String getTitle() {
    return title;
  }

  public String getAction() {
    return action;
  }
}
