package model.strategies;

import java.util.Optional;

import controller.Player;
import model.Move;
import model.ReadOnlyBoardModel;

public interface FallibleHexGameStrategy extends IStrategy {
  Optional<Move> selectMove(ReadOnlyBoardModel board, Player player);
}
