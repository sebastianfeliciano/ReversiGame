package controller;

import controller.players.PlayerType;

/**
 * A listener interface for the mock controller to understand what is going on.
 */
public interface ModelStatusListener {

  void onPlayerMove(int row, int column);

  void onPass();

  void onGameStart();

  void onGameEnd(PlayerType type);

  void onPlayerChanged(PlayerType player);

  void onInvalidMove();

  void onBoardUpdate();
}
