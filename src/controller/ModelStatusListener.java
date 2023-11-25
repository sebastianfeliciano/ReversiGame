package controller;

import controller.players.PlayerType;

public interface ModelStatusListener {
  void onGameStart();

  void onGameEnd(PlayerType type);

  void onPlayerChanged(PlayerType player);

  void onInvalidMove();

  void onBoardUpdate();
}