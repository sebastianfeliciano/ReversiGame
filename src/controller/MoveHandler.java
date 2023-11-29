package controller;

import controller.players.Player;

/**
 * A move handler interface, that handles the move for the player.
 */
public interface MoveHandler {
  void handleMove(Player player, int row, int column);
}
