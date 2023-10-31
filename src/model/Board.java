package model;

import controller.PlayerType;

import java.awt.*;

public class Board {
  public int BOARD_SIZE;  //Default OOD Website Size
  public HexShape[][] cellsThatMakeTheBoard;

  public Board() {
    BOARD_SIZE = 7;
    cellsThatMakeTheBoard = new HexShape[BOARD_SIZE][BOARD_SIZE];
    int midPoint = BOARD_SIZE / 2; // 3

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

  public Board(int sizeOfBoard) {
    if (sizeOfBoard < 5 || (sizeOfBoard % 2 == 0)) {
      throw new IllegalStateException("The game must be a minimum of size 9 and cannot be even!");
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

  //IGNORE THIS
  public HexShape getTopRightHex() {
    for (int col = cellsThatMakeTheBoard[0].length - 1; col >= 0; col--) {
      if (cellsThatMakeTheBoard[0][col] != null) {
        return cellsThatMakeTheBoard[0][col];
      }
    }
    return null; // This should not reach.
  }

  public Point getTopRightHexPosition() {
    for (int column = BOARD_SIZE - 1; column >= 0; column--) {
      if (cellsThatMakeTheBoard[0][column] != null) {
        return new Point(column, 0);  // Return as soon as you find the hexagon
      }
    }
    return null;  // Return null when nothing exists
  }

//  public void moveToSpot(Player currentPlayer, int c, int r, Board board) {
//    if (board.getCurrentHex(c, r).getPlayerType() == PlayerType.EMPTY) {
//      // Place the current player's disc on the board
//      board.setHex(c, r, currentPlayer.getType());
//
//      // For each direction, check if discs should be flipped
//      for (int dx = -1; dx <= 1; dx++) {
//        for (int dy = -1; dy <= 1; dy++) {
//          if (dx != 0 || dy != 0) { // Skip the (0,0) direction
//            flipDiscsInDirection(currentPlayer.getType(), c, r, dx, dy, board);
//          }
//        }
//      }
//    }
//  }
//
//  public void setHex(int c, int r, PlayerType type) {
//    if(cellsThatMakeTheBoard[r][c] != null) {
//      cellsThatMakeTheBoard[r][c].setPlayerType(type);
//    }
//  }
//
//
//  private void flipDiscsInDirection(PlayerType currentPlayerType, int c, int r, int dx, int dy, Board board) {
//    int x = c + dx;
//    int y = r + dy;
//    boolean canFlip = false;
//
//    // Find the opponent's discs that might be flipped
//    while (Board.isValidCoordinate(x, y) && board.getCurrentHex(x, y).getPlayerType()
//            == currentPlayerType.isOpponent(currentPlayerType)) {
//      x += dx;
//      y += dy;
//    }
//
//    // Check if we found a disc of the current player after one or more opponent discs
//    if (Board.isValidCoordinate(x, y) && board.getCurrentHex(x, y).getPlayerType() == currentPlayerType) {
//      canFlip = true;
//    }
//
//    if (canFlip) {
//      x = c + dx;
//      y = r + dy;
//      while (Board.isValidCoordinate(x, y) && board.getCurrentHex(x, y).getPlayerType()
//              == currentPlayerType.isOpponent(currentPlayerType)) {
//        board.getCurrentHex(x, y);
//        x += dx;
//        y += dy;
//      }
//    }
//
//  }
//
//  public static boolean isValidCoordinate(int c, int r) {
//    return c >= 0 && c < BOARD_SIZE && r >= 0 && r < BOARD_SIZE;
//  }


  public int getBoardSize() {
    return this.BOARD_SIZE;
  }
}