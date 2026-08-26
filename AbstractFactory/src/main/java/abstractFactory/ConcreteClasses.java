package abstractFactory;

class ButtonA implements Button {
  public void display(String text) {
    int length = text.length();
    ConcreteClasses.printSymbolFor(length, '_');
    System.out.println("|" + text.trim() + "|");
    ConcreteClasses.printSymbolFor(length, '_');
  }
}

class ButtonB implements Button {
  public void display(String text) {
    int length = text.length();
    ConcreteClasses.printSymbolFor(length, '<');
    System.out.println("v" + text.trim() + "^");
    ConcreteClasses.printSymbolFor(length, '>');
  }
}

class TextFieldA implements TextField {
  public void display(String text) {
    System.out.println(text + ':');
  }
}

class TextFieldB implements TextField {
  public void display(String text) {
    System.out.println(text + '>');
  }
}

class CheckBoxA implements CheckBox {
  public void display(String text) {
    System.out.println(text + " []");
  }
}

class CheckBoxB implements CheckBox {
  public void display(String text) {
    System.out.println(text + " | |");
  }
}

class UIFactoryA implements UIFactory {
  public Button createButton() {
    return new ButtonA();
  }

  public TextField createTextField() {
    return new TextFieldA();
  }

  public CheckBox createCheckBox() {
    return new CheckBoxA();
  }
}

class UIFactoryB implements UIFactory {
  public Button createButton() {
    return new ButtonB();
  }

  public TextField createTextField() {
    return new TextFieldB();
  }

  public CheckBox createCheckBox() {
    return new CheckBoxB();
  }
}

public class ConcreteClasses {
  static void printSymbolFor(int length, char character) {
    for (int i = 0; i < length + 2; i++) {
      System.out.print(character);
    }
    System.out.println();
  }
}
