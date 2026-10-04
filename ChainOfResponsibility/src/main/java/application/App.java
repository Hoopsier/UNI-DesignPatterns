package application;

import application.Message.MessageType;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    Handler handler1 = new ContactRequestHandler();
    Handler handler2 = new CompensationHandler();
    Handler handler3 = new DevSuggestionHandler();
    Handler handler4 = new FeedbackHandler();
    handler1.setNext(handler2);
    handler2.setNext(handler3);
    handler3.setNext(handler4);
    Message message1 = new Message(MessageType.FEEDBACK, "Hi", "loser@winningcorps.omg");
    Message message2 = new Message(MessageType.DEVELOPMENT_SUGGESTION, "Git Gud", "goon@winningcorps.omg");
    Message message3 = new Message(MessageType.COMPENSATION_CLAIM, "Where's my money?", "rat@winningcorps.omg");
    Message message4 = new Message(MessageType.CONTACT_REQUEST, "Hi", "king@winningcorps.omg");
    handler1.handle(message1);
    handler1.handle(message2);
    handler1.handle(message3);
    handler1.handle(message4);

  }
}
