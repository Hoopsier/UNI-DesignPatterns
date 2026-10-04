package application;

public abstract class Handler {
  protected Handler nextHandler;

  public void setNext(Handler next) {
    nextHandler = next;
  }

  public abstract void handle(Message message);

}
