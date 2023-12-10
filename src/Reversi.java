import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Command;
import controller.players.IPlayer;
import controller.ReversiController;
import model.BoardModel;
import view.DrawSquareUtils;
import view.FrameSetup;
import view.Game;
import view.HintKeyToggle;
import view.DrawUtilsWithHints;
import view.HintSystem;
import view.ReversiView;

/**
 * Represents the Game.
 */
public class Reversi {

  /**
   * Entry point for Reversi.
   */
  public static void main(String[] args) {

    Command commandLine = new Command(args);
    BoardModel board = commandLine.getBoard();
    String boardType = commandLine.getBoardType();
    IPlayer player1 = commandLine.getPlayer1();
    IPlayer player2 = commandLine.getPlayer2();

    ReversiView view1;
    ReversiView view2;

    HintSystem hintSystem = new HintSystem();

    if ("square".equals(boardType)) {
      view1 = new DrawSquareUtils(board);
      view2 = new DrawSquareUtils(board);
    } else {
      view1 = new DrawUtilsWithHints(board, hintSystem, player1.getType());
      view2 = new DrawUtilsWithHints(board, hintSystem, player2.getType());
    }
    ReversiController controller1 = new ReversiController(player1, board, view1);
    player1.setMoveHandler(controller1);
    ReversiController controller2 = new ReversiController(player2, board, view2);
    player2.setMoveHandler(controller2);

    JFrame frame1 = new JFrame("Reversi - Player 1");
    JFrame frame2 = new JFrame("Reversi - Player 2");


    JLabel frame1Setup = FrameSetup.setupFrame(frame1, view1,
            "You are Player " + player1.getColor());
    JLabel frame2Setup = FrameSetup.setupFrame(frame2, view2,
            "You are Player " + player2.getColor());

    view1.setScoreLabel(frame1Setup);
    view2.setScoreLabel(frame2Setup);

    view1.setEventListener(controller1);
    view2.setEventListener(controller2);


    HintKeyToggle hinterForPlayer1 = new HintKeyToggle(hintSystem, player1.getType(), view1);
    HintKeyToggle hinterForPlayer2 = new HintKeyToggle(hintSystem, player2.getType(), view2);

    view1.addKeyListener(hinterForPlayer1);
    view1.setFocusable(true);
    view1.requestFocusInWindow();

    view2.addKeyListener(hinterForPlayer2);
    view2.setFocusable(true);
    view2.requestFocusInWindow();

    frame1.setVisible(true);
    frame2.setVisible(true);


    Game model = new Game(controller1, controller2, board);
    model.start();
  }
}








