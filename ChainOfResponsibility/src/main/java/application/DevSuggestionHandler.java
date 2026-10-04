
package application;

import application.Message.MessageType;

public class DevSuggestionHandler extends Handler {
  public void handle(Message message) {
    if (message.messageType != MessageType.DEVELOPMENT_SUGGESTION) {
      nextHandler.handle(message);
      return;
    }

    System.out.println("Development Suggestion sent from: " + message.senderEmail);
  }
}
