package model;

import java.util.Objects;

public class Move {
  private int x;
  private int y;
  private int piecesCaught;


  public Move(int x, int y) {
    this.x = x;
    this.y = y;
    this.piecesCaught = 0; // Default to 0, can be set later if needed
  }

  public Move(int x, int y, int piecesCaught) {
    this.x = x;
    this.y = y;
    this.piecesCaught = piecesCaught;
  }

  // Getters
  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getPiecesCaught() {
    return piecesCaught;
  }

  // Setters (if you want to change values after creation)
  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setPiecesCaught(int piecesCaught) {
    this.piecesCaught = piecesCaught;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Move move = (Move) o;
    return x == move.x && y == move.y && piecesCaught == move.piecesCaught;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y, piecesCaught);
  }
}
