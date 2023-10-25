package model;

import java.util.Objects;

public class HexShape {

  //The most common approach is to
  // offset every other column or row.
  // Columns are named col (q). Rows are named row (r).
  // You can either offset the odd or the even column/rows,
  // so the horizontal and vertical hexagons each have two variants.
  private final int q;
  private final int r;

  public HexShape(int q, int r) {
    this.q = q;
    this.r = r;
  }

@Override
public int hashCode(){
  return Objects.hash(this.q, this.r);
}

}
