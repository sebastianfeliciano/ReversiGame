package model.strategies;

import controller.Player;
import model.Board;
import model.Move;
import model.ReadOnlyBoardModel;

public class TwoStratsCombine implements ITwoStrategies {
    private final IStrategy primaryStrategy;
    private final IStrategy secondaryStrategy;

    public TwoStratsCombine(IStrategy primaryStrategy, IStrategy secondaryStrategy) {
      this.primaryStrategy = primaryStrategy;
      this.secondaryStrategy = secondaryStrategy;
    }

    @Override
    public Move selectMove(Board board, Player player) {
      if (!board.getValidMovesWithCaptures(player).isEmpty()) {
        return primaryStrategy.selectMove(board, player);
      } else {
        return secondaryStrategy.selectMove(board, player);
      }
    }

  @Override
  public Move selectMove(ReadOnlyBoardModel board, Player player) {
    return null;
  }
}

