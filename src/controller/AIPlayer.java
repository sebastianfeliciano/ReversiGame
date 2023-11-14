package controller;

import model.Board;
import model.strategies.IStrategy;
import model.Move;

public class AIPlayer extends Player {
  private final IStrategy strategy;

  public AIPlayer(String name, PlayerType type, Board board, IStrategy strategy) {
    super("Computer", type, board);
    this.strategy = strategy;
    this.hasPassed = false;
  }

  public void makeMove() {
    Move selectedMove = strategy.selectMove(this.board, this);
    if (selectedMove != null) {
      super.placeKey(selectedMove.getX(), selectedMove.getY());
    } else {
      this.setHasPassed();
    }
  }
}
