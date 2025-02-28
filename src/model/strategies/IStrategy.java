package model.strategies;

import java.util.Optional;

import controller.players.IPlayer;
import model.Move;
import model.ReadOnlyBoardModel;

/**
 * Interface for all types of strategies.
 */
public interface IStrategy {

  /**
   * Represents a certain move that an AIPlayer makes.
   */
  Optional<Move> selectMove(ReadOnlyBoardModel board, IPlayer player);
}
