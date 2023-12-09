package adapters;

import javax.swing.JLabel;

import java.awt.Graphics;
import java.util.Objects;

import model.Board;
import model.BoardModel;
import provider.controller.IViewFeatures;
import provider.view.IGraphicalReversiView;
import provider.view.JFrameView;
import view.DrawInterfaceMocker;

/**
 * Adapter class implementing IGraphicalReversiView to provide a graphical
 * interface for the Reversi game.
 * This class adapts various functionalities
 * to work within a graphical context, bridging between the
 * game model and the view.
 */
public class IGraphicalReversiViewAdapter implements IGraphicalReversiView {

  private DrawInterfaceMocker done;
  private Graphics g;
  private BoardModel board;

  private final JFrameView jFrameView;

  /**
   * Constructs an IGraphicalReversiViewAdapter with specified view features and a score label.
   *
   * @param features   The IViewAdapter instance to add features to the view.
   * @param scoreLabel The JLabel to display the score.
   */
  public IGraphicalReversiViewAdapter(IViewAdapter features,
                                      JLabel scoreLabel, JFrameView jFrameView) {
    IViewAdapter features1 = Objects.requireNonNull(features);
    JLabel scoreLabel1 = Objects.requireNonNull(scoreLabel);
    this.jFrameView = Objects.requireNonNull(jFrameView);
  }

  /**
   * Adds view features to this graphical view.
   *
   * @param feature The IViewFeatures to add to the view.
   */
  @Override
  public void addFeatures(IViewFeatures feature) {
    // Assigned earlier, do not need to reassign feature.
  }

  /**
   * Updates the view based on the current state of the game.
   */
  @Override
  public void updateView() {
    if (jFrameView != null) {
      jFrameView.updateView();
    }
  }


  /**
   * Displays an error message in the graphical interface.
   *
   * @param message The error message to display.
   */
  @Override
  public void displayErrorMessage(String message) {
    checkingGameOverNess();
    if (jFrameView != null) {
      jFrameView.displayErrorMessage("It is an invalid move or it isn't your turn!");
    }
  }

  private void checkingGameOverNess() {
    if (board.isGameOver()) {
      int black = board.getScoreBlack();
      int white = board.getScoreWhite();
      if (black > white) {
        jFrameView.displayGameOver("BLACK");
      }
      if (black < white) {
        jFrameView.displayGameOver("WHITE");
      }
      if (black == white) {
        jFrameView.displayGameOver("TIE");
      }
    }
  }

  /**
   * Displays a message indicating whose turn it is.
   *
   * @param playerDisc The identifier for the player whose turn it is.
   */
  @Override
  public void displayWho(String playerDisc) {
    if (jFrameView != null) {
      checkingGameOverNess();
      jFrameView.displayWho(playerDisc);
    }
  }

  /**
   * Displays a game over message and the winner.
   *
   * @param winner The identifier for the winning player.
   */
  @Override
  public void displayGameOver(String winner) {
    if (jFrameView != null) {
      jFrameView.displayGameOver(winner);
    }
  }

  /**
   * Renders the game board in the graphical interface.
   */
  @Override
  public void render() {
    jFrameView.render();
  }

  /**
   * Gets the graphics context used for rendering the game.
   *
   * @return The Graphics context.
   */
  public Graphics getGraphics() {
    return this.g;
  }

  /**
   * Gets the current state of the game board.
   *
   * @return The Board representing the current state of the game.
   */
  public Board getBoard() {
    return (Board) this.board;
  }
}
