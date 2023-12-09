package model.strategies;

import java.util.Optional;

import controller.players.IPlayer;
import model.Move;
import model.ReadOnlyBoardModel;

/**
 * Represents a fallible strategy in a ReversiGame.
 */
public interface FallibleHexGameStrategy extends IStrategy {
  Optional<Move> selectMove(ReadOnlyBoardModel board, IPlayer player);
}
