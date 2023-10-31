package controller;

public enum PlayerType {
  //Represents the different pieces in a game
  BLACK('O'), WHITE('X'), EMPTY('_');

  private final char gamePiece;

  PlayerType(char gamePiece) {
    this.gamePiece = gamePiece;
  }

  //Checks who the nextPlayer should be (opposite game piece) or the
  //Switch GamePiece
  public PlayerType nextPlayer(){
    {
      if (this == BLACK) {
        return WHITE;
      } else if (this == WHITE) {
        return BLACK;
      } else {
        return EMPTY;
      }
    }
  }

  @Override
  public String toString() {
    return String.valueOf(gamePiece);
  }

}
