package model;

import controller.players.PlayerType;

public class Square extends HexShape implements Shape {
  /**
   * Constructor for creating a certain cell in a board.
   * Throws an IllegalArgumentException when a certain coordinate does not
   * exist in the board.
   *
   * @param currentPlayerType
   */
  public Square(int x, int y, PlayerType currentPlayerType) {
    super(x, y, currentPlayerType);
  }
}
