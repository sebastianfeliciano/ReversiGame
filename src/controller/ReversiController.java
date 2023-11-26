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
    System.out.println("Controller instance: " + this);
  }


  public void setView(DrawUtils view) {
    this.view = view;
  }


  @Override
  public void onPlayerMove(int row, int column) {
    if (player instanceof AIPlayer) {
      ((AIPlayer) player).makeMove();
      System.out.println("AI move completed."); // Debugging statement
      view.updateBoard(board);
      board.switchTurns();
      return;
    }

    if (!board.isPlayerTurn(player)) {
      JOptionPane.showMessageDialog(frame, "It is not your turn! It is " + player.getOtherColor() + "'s Turn.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
      return;
    }


    if (!board.isValidMove(row, column, player.getType())) {
      JOptionPane.showMessageDialog(frame, "Invalid move, please try again.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
      return;
    }

    player.makeMove(row, column);
    view.updateBoard(board);
    board.switchTurns();


//    if (board.isGameOver()) {
//      handleGameOver(); // Implement this method to handle game over logic
//    }
  }


  public void updateScore(JLabel scoreLabel) {
    scoreLabel.setText("Black: " + board.getScoreBlack() + " White: " + board.getScoreWhite());
  }


  @Override
  public void onPass() {

  }
}