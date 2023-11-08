package model;

import java.util.Objects;

import controller.PlayerType;

public class HexShape {

  //The most common approach is to
  // offset every other column or row.
  // Columns are named col (q). Rows are named row (r).
  // You can either offset the odd or the even column/rows,
  // so the horizontal and vertical hexagons each have two variants.
  private final int column;
  private final int row;
  private final int s;
  protected PlayerType currentPlayerType;

  /**
   * Constructor for creating a certain cell in a board.
   * Throws an IllegalArgumentException when a certain coordinate does not
   * exist in the board.
   */
  public HexShape(int rowY, int columnQ, PlayerType currentPlayerType) {
    this.column = columnQ;
    this.row = rowY;
    this.currentPlayerType = currentPlayerType;
    //s = -q-r
    s = - columnQ - rowY;
    if (columnQ + rowY + s != 0) {
      throw new IllegalArgumentException("The Coordinate does not Exist");
    }
  }

  /**
   * Returns the current player type.
   * Returns an empty player if the current player type is null.
   */
  public PlayerType getPlayerType() {
    if (this.currentPlayerType == null){
      return PlayerType.EMPTY;
    }
    else {
      return this.currentPlayerType;
    }
  }

  /**
   * Returns the hashcode of a certain row and column.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.column, this.row);
  }

  /**
   * Method that will help for testing.
   */
  public String getColumn() {
    return String.valueOf(this.column);
  }

  /**
   * Returns the row value.
   */
  public String getRow() {
    return String.valueOf(this.row);
  }

  /**
   * Returns the value of the s-coordinate.
   */
  public String getSValue() {
    return String.valueOf(this.s);
  }

  /**
   * Sets the current player type.
   */
  public PlayerType setPlayerType(PlayerType type) {
    return this.currentPlayerType = type;
  }


  public static int hex_length(HexShape hex) {
    return (Math.abs(hex.column) + Math.abs(hex.row) + Math.abs(hex.s)) / 2;
  }

  public static int hex_distance(HexShape a, HexShape b) {
    return hex_length(hex_subtract(a, b));
  }

  public static HexShape hex_subtract(HexShape a, HexShape b) {
    return new HexShape(a.column - b.column, a.row - b.row, PlayerType.EMPTY);
  }

}