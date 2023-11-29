package controller;

import controller.players.Player;
import model.Board;
import view.DrawUtils;

public class MockController extends ReversiController implements Features {
  private StringBuilder log;

  /**
   * The constructor, that sets up the observers and make sure the game isn't over when started.
   * A controller consists of a player, board, and view.
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

  public String toString() {
    return this.getLog().toString();
  }
}
