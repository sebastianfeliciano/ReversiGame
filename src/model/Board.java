package model;

public class Board {
  public static int BOARD_SIZE;  //Default OOD Website Size
  public HexShape[][] cellsThatMakeTheBoard;

  public Board() {
    BOARD_SIZE = 11;
    cellsThatMakeTheBoard = new HexShape[BOARD_SIZE][BOARD_SIZE];
  }


  public Board(int sizeOfBoard) {
    if (sizeOfBoard < 8 || (sizeOfBoard % 2 == 0)) {
      throw new IllegalStateException("The game must be a minimum of size 9 " +
              "and cannot be even!");
    }
    BOARD_SIZE = sizeOfBoard;
    cellsThatMakeTheBoard = new HexShape[BOARD_SIZE][BOARD_SIZE];
  }

  public HexShape getCurrentHex(int c, int r) {
    return cellsThatMakeTheBoard[c][r];
  }
}



