package adapters;

import controller.players.PlayerType;
import model.HexShape;
import provider.model.Hex;

/**
 * A HexAdapter class that converts my providers, Hex to my HexShape.
 * This also accounts for conversion coordination. That is made in other classes
 * and methods.
 */
public class HexAdapter {

  private final Hex hex;
  private PlayerType currentPlayerType;

  /**
   * Constructs a HexAdapter using an instance of Hex and a PlayerType.
   *
   * @param hex               The Hex instance to be adapted.
   * @param currentPlayerType The current player type to associate with the Hex.
   */
  public HexAdapter(Hex hex, PlayerType currentPlayerType) {
    this.hex = hex;
    this.currentPlayerType = currentPlayerType;
  }

  /**
   * Adapts the Hex object to a HexShape object.
   *
   * @return HexShape The adapted HexShape with the coordinates and player type.
   */
  public HexShape adaptToHexShape() {
    int q = hex.getQ();
    int r = hex.getR();
    return new HexShape(r, q, currentPlayerType);
  }

  /**
   * Sets the current player type for this adapter.
   *
   * @param currentPlayerType The player type to be set.
   */
  public void setCurrentPlayerType(PlayerType currentPlayerType) {
    this.currentPlayerType = currentPlayerType;
  }

  /**
   * Gets the current player type associated with this adapter.
   *
   * @return PlayerType The current player type.
   */
  public PlayerType getCurrentPlayerType() {
    return currentPlayerType;
  }

  /**
   * Returns the R of the Hex.
   * @return r.
   */
  public int getR() {
    return this.hex.getR();
  }

  /**
   * Returns the Q of the Hex.
   * @return q.
   */
  public int getQ() {
    return this.hex.getQ();
  }

}

