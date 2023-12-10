package adapters;

import provider.model.Hex;
import model.Board;
import model.HexShape;
import controller.players.PlayerType;
import provider.model.PlayerDisc;
import provider.view.IView;
import view.ReversiView;

import java.awt.Graphics;

/**
 * Adapter class that integrates ReversiView with the provider's IView interface.
 * This class adapts the ReversiView to be used with a different interface, allowing for
 * the rendering and updating of the Reversi game board.
 */
public class IViewAdapter implements IView {
  private final ReversiView reversiView;
  private Board board;
  private Graphics g;
  private Hex hex;
  private int centerX;
  private int centerY;
  private int hexSize;
  private PlayerType playerType;

  /**
   * Constructs an IViewAdapter with a specified ReversiView.
   *
   * @param reversiView The ReversiView to be adapted.
   */
  public IViewAdapter(ReversiView reversiView) {
    this.reversiView = reversiView;
  }


  /**
   * Renders the game board using the adapted ReversiView.
   */
  @Override
  public void render() {
    reversiView.drawEachHexagon(g, getHex(), centerX, centerY, hexSize, playerType);
    reversiView.update();
    drawBoard(g, board);
  }

  /**
   * Draws the game board on the given Graphics context.
   *
   * @param g     The Graphics context to draw on.
   * @param board The Board to be drawn.
   */
  public void drawBoard(Graphics g, Board board) {
    reversiView.drawBoard(g, board);
  }

  /**
   * Returns the Graphics context used for drawing.
   *
   * @return The current Graphics context.
   */
  public Graphics getG() {
    return this.g;
  }

  /**
   * Retrieves the HexShape corresponding to the current Hex state.
   * Converts a Hex object to a HexShape by determining its PlayerType.
   *
   * @return The adapted HexShape object.
   */
  public HexShape getHex() {
    if (this.hex == null) {
      return null;
    }
    IBoardAdapter boardAdapter = new IBoardAdapter(this.board);
    PlayerType playerTypeForHex = determinePlayerTypeForHex(this.hex, boardAdapter);
    HexAdapter hexAdapter = new HexAdapter(this.hex, playerTypeForHex);
    return hexAdapter.adaptToHexShape();
  }

  /**
   * Determines the PlayerType for a given Hex using a BoardAdapter instance.
   * Fetches the PlayerDisc from the Hex and converts it to PlayerType.
   *
   * @param hex          The Hex for which to determine the player type.
   * @param boardAdapter An IBoardAdapter instance to access board state.
   * @return The PlayerType associated with the Hex.
   */
  private PlayerType determinePlayerTypeForHex(Hex hex, IBoardAdapter boardAdapter) {
    PlayerDisc playerDisc = boardAdapter.getPlayerDiscForHex(hex);
    return PlayerDiscAdapter.convertToPlayerType(playerDisc);
  }

  /**
   * Returns the centerX.
   *
   * @return number.
   */
  public int getCenterX() {
    return this.centerX;
  }

  /**
   * Returns the centerY.
   *
   * @return number.
   */
  public int getCenterY() {
    return this.centerY;
  }

  /**
   * Returns the Hex Size.
   *
   * @return number.
   */
  public int getHexSize() {
    return this.hexSize;
  }

  /**
   * Returns the Player's Type.
   *
   * @return player type.
   */
  public PlayerType getPlayerType() {
    return this.playerType;
  }
}

