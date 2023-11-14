package controller;

import model.Board;
import model.Strategy;

public class AIPlayer {
  private PlayerType type;
  private Board board;
  private Strategy strat;

  public AIPlayer(PlayerType type, Board board, Strategy strat) {
    this.type = type;
    this.board = board;
    this.strat = strat;

  }

}
