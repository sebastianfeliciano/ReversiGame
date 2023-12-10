package model;


import controller.players.IPlayer;
import controller.players.PlayerType;

import java.util.List;

/**
 * Creates a read only copy board.
 */
public interface ReadOnlyBoardModel {

  int getScoreWhite();

  int getScoreBlack();

  int getBoardSize();

  boolean isGameOver();

  boolean isBoardFull();

  BoardModel deepCopy();

  int countPieces(PlayerType type);

  boolean hasPlayerPassed(PlayerType type);

  Shape getCurrentHex(int row, int column);

  boolean isValidMove(int x, int y, PlayerType playerType);

  boolean isValidCoordinate(int q, int r);

  List<Move> getValidMovesWithCaptures(IPlayer player);

  boolean isCornerMove(Move move, int boardSize);

  int getMidPoint();

  Board getRegularBoard();

  PlayerType getCurrentTurn();

  boolean isPlayerTurn(IPlayer player);
}
