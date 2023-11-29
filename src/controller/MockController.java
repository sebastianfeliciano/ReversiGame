package controller;

import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import view.DrawUtils;

public class MockController extends ReversiController implements Features
        //, ModelStatusListener
        {
  ReversiController controller;
  Board board;
  private StringBuilder log;


  /**
   * The constructor, that sets up the observers and make sure the game isn't over when started.
   * A controller consists of a player, board, and view.
   *
   * @param player
   * @param board
   * @param view
   */
  public MockController(Player player, Board board, DrawUtils view) {
    super(player, board, view);
    this.log = new StringBuilder();
  }

  @Override
  public void onPlayerMove(int row, int column) {
    this.getLog()
            .append("Player moved to row: ")
            .append(row).append(", column: ").append(column).append("\n").toString();
    super.onPlayerMove(row, column);
  }

  @Override
  public void onPass() {
    this.getLog().append("Player passed. \n").toString();
    super.onPass();
  }

  public StringBuilder getLog() {
    return this.log;
  }


//  @Override
//  public void onGameEnd(PlayerType type) {
//    if (board.isGameOver()) {
//      log.append("Game over!");
//    }
//  }
//
//  @Override
//  public void onPlayerChanged(PlayerType player) {
//    log.append("Player ").append(player).append("changed.");
//  }
//
//  @Override
//  public void onInvalidMove() {
//    if (board.isValidMove(11, 11, PlayerType.WHITE)) {
//      log.append("Invalid move");
//      super.;
//    }
//  }
//
//  @Override
//  public void onBoardUpdate() {
//    log.append("Board updated");
//    super.update();
//  }

  public String toString(){
    return this.getLog().toString();
  }

}
