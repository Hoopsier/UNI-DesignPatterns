package application;

import application.Message.MessageType;

public class FeedbackHandler extends Handler {
  public void handle(Message message) {
    if (message.messageType != MessageType.FEEDBACK) {
      nextHandler.handle(message);
      return;
    }

    System.out.println("Feedback sent from: " + message.senderEmail);
  }
}
