package controller;

import controller.players.IPlayer;
import model.BoardModel;
import model.SquareBoard;
import view.ReversiView;

/**
 * The controller for a player to interact with a square board through the view.
 */
public class SquareController extends ReversiController {
  IPlayer player;
  BoardModel board;
  ReversiView view;

  /**
   * The constructor, that sets up the observers and make sure the game isn't over when started.
   * A controller consists of a player, board, and view.
   */
  public SquareController(IPlayer player, BoardModel board, ReversiView view) {
    super(player, board, view);
  }

}
