package game;

import java.util.LinkedList;

public class Keys {

  static final LinkedList<Integer> index = new LinkedList<Integer>();

  private Keys() {
  }

  public static boolean key(int code) {
    return index.contains(code);
  }
}
