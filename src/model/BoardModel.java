package model;

import controller.players.PlayerType;

/**
 * Represents an interface for the board that takes in void methods.
 */
public interface BoardModel extends ReadOnlyBoardModel {
  void flipPieces(int x, int y, PlayerType currentPlayer);

  void playerPass(PlayerType playerType);

  void placePiece(int q, int r, PlayerType type);
}
