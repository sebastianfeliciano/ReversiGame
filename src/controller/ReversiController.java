package controller;

import javax.swing.*;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import view.DrawUtils;
import view.PlayerActionListener;

public class ReversiController implements PlayerActionListener {
  private final Player player;
  private Board board;
  private DrawUtils view;
  private JFrame frame;

  public ReversiController(Player player, Board board, JFrame frame) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    this.player = player;
    this.board = board;
    this.frame = frame;
  }


  public void setView(DrawUtils view) {
    this.view = view;
  }


  @Override
  public void onPlayerMove(int row, int column) {
    if (!board.isPlayerTurn(player) || player.getHasPassed()) {
      JOptionPane.showMessageDialog(frame, "It is not your turn! It is " + player.getOtherColor() + "'s Turn.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
      return;
    }


    if (player instanceof AIPlayer) {
      ((AIPlayer) player).makeMove();
      System.out.println("AI move completed."); // Debugging statement
      view.updateBoard(board);
      board.switchTurns();
      return;
    }


    if (!board.isValidMove(row, column, player.getType())) {
      JOptionPane.showMessageDialog(frame, "Invalid move, please try again.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
      return;
    }
    player.makeMove(row, column);
    view.updateBoard(board);
    board.switchTurns();
    player.resetHasPassed();




    if (board.isGameOver()) {
      view.handleGameOver();
    }
  }


  public void updateScore(JLabel scoreLabel) {
    scoreLabel.setText("Black: " + board.getScoreBlack() + " White: " + board.getScoreWhite());
  }


  @Override
  public void onPass() {
//    if (!board.isPlayerTurn(player)) {
//      JOptionPane.showMessageDialog(frame, "It's not your turn!", "Cannot Pass", JOptionPane.ERROR_MESSAGE);
//      return;
//    }
//
//    if (player.getHasPassed()) {
//      JOptionPane.showMessageDialog(frame, "You have already passed. Wait for your next turn.", "Cannot Pass", JOptionPane.ERROR_MESSAGE);
//      return;
//    }
//
//    player.setHasPassed();
//    view.updateBoard(board);
//    board.switchTurns();
//
//    // Assuming you have access to the other player, reset their hasPassed status
//    // otherPlayer.resetHasPassed();
//
//    JOptionPane.showMessageDialog(frame, "You have passed your turn.", "Turn Passed", JOptionPane.PLAIN_MESSAGE);
//  }
  }

}