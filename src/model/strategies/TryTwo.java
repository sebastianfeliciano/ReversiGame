package model.strategies;

import java.util.Optional;

import controller.Player;
import model.Move;
import model.ReadOnlyBoardModel;

public class TryTwo implements FallibleHexGameStrategy {
  FallibleHexGameStrategy first, second;

  public TryTwo(FallibleHexGameStrategy first, FallibleHexGameStrategy second) {
    this.first = first;
    this.second = second;
  }


  public Optional<Move> selectMove(ReadOnlyBoardModel board, Player player) {
    Optional<Move> firstMove = this.first.selectMove(board, player);
    if (firstMove.isPresent()) return firstMove; // the first strategy succeeded
    return this.second.selectMove(board, player);
  }
}
