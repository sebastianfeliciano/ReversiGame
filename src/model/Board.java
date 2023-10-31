package model;

import controller.Directions;
import controller.PlayerType;

import java.util.ArrayList;
import java.util.List;


public class Board {
  public int BOARD_SIZE;  //Default OOD Website Size
  public HexShape[][] cellsThatMakeTheBoard;
  private boolean whitePassed = false;
  private boolean blackPassed = false;


  public Board() {
    this(7);
  }

  public Board(int sizeOfBoard) {
    if (sizeOfBoard < 5 || (sizeOfBoard % 2 == 0)) {
      throw new IllegalStateException("The game must be a minimum of size 5 and cannot be even!");
    }

    BOARD_SIZE = sizeOfBoard;
    cellsThatMakeTheBoard = new HexShape[BOARD_SIZE][BOARD_SIZE];
    int midPoint = BOARD_SIZE / 2;

    for (int row = 0; row < BOARD_SIZE; row++) {
      int startQ;
      int endQ;

      if (row <= midPoint) {
        startQ = midPoint - row;
        endQ = BOARD_SIZE;
      } else {
        startQ = 0;
        endQ = BOARD_SIZE - row + midPoint;
      }

      for (int column = startQ; column < endQ; column++) {
        int q = column - midPoint;
        int r = row - midPoint;
        cellsThatMakeTheBoard[row][column] = new HexShape(r, q, null);
      }
    }
  }



  // Changed this to an instance method
  public HexShape getCurrentHex(int row, int column) {
    return cellsThatMakeTheBoard[row][column];
  }

  public void flipPieces(int x, int y, PlayerType currentPlayer) {
    PlayerType opponent = currentPlayer.nextPlayer();

    for (Directions dir : Directions.values()) {
      int nextQ = x + dir.getQMove();
      int nextR = y + dir.getRMove();
      List<HexShape> piecesToFlip = new ArrayList<>();

      while (isValidCoordinate(nextQ, nextR) && this.getCurrentHex(nextQ, nextR).getPlayerType().equals(opponent)) {
        piecesToFlip.add(getCurrentHex(nextQ, nextR));
        nextQ += dir.getQMove();
        nextR += dir.getRMove();
      }

      // If the line ends in the current player's piece, then flip all the pieces in this line
      if (isValidCoordinate(nextQ, nextR) && getCurrentHex(nextQ, nextR).getPlayerType().equals(currentPlayer)) {
        for (HexShape piece : piecesToFlip) {
          piece.setPlayerType(currentPlayer);
        }
      }
    }
  }

  public boolean isValidMove(int x, int y, PlayerType playerType) {
    // Convert (x, y) to (q, r) representation
    if (x > this.getBoardSize()/2 || y > this.getBoardSize()/2
            || x < -this.getBoardSize()/2 || y < -this.getBoardSize()/2){
      return false;
    }
    int q = x + this.getBoardSize()/ 2; //Converts it onto the 2D Array Layout
    int r = y + this.getBoardSize() / 2; //Converts it onto the 2D array

    if (Math.abs(r) >= this.getBoardSize()) {
      return false;
    }

    if (!getCurrentHex(q, r).getPlayerType().equals(PlayerType.EMPTY)) {
      return false;
    }

    PlayerType currentPlayer = playerType;
    PlayerType opponent = currentPlayer.nextPlayer();


    for (Directions dir : Directions.values()) {
      int nextQ = q + dir.getQMove();
      int nextR = r + dir.getRMove();

      //opponent's piece next to the current position
      if (isValidCoordinate(nextQ, nextR) && getCurrentHex(nextR, nextQ).getPlayerType().equals(opponent)) {
        while (isValidCoordinate(nextQ, nextR) && !getCurrentHex(nextR, nextQ).getPlayerType().equals(PlayerType.EMPTY)) {
          nextQ += dir.getQMove();
          nextR += dir.getRMove();

          if (isValidCoordinate(nextQ, nextR) && getCurrentHex(nextR, nextQ).getPlayerType().equals(currentPlayer)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean isValidCoordinate(int q, int r) {
    return q >= 0 && q < this.getBoardSize() && r >= 0 && r < this.getBoardSize();
  }

  public void placePiece(int q, int r, PlayerType type) {
    this.getCurrentHex(r, q).setPlayerType(type);
  }

  public int getBoardSize() {
    return BOARD_SIZE;
  }

  public boolean isGameOver() {
    return isBoardFull() || bothPlayersPassed() || isPlayerTrapped(PlayerType.WHITE) || isPlayerTrapped(PlayerType.BLACK);
  }

  private boolean bothPlayersPassed() {
    return whitePassed && blackPassed;
  }

  private boolean isPlayerTrapped(PlayerType player) {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        HexShape hex = cellsThatMakeTheBoard[i][j];
        if (hex != null && hex.getPlayerType() == player) {
          if (isValidMove(i - BOARD_SIZE / 2, j - BOARD_SIZE / 2, player)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean isBoardFull() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        HexShape hex = getCurrentHex(i, j);
        if (hex == null || hex.getPlayerType() == null || hex.getPlayerType() == PlayerType.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }


  public int countPieces(PlayerType type) {
    int count = 0;
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if (getCurrentHex(i, j) != null && getCurrentHex(i, j).getPlayerType() == type) {
          count++;
        }
      }
    }
    return count;
  }


}