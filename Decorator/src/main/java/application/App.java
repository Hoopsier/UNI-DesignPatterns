package application;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class App {
  public static void main(String[] args) {
    Printer printer = new EncryptedPrinter(new XMLPrinter(new BasicPrinter()));
    printer.print("Sweese");
  }
}

interface Printer {
  public void print(String message);
}

class BasicPrinter implements Printer {
  protected Printer printer;

  public void print(String message) {

    System.out.println(message);
  }
}

class XMLPrinter extends BasicPrinter {

  public XMLPrinter(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void print(String message) {
    printer.print("<message>\n " + message + "\n</message>");
  }
}

class EncryptedPrinter extends BasicPrinter {

  public EncryptedPrinter(Printer printer) {
    this.printer = printer;
  }

  @Override
  public void print(String message) {
    try {
      // Generating objects of KeyGenerator &
      // SecretKey
      KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
      SecretKey myDesKey = keygenerator.generateKey();

      // Creating object of Cipher
      Cipher desCipher;
      desCipher = Cipher.getInstance("DES");

      // Creating byte array to store string
      byte[] text = message.getBytes("UTF8");

      // Encrypting text
      desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
      byte[] textEncrypted = desCipher.doFinal(text);

      // Converting encrypted byte array to string
      String s = new String(textEncrypted);
      System.out.println(s);

      // Decrypting text
      desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
      byte[] textDecrypted = desCipher.doFinal(textEncrypted);

      // Converting decrypted byte array to string
      s = new String(textDecrypted);
      printer.print(s);

    } catch (Exception e) {
      System.out.println("Exception");
    }
  }
}
