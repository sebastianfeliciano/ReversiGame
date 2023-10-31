package controller;

/**
 * Sets up an enum for directions.
 */
public enum Directions {
  UP(0, 1),
  DOWN(0, -1),
  LEFT(-1, 0),
  RIGHT(1, 0),
  TOP_LEFT_CORNER(-1, 1),
  TOP_RIGHT_CORNER(1, 1),
  BOTTOM_LEFT_CORNER(-1, -1),
  BOTTOM_RIGHT_CORNER(1, -1);

  private final int qMove;
  private final int rMove;

  /**
   * Constructor used to make a move.
   */
  Directions(int qMove, int rMove) {
    this.qMove = qMove;
    this.rMove = rMove;
  }

  /**
   * Gets the Row move.
   */
  public int getQMove() {
    return qMove;
  }

  /**
   * Gets the Column move.
   */
  public int getRMove() {
    return rMove;
  }
}

