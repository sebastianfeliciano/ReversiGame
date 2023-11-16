package model.strategies;

import java.util.Optional;

import controller.Player;
import model.Move;
import model.ReadOnlyBoardModel;

public class CompleteStrategyFromFallible implements InfallibleHexGameStrategy {
  FallibleHexGameStrategy strategyToTry;

  public Optional<Move> selectMove(ReadOnlyBoardModel boardModel, Player player) throws IllegalStateException {
    Optional<Move> firstMove = this.strategyToTry.selectMove(boardModel, player);
    if (firstMove.isPresent()) {
      return firstMove;
    }
    throw new IllegalStateException("There are no possible moves chosen by this strategy!");
  }
}
