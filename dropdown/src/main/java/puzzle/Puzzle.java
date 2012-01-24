package puzzle;

import game.Mouse;
import game.State;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Puzzle extends State {

  final private Color[] colors = {
      Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA,
      Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW,
  };

  final private Color[][] matrix = {
      { sortColor(), sortColor(), sortColor(), sortColor(), sortColor() },
      { sortColor(), sortColor(), sortColor(), sortColor(), sortColor() },
      { sortColor(), sortColor(), sortColor(), sortColor(), sortColor() },
      { sortColor(), sortColor(), sortColor(), sortColor(), sortColor() },
      { sortColor(), sortColor(), sortColor(), sortColor(), sortColor() },
  };

  final int unit = 40;
  final int cols = matrix.length;
  final int rows = matrix[0].length;
  final int hsize = cols * unit;
  final int vsize = rows * unit;

  int xoff;
  int yoff;

  int xover;
  int yover;

  int count;

  private Color sortColor() {
    return colors[(int) (Math.random() * colors.length)];
  }

  @Override
  public void update(int w, int h) {
    super.update(w, h);

    xoff = (w - hsize) / 2;
    yoff = (h - vsize) / 2;

    if (Mouse.consumeLeft()) {
      // hittest
      int col = (getGame().mouseX() - xoff) / unit;
      int row = (getGame().mouseY() - yoff) / unit;

      if (col >= 0 && col < cols) {
        if (row >= 0 && row < rows) {
          while (row > 0) {
            matrix[col][row] = matrix[col][--row];
          }
          matrix[col][row] = sortColor();
        }
      }
    }
  }

  public void render(Graphics2D g, int w, int h) {
    super.render(g, w, h);

    g.setBackground(Color.WHITE);
    g.clearRect(0, 0, w, h);

    // gems
    for (int col = 0; col < cols; col++) {
      for (int row = 0; row < rows; row++) {
        Color color = matrix[col][row];
        if (color != null) {
          int x = xoff + col * unit;
          int y = yoff + row * unit;

          g.setColor(color);
          g.fillRect(x, y, unit, unit);

          g.setColor(Color.WHITE);
          g.setStroke(new BasicStroke(4f));
          g.drawRect(x, y, unit, unit);
        }
      }
    }
  }
}
