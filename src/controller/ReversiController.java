package controller;

import javax.swing.*;

import model.Board;
import view.DrawUtils;
import view.ModelStatusInterface;
import view.PlayerActionListener;

public class ReversiController implements PlayerActionListener {
  private Player player;
  private Board board;
  private DrawUtils view;
  private JFrame frame;

  public ReversiController(Player player, Board board, DrawUtils view, JFrame frame) {
    this.player = player;
    this.board = board;
    this.view = view;
    this.frame = frame;
    this.view.setEventListener(this);

  }

  public void registerListeners(DrawUtils view) {

  }

  //@Override
  public void onGameStart() {

  }

  //@Override
  public void onGameEnd(PlayerType type) {

  }

  //@Override
  public void onPlayerChanged(PlayerType current) {
    if (current == player.getType()) {
      //view.updateBoard(current.getModel().getBoard());
    }
  }

  //@Override
  public void onInvalidMove() {

  }

  @Override
  public void onPlayerMove(int row, int column) {
    if (!board.isPlayerTurn(player)) {
      return;
    }
    // Validate the move
    if (!board.isValidMove(row, column, player.getType())) {
      JOptionPane.showMessageDialog(frame, "Invalid move, please try again.", "Invalid Move", JOptionPane.ERROR_MESSAGE);
      return;
    }
    //Player will make move
    player.makeMove(row, column);

    //Game State will switch the currentTurn
    board.switchTurns();

    view.updateBoard(board);
    if (board.isGameOver()) {
      System.out.println("kjklk");
    }
  }

  @Override
  public void onPass() {

  }

//  //@Override
//  public void onBoardUpdate() {
//
//  }
//
//  @Override
//  public void onPlayerMove(int row, int column) {
////        try {
////            player.placeKey(row, column);
////        } catch (InvalidMoveException e) {
////            handleInvalidMove(e);
////        }
//  }
//
//  @Override
//  public void onPass() {
////        try {
////            player.setHasPassed();
////        } catch (InvalidMoveException e) {
////            handleInvalidmove(e);
////        }
//  }
}