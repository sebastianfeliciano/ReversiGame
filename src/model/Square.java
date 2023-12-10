package model;

import controller.players.PlayerType;

/**
 * Class for a square shape, for visualization purposes.
 */
public class Square extends HexShape implements Shape {

  /**
   * Constructor for creating a certain cell in a board.
   */
  public Square(int x, int y, PlayerType currentPlayerType) {
    super(x, y, currentPlayerType);
  }
}
