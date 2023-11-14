package controller;

import model.Board;
import model.strategies.IStratedgy;
import model.Move;

public class AIPlayer extends Player {
  private final IStratedgy strategy;

  public AIPlayer(String name, PlayerType type, Board board, IStratedgy strategy) {
    super("Computer", type, board);
    this.strategy = strategy;
    this.hasPassed = false;
  }

  public void makeMove() {
    Move selectedMove = strategy.selectMove(this.board, this.getType());
    if (selectedMove != null) {
      super.placeKey(selectedMove.getX(), selectedMove.getY());
    } else {
      this.setHasPassed();
    }
  }
}
