package adapters;

import controller.players.PlayerType;
import provider.model.PlayerDisc;

/**
 * A utility class providing static methods to convert between
 * PlayerDisc and PlayerType enumerations.
 * This adapter facilitates the interaction between
 * different enumeration types used across the application,
 * ensuring a consistent mapping between the two.
 */
public class PlayerDiscAdapter {

  /**
   * Converts a PlayerDisc enum to a corresponding PlayerType enum.
   *
   * @param playerDisc The PlayerDisc enum to convert.
   * @return The corresponding PlayerType enum.
   * @throws IllegalArgumentException if the provided PlayerDisc is unknown or null.
   */
  public static PlayerType convertToPlayerType(PlayerDisc playerDisc) {
    switch (playerDisc) {
      case BLACK:
        return PlayerType.BLACK;
      case WHITE:
        return PlayerType.WHITE;
      case EMPTY:
        return PlayerType.EMPTY;
      default:
        throw new IllegalArgumentException("Unknown PlayerDisc: " + playerDisc);
    }
  }


  /**
   * Converts a PlayerType enum to a corresponding PlayerDisc enum.
   *
   * @param playerType The PlayerType enum to convert.
   * @return The corresponding PlayerDisc enum.
   * @throws IllegalArgumentException if the provided PlayerType is unknown or null.
   */
  public static PlayerDisc convertToPlayerDisc(PlayerType playerType) {
    switch (playerType) {
      case BLACK:
        return PlayerDisc.BLACK;
      case WHITE:
        return PlayerDisc.WHITE;
      case EMPTY:
        return PlayerDisc.EMPTY;
      default:
        throw new IllegalArgumentException("Unknown PlayerType: " + playerType);
    }
  }
}
