package model;

import java.util.ArrayList;
import java.util.List;

import controller.DirectionsEnum;
import controller.DirectionsSquareEnum;
import controller.players.IPlayer;
import controller.players.PlayerType;

public class SquareBoard extends Board {

  public SquareBoard(int sizeOfBoard) {
    super(sizeOfBoard);
    ensureValidRadius(sizeOfBoard);
    this.boardSize = sizeOfBoard;
    cellsThatMakeTheBoard = new HexShape[boardSize][boardSize];
    makeBoard();
  }

  @Override
  public void ensureValidRadius(int size) {
    if (size < 6 || (size % 2 != 0)) {
      throw new IllegalStateException("The game must be a minimum of size 6 and cannot be odd!");
    }
  }


  public void makeBoard() {
    for (int row = 0; row < this.boardSize; row++) {
      for (int col = 0; col < this.boardSize; col++) {
        this.cellsThatMakeTheBoard[row][col] = new Square(row, col, PlayerType.EMPTY);
      }
    }

    this.getCurrentHex(this.boardSize / 2 - 1, this.boardSize / 2 - 1).setPlayerType(PlayerType.BLACK);
    this.getCurrentHex(this.boardSize / 2 - 1, this.boardSize / 2).setPlayerType(PlayerType.WHITE);
    this.getCurrentHex(this.boardSize / 2, this.boardSize / 2 - 1).setPlayerType(PlayerType.WHITE);
    this.getCurrentHex(this.boardSize / 2, this.boardSize / 2).setPlayerType(PlayerType.BLACK);
  }


  @Override
  public boolean isValidMove(int x, int y, PlayerType playerType) {
    if (!isValidCoordinate(y, x) || getCurrentHex(y, x).getPlayerType() != PlayerType.EMPTY) {
      return false;
    }

    PlayerType opponent = playerType.nextPlayer();
    for (DirectionsSquareEnum dir : DirectionsSquareEnum.values()) {
      int nextX = x + dir.getXMove();
      int nextY = y + dir.getYMove();

      if (!isValidCoordinate(nextY, nextX) || getCurrentHex(nextY, nextX).getPlayerType() != opponent) {
        continue;
      }

      nextX += dir.getXMove();
      nextY += dir.getYMove();

      while (isValidCoordinate(nextY, nextX) && getCurrentHex(nextY, nextX).getPlayerType() == opponent) {
        nextX += dir.getXMove();
        nextY += dir.getYMove();
      }

      if (isValidCoordinate(nextY, nextX) && getCurrentHex(nextY, nextX).getPlayerType() == playerType) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void flipPieces(int x, int y, PlayerType currentPlayer) {
    if (currentPlayer == null) {
      System.out.println("Current player is null!");
      return;
    }

    PlayerType opponent = currentPlayer.nextPlayer();
    if (opponent == null) {
      System.out.println("Opponent is null!");
      return;
    }

    for (DirectionsSquareEnum dir : DirectionsSquareEnum.values()) {
      List<Shape> piecesToFlip = new ArrayList<>();
      int nextX = x + dir.getXMove();
      int nextY = y + dir.getYMove();

      while (isValidCoordinate(nextY, nextX) && getCurrentHex(nextY, nextX).getPlayerType() == opponent) {
        piecesToFlip.add(getCurrentHex(nextY, nextX));
        nextX += dir.getXMove();
        nextY += dir.getYMove();
      }

      if (isValidCoordinate(nextY, nextX) && getCurrentHex(nextY, nextX).getPlayerType() == currentPlayer) {
        for (Shape piece : piecesToFlip) {
          piece.setPlayerType(currentPlayer);
        }
      }
    }
  }

  /**
   * Gets a valid list of moves that a player can play on a square board.
   */
  @Override
  public List<Move> getValidMovesWithCaptures(IPlayer player) {
    List<Move> validMoves = new ArrayList<>();
    for (int row = 0; row < getBoardSize(); row++) {
      for (int col = 0; col < getBoardSize(); col++) {
        if (isValidMove(col, row, player.getType())) {
          int piecesThatAreFlipped = calculateCaptures(col, row, player.getType(), this);
          validMoves.add(new Move(col, row, piecesThatAreFlipped));
        }
      }
    }
    return validMoves;
  }


  @Override
  public void placePiece(int x, int y, PlayerType type) {
    this.getCurrentHex(y, x).setPlayerType(type);
    setWhiteBoolean(false);
    setBlackBoolean(false);
    checkGameOver();
  }



}
