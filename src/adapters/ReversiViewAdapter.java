package adapters;

import model.Board;
import model.BoardModel;
import provider.controller.IViewFeatures;
import provider.view.IGraphicalReversiView;
import view.ReversiView;

import java.awt.Graphics;

/**
 * Adapter class that integrates ReversiView with the IGraphicalReversiView interface.
 * This class adapts a ReversiView instance to provide graphical rendering and interaction
 * capabilities for the Reversi game, conforming to the IGraphicalReversiView interface.
 */
public class ReversiViewAdapter implements IGraphicalReversiView {
  private final ReversiView drawInterface;
  private final BoardModel board;

  Graphics g;

  /**
   * Constructs a ReversiViewAdapter with a given ReversiView and BoardModel.
   *
   * @param drawInterface The ReversiView instance used for rendering the game.
   * @param board         The BoardModel representing the state of the game.
   */
  public ReversiViewAdapter(ReversiView drawInterface, BoardModel board) {
    this.drawInterface = drawInterface;
    this.board = board;
  }

  /**
   * Adds interactive features to the view.
   * Implementation can be customized to handle specific view features.
   *
   * @param feature The features to be added to the view.
   */
  @Override
  public void addFeatures(IViewFeatures feature) {
    // Feature is assigned already elsewhere in this code. Do not need to re-assign.
  }

  /**
   * Updates the view to reflect any changes in the game state.
   */
  @Override
  public void updateView() {
    drawInterface.update();
  }

  /**
   * Displays an error message to the user.
   * Delegates the display of the message to the drawInterface.
   *
   * @param message The error message to display.
   */
  @Override
  public void displayErrorMessage(String message) {
    drawInterface.showInvalidMoveMessage();
  }

  /**
   * Displays a message indicating whose turn it is.
   * Delegates to the drawInterface for displaying the message.
   *
   * @param playerDisc The identifier of the player whose turn it is.
   */
  @Override
  public void displayWho(String playerDisc) {
    drawInterface.itIsNowYourTurnMessage();
  }

  /**
   * Displays a message indicating the game is over and the winner.
   * Delegates to the drawInterface for handling the game over scenario.
   *
   * @param winner The identifier of the winning player.
   */
  @Override
  public void displayGameOver(String winner) {
    drawInterface.handleGameOver();
  }

  /**
   * Renders the graphical components of the game.
   * Delegates the rendering process to the drawInterface's drawBoard method.
   */
  @Override
  public void render() {
    drawInterface.drawBoard(g, (Board) board);
  }
}
