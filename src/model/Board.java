package model;

import controller.PlayerType;

public class Board {
  public static int BOARD_SIZE;  //Default OOD Website Size
  public HexShape[][] cellsThatMakeTheBoard;

  public Board() {
    BOARD_SIZE = 7;
    //Entire Board is a entire HexShape Composed of the Board_size(2)
    cellsThatMakeTheBoard = new HexShape[BOARD_SIZE][BOARD_SIZE];

    int topLineIndex = -((BOARD_SIZE) / 2);
    int maxLineIndex = (BOARD_SIZE) / 2;

    for (int row = topLineIndex; row < maxLineIndex; row++) {

      for (int column = Math.abs(row) - BOARD_SIZE/2; column < BOARD_SIZE - Math.abs(row); column++) {

        cellsThatMakeTheBoard[0][row + BOARD_SIZE/2]  = new HexShape(column , row, null);
      }
    }
  }


  public Board(int sizeOfBoard) {
    if (sizeOfBoard < 8 || (sizeOfBoard % 2 == 0)) {
      throw new IllegalStateException("The game must be a minimum of size 9 " +
              "and cannot be even!");
    }
    BOARD_SIZE = sizeOfBoard;
    cellsThatMakeTheBoard = new HexShape[BOARD_SIZE][BOARD_SIZE];
    for (int i = -((BOARD_SIZE + 1) / 2); i < BOARD_SIZE / 2; i++) {
      for (int j = Math.abs(i); j < BOARD_SIZE; j++) {
        this.cellsThatMakeTheBoard[i + (BOARD_SIZE + 1) / 2][j] = new HexShape(i, j, null);
      }
    }
  }

  public HexShape getCurrentHex(int c, int r) {
    return cellsThatMakeTheBoard[c][r];
  }
}
