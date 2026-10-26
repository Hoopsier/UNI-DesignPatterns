package application;

import java.util.Comparator;
import java.util.Date;

public class Memento implements IMemento, Comparator<IMemento> {
  private String title;
  private String action;
  private int[] options;
  private boolean isSelected;
  private Date timestamp;

  public Memento(int[] options, boolean isSelected, String title, String action) {
    timestamp = new Date();
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

  public Date getTimestamp() {
    return timestamp;
  }

  @Override
  public int compare(IMemento o1, IMemento o2) {
    return o1.getTimestamp().compareTo(o2.getTimestamp());
  }

}

// Source - https://stackoverflow.com/a/2784576
// Posted by Michael Myers, modified by community. See post 'Timeline' for
// change history
// Retrieved 2026-10-22, License - CC BY-SA 3.0

class MementoComparator implements Comparator<IMemento> {
  @Override
  public int compare(IMemento o1, IMemento o2) {
    return o1.getTimestamp().compareTo(o2.getTimestamp());
  }
}
