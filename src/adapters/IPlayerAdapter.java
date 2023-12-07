package adapters;

import java.util.Objects;

import controller.players.IPlayer;  // Renamed your IPlayer interface for clarity
import provider.controller.IViewFeatures;
import provider.model.PlayerDisc;
import provider.player.PlayerMoves;

/**
 * Adapter class that implements provider.player.IPlayer to interact with an IPlayer implementation.
 * This class adapts the IPlayer interface, allowing it to be used within
 * a different context or framework.
 */
public class IPlayerAdapter implements provider.player.IPlayer {
  private final IPlayer player;

  /**
   * Constructs an IPlayerAdapter with a specified IPlayer.
   *
   * @param player The IPlayer to be adapted.
   */
  public IPlayerAdapter(IPlayer player) {
    this.player = Objects.requireNonNull(player);
    PlayerMoves currentMove = Objects.requireNonNull(PlayerMoves.MAKE_MOVE);
  }

  /**
   * Adds view features to the player.
   *
   * @param features The view features to be added.
   */
  @Override
  public void addFeatures(IViewFeatures features) {
    // Do not need to add here, did it in another class. Do not want to
    // reassign features.
  }

  /**
   * Determines and executes the action to be taken by the player.
   * If the player has passed, it sets the current move as a pass.
   * Otherwise, it triggers the player to make a move.
   */
  @Override
  public void chooseAction() {
    if (player.getHasPassed()) {
      player.setHasPassed();
    } else {
      player.makeMove();
    }
  }

  /**
   * Gets the type of disc associated with the player.
   *
   * @return The PlayerDisc type of the player.
   */
  @Override
  public PlayerDisc getPlayerDisc() {
    return PlayerDiscAdapter.convertToPlayerDisc(player.getType());
  }
}
