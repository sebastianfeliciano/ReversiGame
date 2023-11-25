package controller;

import javax.swing.*;

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
  JLabel scoreLabel;

  public ReversiController(Player player, Board board, JFrame frame, JLabel scoreLabel) {
    this.player = player;
    this.board = board;
    this.frame = frame;
    this.scoreLabel = scoreLabel;

  }

  public void setView(DrawUtils view) {
    this.view = view;
    //this.view.setEventListener(this); // Uncomment this if needed
  }


  @Override
  public void onPlayerMove(int row, int column) {
    if (!board.isPlayerTurn(player)) {
      return;
    }


    if (!board.isValidMove(row, column, player.getType())) {
      JOptionPane.showMessageDialog(frame, "Invalid move, please try again.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
      return;
    }

    player.makeMove(row, column);

    board.switchTurns();

    view.updateBoard(board);
    updateScore(scoreLabel);


    if (board.isGameOver()) {
      System.out.println("kjklk");
    }
  }

  public void updateScore(JLabel scoreLabel) {
    scoreLabel.setText("Black: " + board.getScoreBlack() + " White: " + board.getScoreWhite());
  }

  @Override
  public void onPass() {

  }
}