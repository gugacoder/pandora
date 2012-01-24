package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class EmptyState extends State {

  private Font titleFont;
  private Font labelFont;

  public EmptyState() {
    titleFont = new Font("Serif", Font.BOLD, 34);
    labelFont = new Font("Serif", Font.PLAIN, 12);
  }

  @Override
  public void render(Graphics2D g, int w, int h) {
    g.setBackground(Color.WHITE);
    g.clearRect(0, 0, w, h);

    drawText(g, w, h, 0, 20, titleFont, Color.BLACK, "AWT Game Engine");
    drawText(g, w, h, 0, 30, labelFont, Color.GRAY, "The only engine you need");
  }

  private void drawText(Graphics2D g, int w, int h,
      int xoff, int yoff, Font font, Color color, String text) {
    g.setFont(font);
    g.setColor(color);

    Rectangle2D bounds = font.getStringBounds(text,
        g.getFontRenderContext());
    int x = (int) ((w - bounds.getWidth()) / 2);
    int y = (int) ((h - bounds.getHeight()) / 2);
    g.drawString(text, x + xoff, y + yoff);
  }
}
