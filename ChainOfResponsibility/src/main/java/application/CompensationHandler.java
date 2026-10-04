package application;

import application.Message.MessageType;

public class CompensationHandler extends Handler {
  public void handle(Message message) {
    if (message.messageType != MessageType.COMPENSATION_CLAIM) {
      nextHandler.handle(message);
      return;
    }

    System.out.println("Compensation Claim sent from: " + message.senderEmail);
  }
}
