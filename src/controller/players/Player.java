package controller.players;

import controller.MoveHandler;
import model.ReadOnlyBoardModel;

/**
 * Represents a single player in a reversi game.
 */
public class Player implements IPlayer {
  private final String name;
  private final PlayerType type;
  final ReadOnlyBoardModel board;
  public boolean hasPassed;
  private MoveHandler moveHandler;

  /**
   * Constructor for player with a name, a player type,
   * and a board.
   */
  public Player(String name, PlayerType type, ReadOnlyBoardModel board) {
    this.name = name;
    this.type = type;
    this.board = board;
    this.hasPassed = false;
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
//  public void setHasPassed() {
//    this.hasPassed = true;
//  }

  public void resetHasPassed() {
    this.hasPassed = false;
  }

  public void setHasPassed() {
    this.hasPassed = true;
  }


  public void setSpecificHasPassed(boolean booleanWord) {
    this.hasPassed = booleanWord;
  }

  /**
   * Places a valid move on the board.
   */
//  public void makeMove(int row, int column) {
//    placeKey(row, column);
//  }

  public void setMoveHandler(MoveHandler moveHandler) {
    this.moveHandler = moveHandler;
  }

  public void makeMove(int row, int column) {
    if (moveHandler != null) {
      moveHandler.handleMove(this, row, column);
    }
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

  public boolean getHasPassed() {
    return this.hasPassed;
  }

}