package abstractFactory;

interface Button {
  void display(String text);
}

interface TextField {
  void display(String text);
}

interface CheckBox {
  void display(String text);
}

interface UIFactory {
  Button createButton();

  TextField createTextField();

  CheckBox createCheckBox();
}
