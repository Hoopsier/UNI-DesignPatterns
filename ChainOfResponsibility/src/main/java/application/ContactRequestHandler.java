package application;

import application.Message.MessageType;

public class ContactRequestHandler extends Handler {
  public void handle(Message message) {
    if (message.messageType != MessageType.CONTACT_REQUEST) {
      nextHandler.handle(message);
      return;
    }

    System.out.println("Contact Request sent from: " + message.senderEmail);
  }
}
