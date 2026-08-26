package abstractFactory;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    UIFactory[] factories = { new UIFactoryA(), new UIFactoryB() };
    String text = "Me is text";
    for (UIFactory factory : factories) {
      Button btn = factory.createButton();
      TextField tf = factory.createTextField();
      CheckBox cb = factory.createCheckBox();
      btn.display(text);
      tf.display(text);
      cb.display(text);
    }
  }
}
