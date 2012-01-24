import game.Application;
import game.Game;
import puzzle.Puzzle;

public class Main {

  public static void main(String[] args) {
    Game game = new Game(200, 200);
    game.setEnhancedGraphicsEnabled(true);
    game.setState(new Puzzle());
    game.setFramesRate(60L);
    
    new Application(game).setVisible(true);
    game.start();
  }
}
