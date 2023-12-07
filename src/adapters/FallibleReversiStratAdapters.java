package adapters;

import java.util.Objects;
import java.util.Optional;

import controller.players.IPlayer;
import model.Move;
import model.ReadOnlyBoardModel;
import model.strategies.FallibleHexGameStrategy;
import model.strategies.IStrategy;
import provider.model.Hex;
import provider.model.PlayerDisc;
import provider.player.FallibleReversiStrat;

/**
 * A FallibleReversiStratAdapters class that allows the ability to adapt the provider's
 * strategy.
 */
public class FallibleReversiStratAdapters implements FallibleHexGameStrategy, IStrategy {
  private final FallibleReversiStrat strategy;

  /**
   * Constructs a FallibleReversiStratAdapters with a specified FallibleReversiStrat.
   *
   * @param strategy The FallibleReversiStrat to be adapted.
   */
  public FallibleReversiStratAdapters(FallibleReversiStrat strategy) {
    this.strategy = Objects.requireNonNull(strategy);

  }

  /**
   * Selects the next move based on the given board state and player.
   *
   * @param board  The board model on which the move is to be selected.
   * @param player The player for whom the move is being selected.
   * @return An Optional containing the selected Move, or an empty Optional if no move is selected.
   */
  @Override
  public Optional<Move> selectMove(ReadOnlyBoardModel board, IPlayer player) {
    IROModelAdapter readOnlyAdapter = new IROModelAdapter(board);

    PlayerDisc who = PlayerDiscAdapter.convertToPlayerDisc(player.getType());
    Optional<Hex> hexMove = strategy.chooseMove(readOnlyAdapter, who);
    System.out.println("selected the move " + hexMove);

    if (hexMove.isPresent()) {
      return Optional.of(convertHexToMove(hexMove.get()));
    } else {
      player.setHasPassed();
      return Optional.empty();
    }

  }


  /**
   * Converts a Hex object to a Move object.
   *
   * @param hex The Hex object to be converted.
   * @return The Move object representing the same position as the given Hex.
   */
  private Move convertHexToMove(Hex hex) {
    return new Move(hex.getQ(), hex.getR());
  }

}
