package application;

public class Message {
  public enum MessageType {
    COMPENSATION_CLAIM,
    CONTACT_REQUEST,
    DEVELOPMENT_SUGGESTION,
    FEEDBACK
  }

  public MessageType messageType;
  public String messageContent;
  public String senderEmail;

  public Message(MessageType type, String content, String email) {
    messageContent = content;
    messageType = type;
    senderEmail = email;
  }
}
