import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Command;
import controller.players.Player;
import controller.ReversiController;
import model.Board;
import model.ReadOnlyBoardModel;
import view.DrawUtils;

/**
 * Represents the GUI view.
 */
public class Reversi {

  /**
   * Entry point for GUI.
   */
  public static void main(String[] args) {
    Command commandLine = new Command();
    commandLine.prompt();

    ReadOnlyBoardModel board = commandLine.getBoard();
    DrawUtils view1 = new DrawUtils(board);
    DrawUtils view2 = new DrawUtils(board);

    Player player1 = commandLine.getPlayer1();
    Player player2 = commandLine.getPlayer2();


    JFrame frame1 = new JFrame("Reversi - Player 1");
    ReversiController controller1 = new ReversiController(player1, (Board) board, frame1);
    controller1.setView(view1);
    setupFrame(frame1, view1, "You are Player " + player1.getColor());
    view1.setEventListener(controller1);

    JFrame frame2 = new JFrame("Reversi - Player 2");
    ReversiController controller2 = new ReversiController(player2, (Board) board, frame2);
    controller2.setView(view2);
    setupFrame(frame2, view2, "You are Player " + player2.getColor());
    view2.setEventListener(controller2);

    ((Board) board).addObserver(view1);
    ((Board) board).addObserver(view2);
    commandLine.close();
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
