package model;

import controller.players.PlayerType;
import view.Observer;

/**
 * Represents an interface for the board that takes in void methods.
 */
public interface BoardModel extends ReadOnlyBoardModel {
  void flipPieces(int x, int y, PlayerType currentPlayer);

  void playerPass(PlayerType playerType);

  void placePiece(int q, int r, PlayerType type);

  void addObserver(Observer o);

  int getRadius();

  void initializeBoard();

  void ensureValidRadius(int size);

  void resetBlackPassed();

  void resetWhitePassed();

  void switchTurns();

  void notifyObservers();

  void checkGameOver();
}
