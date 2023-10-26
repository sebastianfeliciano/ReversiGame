package model;

import controller.PlayerType;

public class Board {
    public static int BOARD_SIZE;  //Default OOD Website Size
    public PlayerType[][] cells;

    public Board() {
      BOARD_SIZE = 11;
      cells = new PlayerType[BOARD_SIZE][BOARD_SIZE];
    }


  public Board(int sizeOfBoard) {
      if(sizeOfBoard < 8) {
        throw new IllegalStateException("The game must be a minimum of size 8x8 " +
                "to start");
      }

      BOARD_SIZE = sizeOfBoard;
      cells = new PlayerType[BOARD_SIZE][BOARD_SIZE];
  }

  public PlayerType getCurrentCell(int c, int r) {
        if(cells[c][r] == null){
          return PlayerType.EMPTY;
      }
        else {
        return cells[c][r];
      }
  }
}
