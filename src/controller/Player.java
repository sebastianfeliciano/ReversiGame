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

  @Override
  public void moveToSpot(Player currentPlayer, int c, int r) throws IOException {
    //if (Board.getCurrentCell(c, r) == PlayerType.EMPTY ){
  }
}

  // other getters, setters, and methods