import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Command;
import controller.players.Player;
import controller.ReversiController;
import model.Board;
import model.GameObserver;
import view.DrawUtils;
import view.FrameSetup;

/**
 * Represents the GUI view.
 */
public class Reversi implements GameObserver {

  private JLabel scoreLabel1; // Score label for first frame
  private JLabel scoreLabel2; // Score label for second frame

  /**
   * Entry point for GUI.
   */
  public static void main(String[] args) {

    Command commandLine = new Command();
    commandLine.prompt();

    Board board = commandLine.getBoard();

    DrawUtils view1 = new DrawUtils(board);
    DrawUtils view2 = new DrawUtils(board);

    Player player1 = commandLine.getPlayer1();
    Player player2 = commandLine.getPlayer2();


    String score = "Black: "+board.getScoreBlack()+" White: "+ board.getScoreWhite();

    JFrame frame1 = new JFrame("Reversi - Player 1");
    ReversiController controller1 = new ReversiController(player1, board, view1);
    player1.setMoveHandler(controller1);
    FrameSetup.setupFrame(frame1, view1, "You are Player " + player1.getColor(), score);
    view1.setEventListener(controller1);

    JFrame frame2 = new JFrame("Reversi - Player 2");
    ReversiController controller2 = new ReversiController(player2, board, view2);
    player2.setMoveHandler(controller2);
    FrameSetup.setupFrame(frame2, view2, "You are Player " + player2.getColor(), score);
    view2.setEventListener(controller2);

    Game model = new Game(controller1, controller2, board);
    model.start();
    commandLine.close();
  }
}
