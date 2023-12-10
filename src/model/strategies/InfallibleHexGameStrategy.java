package model.strategies;

import java.util.Optional;

import controller.players.IPlayer;
import model.Move;
import model.ReadOnlyBoardModel;

/**
 * Represents an infallible strategy in reversi game.
 */
public interface InfallibleHexGameStrategy extends IStrategy {
  Optional<Move> selectMove(ReadOnlyBoardModel boardModel,
                            IPlayer player) throws IllegalStateException;
}
