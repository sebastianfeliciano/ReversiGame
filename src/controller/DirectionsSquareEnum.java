package controller;

/**
 * Sets up an enum for directions.
 */
public enum DirectionsSquareEnum {
  NORTH(0, -1),
  NORTH_EAST(1, -1),
  EAST(1, 0),
  SOUTH_EAST(1, 1),
  SOUTH(0, 1),
  SOUTH_WEST(-1, 1),
  WEST(-1, 0),
  NORTH_WEST(-1, -1);

  private final int xMove;
  private final int yMove;

  /**
   * Constructor used to make a move.
   */
  DirectionsSquareEnum(int xMove, int yMove) {
    this.xMove = xMove;
    this.yMove = yMove;
  }

  /**
   * Gets the Row move.
   */
  public int getXMove() {
    return xMove;
  }

  /**
   * Gets the Column move.
   */
  public int getYMove() {
    return yMove;
  }
}

