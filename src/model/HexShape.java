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


  public PlayerType getPlayerType() {
    if (this.currentPlayerType == null){
      return PlayerType.EMPTY;
    }
    else {
      return this.currentPlayerType;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.column, this.row);
  }

  //Method that will help for testing
  public String getColumn() {
    return String.valueOf(this.column);
  }

  public String getRow() {
    return String.valueOf(this.row);
  }

  public String getSValue() {
    return String.valueOf(this.s);
  }

  public PlayerType setPlayerType(PlayerType type) {
    return this.currentPlayerType = type;
  }
}
