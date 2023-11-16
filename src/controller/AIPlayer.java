package controller;

import java.util.Optional;

import model.Board;
import model.strategies.FallibleHexGameStrategy;
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
    Optional<Move> selectedMove = strategy.selectMove(this.board, this);
    if (selectedMove.isPresent()) {
      super.placeKey(selectedMove.get().getX(), selectedMove.get().getY());
    } else {
      this.setHasPassed();
    }
  }
}
