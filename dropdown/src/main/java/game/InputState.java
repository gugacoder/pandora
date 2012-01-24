package game;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class InputState implements AWTEventListener {

  private static InputState instance;

  private boolean binding;

  private final Toolkit toolkit;

  private InputState() {
    this.toolkit = Toolkit.getDefaultToolkit();
  }

  public static InputState getInstance() {
    if (instance == null) {
      instance = new InputState();
    }
    return instance;
  }

  public boolean isBinding() {
    return binding;
  }

  public void setBinding(boolean value) {
    if (value) {
      if (!isBinding()) {
        toolkit.addAWTEventListener(this,
            AWTEvent.KEY_EVENT_MASK + AWTEvent.MOUSE_EVENT_MASK);
      }
    } else {
      if (isBinding()) {
        toolkit.removeAWTEventListener(this);
      }
    }
    this.binding = value;
  }

  @Override
  public void eventDispatched(AWTEvent event) {
    if (event instanceof MouseEvent) {
      onMouse((MouseEvent) event);
    } else if (event instanceof KeyEvent) {
      onKey((KeyEvent) event);
    }
  }

  private void onKey(KeyEvent event) {
    if (event.getID() == KeyEvent.KEY_PRESSED) {
      if (!Keys.index.contains(event.getKeyCode())) {
        Keys.index.add((Integer) event.getKeyCode());
      }
    } else if (event.getID() == KeyEvent.KEY_RELEASED) {
      if (Keys.index.contains(event.getKeyCode())) {
        Keys.index.remove((Integer) event.getKeyCode());
      }
    }
  }

  private void onMouse(MouseEvent event) {
    if (event.getID() == MouseEvent.MOUSE_PRESSED) {
      if (!Mouse.index.contains(event.getButton())) {
        Mouse.index.add((Integer) event.getButton());
      }
    } else if (event.getID() == MouseEvent.MOUSE_RELEASED) {
      if (Mouse.index.contains(event.getButton())) {
        Mouse.index.remove((Integer) event.getButton());
      }
    }
  }

}
