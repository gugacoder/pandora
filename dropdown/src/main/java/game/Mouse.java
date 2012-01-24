package game;

import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class Mouse {

  static final LinkedList<Integer> index = new LinkedList<Integer>();

  private Mouse() {
  }

  public static boolean button(int code) {
    return index.contains(code);
  }

  public static boolean consumeButton(int code) {
    if (index.contains(code)) {
      index.remove((Integer) code);
      return true;
    }
    return false;
  }

  public static boolean left() {
    return button(MouseEvent.BUTTON1);
  }

  public static boolean middle() {
    return button(MouseEvent.BUTTON2);
  }

  public static boolean right() {
    return button(MouseEvent.BUTTON3);
  }

  public static boolean consumeLeft() {
    return consumeButton(MouseEvent.BUTTON1);
  }

  public static boolean consumeMiddle() {
    return consumeButton(MouseEvent.BUTTON2);
  }

  public static boolean consumeRight() {
    return consumeButton(MouseEvent.BUTTON3);
  }
}
