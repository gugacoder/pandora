package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Application extends JFrame {

  public Application(Game game) {
    setTitle("Game");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());
    add(game, BorderLayout.CENTER);

    pack();

    Dimension ss = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(
        (int) (ss.getWidth() - getWidth()) / 2,
        (int) (ss.getHeight() - getHeight()) / 2,
        (int) getWidth(),
        (int) getHeight());
    
    setResizable(false);
  }
}
