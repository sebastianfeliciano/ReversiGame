package model;

import java.awt.*;

import controller.PlayerType;

public class Board {
  public static int BOARD_SIZE;  //Default OOD Website Size
  public static HexShape[][] cellsThatMakeTheBoard;

  public Board() {
    BOARD_SIZE = 7;
    cellsThatMakeTheBoard = new HexShape[BOARD_SIZE][BOARD_SIZE];
    int midPoint = BOARD_SIZE / 2;

    for (int row = 0; row < BOARD_SIZE; row++) {
      int startQ;
      int endQ;

      if (row <= midPoint) {
        startQ = midPoint - row;
        endQ = BOARD_SIZE - startQ;
      } else {
        startQ = row - midPoint;
        endQ = BOARD_SIZE - startQ;
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
        endQ = BOARD_SIZE - startQ;
      } else {
        startQ = row - midPoint;
        endQ = BOARD_SIZE - startQ;
      }

      for (int column = startQ; column < endQ; column++) {
        int q = column - midPoint;
        int r = row - midPoint;
        cellsThatMakeTheBoard[row][column] = new HexShape(r, q, null);
      }
    }
  }

  // Changed this to an instance method
  public HexShape getCurrentHex(int column, int row) {
    return cellsThatMakeTheBoard[row][column];
  }

  //IGNORE THIS
  public HexShape getTopRightHex() {
    for (int col = cellsThatMakeTheBoard[0].length - 1; col >= 0; col--) {
      if (cellsThatMakeTheBoard[0][col] != null) {
        return cellsThatMakeTheBoard[0][col];
      }
    }
    return null; // This should not happen if the board is properly initialized.
  }

  //IGNORE THIS
  public Point getTopRightHexPosition() {
    for (int column = BOARD_SIZE - 1; column >= 0; column--) {
      if (cellsThatMakeTheBoard[0][column] != null) {
        return new Point(column, 0);  // Return as soon as you find the hexagon
      }
    }
    return null;  // Return null if not found (this should ideally not happen)
  }




//  BOARD_SIZE = 7;
//  //Entire Board is a entire HexShape Composed of the Board_size(2)
//  cellsThatMakeTheBoard = new HexShape[BOARD_SIZE][BOARD_SIZE];
//  int topLineIndex = -((BOARD_SIZE) / 2);
//  int maxLineIndex = (BOARD_SIZE) / 2;
//    for (int row = topLineIndex; row < maxLineIndex; row++) {
//
//    //-3
//
//    //3 - 3 = 0
//    for (int column = Math.abs(row) - BOARD_SIZE/2; column < BOARD_SIZE; column++) {
//      if(row <= 0){
//        int c = Math.abs(row);
//        cellsThatMakeTheBoard[row + BOARD_SIZE/2][c]  = new HexShape(column , row, null);
//      }
//      else {
//        cellsThatMakeTheBoard[row + BOARD_SIZE/2][Math.abs(column)]  = new HexShape(column , row, null);
//      }
//    }
//  }
}
