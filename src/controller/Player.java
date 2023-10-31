package controller;

import model.Board;

/**
 * Sets up a player in the Reversi Game.
 */
public class Player implements IPlayer {
  private final String name;
  private final PlayerType type;
  private final Board board;
  private boolean hasPassed;

  /**
   * Constructor used for creating a new player, with
   * a name, a type, and a model.
   */
  public Player(String name, PlayerType type, Board board) {
    this.name = name;
    this.type = type;
    this.board = board;
    this.hasPassed = false;
  }

  /**
   * Returns the name value of a player.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the type of player.
   */
  public PlayerType getType() {
    return type;
  }

  /**
   * Determines whether the play wants to skip their turn.
   */
  public boolean hasPassed() {
    return hasPassed;
  }

  /**
   * Sets the boolean to be true, that the player did choose to pass.
   */
  public void setHasPassed(boolean hasPassed) {
    this.hasPassed = hasPassed;
  }

  /**
   * Places a certain player into the board based on coordinates passed in.
   */
  @Override
  public void placeKey(int x, int y) {
    if (!board.isValidMove(x, y, this.getType())) { // Notice the change here
      throw new IllegalArgumentException("Not a Valid Move!");
    }
    int q = x + board.getBoardSize() / 2;
    int r = y + board.getBoardSize() / 2;
    board.placePiece(q, r, this.getType());
    board.flipPieces(q, r, this.getType());
  }
}
