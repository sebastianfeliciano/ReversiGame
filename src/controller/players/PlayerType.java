package controller.players;

/**
 * Represents the different game pieces in a game.
 */
public enum PlayerType {
  BLACK('O'), WHITE('X'), EMPTY('_');

  private final char gamePiece;

  /**
   * Constructor used for setting a playerType.
   */
  PlayerType(char gamePiece) {
    this.gamePiece = gamePiece;
  }

  /**
   * Checks who the nextPlayer should be (opposite game piece) or the
   * Switch GamePiece.
   */
  public PlayerType nextPlayer() {
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

  /**
   * Returns the string value of a gamePiece.
   */
  @Override
  public String toString() {
    return String.valueOf(gamePiece);
  }
}
