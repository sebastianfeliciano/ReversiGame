package controller;

import model.Board;


public class Player implements IPlayer {
  private final String name;
  private final PlayerType type;
  private final Board board;
  private boolean hasPassed;

  public Player(String name, PlayerType type, Board board) {  // Added Board parameter
    this.name = name;
    this.type = type;
    this.board = board;
    this.hasPassed = false;
  }

  public String getName() {
    return name;
  }

  public PlayerType getType() {
    return type;
  }

  public boolean hasPassed() {
    return hasPassed;
  }

  public void setHasPassed() {
    this.hasPassed = !hasPassed;
  }

  @Override
  public void placeKey(int x, int y) {
    if (!board.isValidMove(x, y, this.getType())) { // Notice the change here
      throw new IllegalArgumentException("Not a Valid Move!");
    }
    if (x > board.getBoardSize() / 2 || x < -board.getBoardSize() || y > board.getBoardSize() / 2 || y < -board.getBoardSize()){
      throw new IllegalArgumentException("Not a Valid Move!");
    }
    int q = x + board.getBoardSize() / 2;
    int r = y + board.getBoardSize() / 2;
    board.placePiece(q, r, this.getType());
    board.flipPieces(q, r, this.getType());
  }
}