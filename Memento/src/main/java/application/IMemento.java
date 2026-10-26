package application;

import java.util.Date;

public interface IMemento {
  // memento metadata methods
  public String getTitle();

  public String getAction();

  public Date getTimestamp();
}
