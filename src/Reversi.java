import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Command;
import controller.players.Player;
import controller.ReversiController;
import model.Board;
import model.BoardModel;
import model.ReadOnlyBoardModel;
import view.DrawUtils;
import view.FrameSetup;

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

    BoardModel board = commandLine.getBoard();

    DrawUtils view1 = new DrawUtils(board);
    DrawUtils view2 = new DrawUtils(board);

    Player player1 = commandLine.getPlayer1();
    Player player2 = commandLine.getPlayer2();


    JFrame frame1 = new JFrame("Reversi - Player 1");
    ReversiController controller1 = new ReversiController(player1, (Board) board, view1);
    FrameSetup.setupFrame(frame1, view1, "You are Player " + player1.getColor());
    view1.setEventListener(controller1);

    JFrame frame2 = new JFrame("Reversi - Player 2");
    ReversiController controller2 = new ReversiController(player2, (Board) board, view2);
    FrameSetup.setupFrame(frame2, view2, "You are Player " + player2.getColor());
    view2.setEventListener(controller2);

    board.addObserver(view1);
    board.addObserver(view2);
    commandLine.close();
  }



}
