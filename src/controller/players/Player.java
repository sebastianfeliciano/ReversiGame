package controller.players;

import model.Board;

/**
 * Represents a single player in a reversi game.
 */
public class Player implements IPlayer {
  private final String name;
  private final PlayerType type;
  final Board board;
  public boolean hasPassed;
  public boolean currentTurnStatus;

  /**
   * Constructor for player with a name, a player type,
   * and a board.
   */
  public Player(String name, PlayerType type, Board board) {
    this.name = name;
    this.type = type;
    this.board = board;
    this.hasPassed = false;
    this.currentTurnStatus = true;
  }

  /**
   * Returns the name of a player.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the PLayerType of a player.
   */
  public PlayerType getType() {
    return type;
  }

  /**
   * The player passes their turns.
   */
  public void setHasPassed() {
    this.hasPassed = !hasPassed;
  }

  /**
   * Places a valid move on the board.
   */
  public void makeMove(int row, int column) {
    placeKey(row, column);
  }


  /**
   * Allows the user to play a textual version of the game.
   */
  public void placeKey(int x, int y) {
    if (!board.isValidMove(x, y, this.getType())) {
      throw new IllegalArgumentException("Not a Valid Move!");
    }
    if (x > board.getBoardSize() / 2 || x < -board.getBoardSize()
            || y > board.getBoardSize() / 2 || y < -board.getBoardSize()) {
      throw new IllegalArgumentException("Not a Valid Move!");
    }
    int q = x + board.getBoardSize() / 2;
    int r = y + board.getBoardSize() / 2;
    board.placePiece(q, r, this.getType());
    board.flipPieces(q, r, this.getType());
  }

  public String getColor() {
    if (this.getType() == PlayerType.WHITE) {
      return whitePlayer();
    } else {
      return blackPlayer();
    }
  }

  public String whitePlayer(){
    return "White";
  }

  public String blackPlayer(){
    return "Black";
  }

  public String getOtherColor() {
      if (this.getType() == PlayerType.WHITE) {
        return blackPlayer();
      } else {
        return whitePlayer();
      }
    }
}