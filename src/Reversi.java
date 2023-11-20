

import java.awt.*;
import javax.swing.*;

import controller.Player;
import controller.PlayerType;
import controller.ReversiController;
import model.Board;
import model.ReadOnlyBoardModel;
import view.DrawUtils;

/**
 * Represents the GUI view.
 */
public class Reversi {
  static ReadOnlyBoardModel board = new Board(7);

  /**
   * Entry point for GUI.
   */
  public static void main(String[] args) {
    Board board = new Board();
    Board board2 = new Board();

    Player player1 = new Player("", PlayerType.BLACK, board);
    Player player2 = new Player("", PlayerType.BLACK, board2);

    DrawUtils view1 = new DrawUtils(board);
    DrawUtils view2 = new DrawUtils(board2);

    ReversiController controller1 = new ReversiController(player1, board, view1);
    ReversiController controller2 = new ReversiController(player2, board2, view2);

    JFrame frame = new JFrame("Reversi");
    JLabel score = new JLabel("Black: "
            + board.getScoreBlack() + " White: " + board.getScoreWhite());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel contentPane = new JPanel(new FlowLayout());
    contentPane.add(view1);
    contentPane.add(view2);

    frame.setContentPane(contentPane);
    frame.add(score, BorderLayout.NORTH);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

}
