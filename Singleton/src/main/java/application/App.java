package application;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    Logger logger = Logger.getInstance();
    logger.setFileName("dweeb64.log");
    logger.write("Dweeb alert!");
    logger.write("Dweeb alert!");
    logger.write("Dweeb alert!");
    logger.close();
  }
}

class Logger {
  static Logger instance = null;
  List<String> lines = new ArrayList<>();
  File file = new File("log.txt");

  public static Logger getInstance() {
    if (instance == null) {
      instance = new Logger();
    }
    return instance;
  }

  public void setFileName(String name) {
    File rename = new File(name);
    boolean flag = file.renameTo(rename);
    if (!flag) {
      System.err.println("something failed in renaming");
    }
  }

  public void write(String text) {
    lines.add(text + '\n');
  }

  public void close() {
    try {
      String writtenText = "";
      for (String string : lines) {
        writtenText += string;
      }
      FileWriter writer = new FileWriter(file);
      writer.write(writtenText);
      writer.close();
    } catch (Exception e) {
      // TODO: handle exception
    }
    try {
      file.createNewFile();
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
