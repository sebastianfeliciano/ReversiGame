

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.players.Player;
import controller.players.PlayerType;
import controller.ReversiController;
import model.Board;
import model.ReadOnlyBoardModel;
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

    Player player1 = new Player("Player 1", PlayerType.BLACK, (Board) board);
    Player player2 = new Player("Player 2", PlayerType.WHITE, (Board) board);

    DrawUtils view1 = new DrawUtils(board);
    DrawUtils view2 = new DrawUtils(board);



    JFrame frame1 = new JFrame("Reversi - Player 1");
    JLabel score1 = new JLabel("Black: " + board.getScoreBlack() + " White: " + board.getScoreWhite());
    JLabel playerType = new JLabel("You are " + player1.getColor());
    frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame1.setLayout(new BorderLayout());
    frame1.add(view1, BorderLayout.CENTER);
    frame1.add(score1, BorderLayout.NORTH);
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(playerType);
    frame1.add(bottomPanel, BorderLayout.SOUTH);
    frame1.pack();
    frame1.setLocationRelativeTo(null);
    frame1.setVisible(true);
    ReversiController controller1 = new ReversiController(player1, (Board) board, frame1, score1);
    controller1.setView(view1);
    view1.setEventListener(controller1);



    JFrame frame2 = new JFrame("Reversi - Player 2");
    JLabel player2Type = new JLabel("You are " + player2.getColor());
    JLabel score2 = new JLabel("Black: " + board.getScoreBlack() + " White: " + board.getScoreWhite());
    frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame2.setLayout(new BorderLayout());
    frame2.add(view2, BorderLayout.CENTER);
    frame2.add(score2, BorderLayout.NORTH);
    JPanel bottom2Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottom2Panel.add(player2Type);
    frame2.add(bottom2Panel, BorderLayout.SOUTH);
    frame2.pack();
    frame2.setLocationRelativeTo(null);
    frame2.setVisible(true);
    ReversiController controller2 = new ReversiController(player2, (Board) board, frame2, score2);
    controller2.setView(view2);
    view2.setEventListener(controller2);

  }

}
