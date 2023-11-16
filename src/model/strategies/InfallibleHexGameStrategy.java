package model.strategies;

import java.util.Optional;

import controller.Player;
import model.Move;
import model.ReadOnlyBoardModel;

public interface InfallibleHexGameStrategy extends IStrategy {
  Optional<Move> selectMove(ReadOnlyBoardModel boardModel, Player player) throws IllegalStateException;
}
