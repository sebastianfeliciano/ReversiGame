package controller;

import java.io.IOException;

import model.Board;

public class Player implements IPlayer {
  private final String name;
  private final PlayerType type;

  public Player(String name, PlayerType type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public PlayerType getType() {
    return type;
  }

//  @Override
//  public void moveToSpot(Player currentPlayer, int c, int r) throws IOException {
//    if (!Board.getCurrentCell(c, r) == PlayerType.EMPTY) {
//      throw new IllegalArgumentException();
//    }
//    if (!validMove(c, r)) {
//      throw new IllegalArgumentException();
//    }
//  }

//  public boolean validMove(int c, int r) {
//    if (c > -Board.BOARD_SIZE /2 || c > Board.BOARD_SIZE / 2) {
//      return false;
//    }
//    if (r > -Board.BOARD_SIZE /2 || r > Board.BOARD_SIZE / 2) {
//      return false;
//    } else if (Board.getCurrentHex(c, r).getPlayerType() == PlayerType.EMPTY) {
//      return true;
//    }
//    return false;
//  }
}

  // other getters, setters, and methods