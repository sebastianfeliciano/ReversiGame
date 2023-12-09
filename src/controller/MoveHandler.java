package controller;

import controller.players.IPlayer;

/**
 * A move handler interface, that handles the move for the player.
 */
public interface MoveHandler {

  /**
   * A move handler that signals the controller to handle the move.
   */
  void handleMove(IPlayer player, int row, int column);
}
