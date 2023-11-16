package model;

import controller.IPlayer;
import controller.Player;
import controller.PlayerType;
import org.yaml.snakeyaml.events.MappingEndEvent;

import java.util.List;

public class Mock extends Board
        implements ReadOnlyBoardModel, BoardModel {
  List<Move> validMoves;
  StringBuilder log;
  ReadOnlyBoardModel board;

  public Mock(ReadOnlyBoardModel board, List<Move> validMoves, StringBuilder log) {
    this.validMoves = validMoves;
    this.board = board;
    this.log = log;
  }

  @Override
  public int getScoreWhite() {
    log.append("Getting white player's score: ").append(board.getScoreWhite());
    return board.getScoreWhite();
  }

  @Override
  public int getScoreBlack() {
    log.append("Getting black player's score: ").append(board.getScoreBlack());
    return board.getScoreBlack();
  }

  @Override
  public int getBoardSize() {
    log.append("Getting board size: ").append(board.getBoardSize());
    return board.getBoardSize();
  }

  @Override
  public boolean isGameOver() {
    if (board.isGameOver()) {
      log.append("Game is over.");
    } else {
      log.append("Game is still running.");
    }
    return board.isGameOver();
  }

  @Override
  public boolean isBoardFull() {
    if (board.isBoardFull()) {
      log.append("The board is full.");
    } else {
      log.append("The board is not full.");
    }
    return board.isBoardFull();
  }

  @Override
  public int countPieces(PlayerType type) {
    if (type == null) {
      throw new IllegalArgumentException("PlayerType cannot be null");
    }
    int piecesCount = board.countPieces(type);
    log.append("Counting pieces for ").append(type).append(": ").append(piecesCount);
    return piecesCount;
  }

  @Override
  public boolean hasPlayerPassed(PlayerType type) {
    if (board.hasPlayerPassed(type)) {
      log.append("Player has passed");
    } else {
      log.append("Player has not passed.");
    }
    return board.hasPlayerPassed(type);
  }

  @Override
  public HexShape getCurrentHex(int row, int column) {
    return cellsThatMakeTheBoard[row][column];
  }

  @Override
  public boolean isValidMove(int x, int y, PlayerType playerType) {
    if (board.isValidMove(x, y, playerType)) {
      log.append("Valid move.");
    } else {
      log.append("Not a valid move.");
    }
    return board.isValidMove(x, y, playerType);
  }

  @Override
  public boolean isValidCoordinate(int q, int r) {
    if (board.isValidCoordinate(q, r)) {
      log.append("Valid coordinate.");
    } else {
      log.append("Not a valid coordinate.");
    }        return board.isValidCoordinate(q, r);
  }

  @Override
  public List<Move> getValidMovesWithCaptures(Player player) {
    log.append("Checking valid :")
            .append(player)
            .append(board.getValidMovesWithCaptures(player));
    return board.getValidMovesWithCaptures(player);
  }

  @Override
  public boolean isCornerMove(Move move, int boardSize) {
    if (board.isCornerMove(move, boardSize)) {
      log.append(move).append(" is a corner move.");
    } else {
      log.append(move).append(" is not a corner move.");
    }
    return board.isCornerMove(move, boardSize);
  }

  public StringBuilder getLog() {
    return log;
  }
}

