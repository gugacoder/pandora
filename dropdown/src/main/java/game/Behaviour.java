package game;

import java.awt.Graphics2D;

public interface Behaviour {

  void welcome();

  void update(int w, int h);

  void render(Graphics2D g, int w, int h);

  void bye();

}
