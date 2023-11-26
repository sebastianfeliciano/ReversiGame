import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Command;
import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import controller.ReversiController;
import model.Board;
import model.ReadOnlyBoardModel;
import model.strategies.CaptureStrategy;
import model.strategies.GoForCornersStrategy;
import model.strategies.IStrategy;
import view.DrawUtils;

/**
 * Represents the GUI view.
 */
public class Reversi {

  static ReadOnlyBoardModel board = new Board(11);
  static JLabel l;
  /**
   * Entry point for GUI.
   */
  public static void main(String[] args) {
    Command commandLine = new Command();
    commandLine.prompt();

    ReadOnlyBoardModel board = new Board(commandLine.getBoardSize());

    Player humanPlayer = new Player("Player 1", PlayerType.BLACK, (Board) board);
    AIPlayer aiPlayer = new AIPlayer("AI", PlayerType.WHITE, (Board) board, new CaptureStrategy());

    // Setup for human player
    JFrame frame1 = new JFrame("Reversi - Player 1");
    DrawUtils view1 = new DrawUtils(board);
    ReversiController controller1 = new ReversiController(humanPlayer, (Board) board, frame1);
    controller1.setView(view1);
    setupFrame(frame1, view1, "You are Player " + humanPlayer.getColor());
    view1.setEventListener(controller1);

    // Setup for AI player
    JFrame frame2 = new JFrame("Reversi - Player 2");
    DrawUtils view2 = new DrawUtils(board);
    ReversiController controller2 = new ReversiController(aiPlayer, (Board) board, frame2);
    controller2.setView(view2);
    setupFrame(frame2, view2, "You are Player " + aiPlayer.getColor());
    view2.setEventListener(controller2);

    ((Board) board).addObserver(view1);
    ((Board) board).addObserver(view2);

  }

  private static void setupFrame(JFrame frame, DrawUtils view, String playerTypeLabel) {
    JLabel scoreLabel = new JLabel();
    JLabel playerType = new JLabel(playerTypeLabel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(view, BorderLayout.CENTER);
    frame.add(scoreLabel, BorderLayout.NORTH);
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(playerType);
    frame.add(bottomPanel, BorderLayout.SOUTH);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }


}
