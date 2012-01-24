package game;

import java.awt.Dimension;
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable {

  private final State defaultState;

  private Thread thread;
  private long frameRate = 30L;
  private State state;

  private int mouseX;
  private int mouseY;

  public Game() {
    this.defaultState = new EmptyState();
    InputState.getInstance().setBinding(true);
  }

  public Game(int w, int h) {
    this();
    setBounds(0, 0, w, h);
    setPreferredSize(new Dimension(w, h));
  }

  public int mouseX() {
    if (getMousePosition() == null)
      return mouseX;
    return mouseX = getMousePosition().x;
  }

  public int mouseY() {
    if (getMousePosition() == null)
      return mouseY;
    return mouseY = getMousePosition().y;
  }

  public long getFramesRate() {
    return frameRate;
  }

  public void setFramesRate(long value) {
    this.frameRate = value;
  }

  public State getState() {
    return state != null ? state : defaultState;
  }

  public void setState(State state) {
    this.state = state;
    this.state.setParent(this);
  }

  @Override
  public void update(Graphics2D g, int w, int h) {
    getState().prepare();
    getState().update(w, h);
    getState().cleanup();
    getState().render(g, w, h);
  }

  public void start() {
    if (thread == null) {
      thread = new Thread(this, "Game");
      thread.setPriority(Thread.MIN_PRIORITY);
      thread.start();
    }
  }

  public synchronized void stop() {
    if (thread != null) {
      thread.interrupt();
    }
    thread = null;
    notifyAll();
  }

  public void run() {
    Thread current = Thread.currentThread();

    while (thread == current && !isShowing() || getSize().width == 0) {
      try {
        Thread.sleep(200L);
      } catch (InterruptedException e) {
      }
    }

    long frameCost = 1000L / frameRate;
    while (thread == current) {
      repaint();
      try {
        long checkpoint = System.currentTimeMillis();

        long waitTime = checkpoint + frameCost - System.currentTimeMillis();
        if (waitTime > 0) {
          Thread.sleep(waitTime);
        }
      } catch (InterruptedException e) {
      }
    }
    thread = null;
  }
}
