package controller;

import controller.players.Player;

public interface MoveHandler {
  void handleMove(Player player, int row, int column);
}
