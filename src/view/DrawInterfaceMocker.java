package view;

import java.awt.Graphics;

import javax.swing.JLabel;

import model.Board;

/**
 * Interface that helps test the mocked view.
 */
public interface DrawInterfaceMocker {
  void handleGameOver();

  /**
   * Signals to the user that they cannot move.
   */
  void showInvalidMoveMessage();

  /**
   * Signals to the user that passed their turn.
   */
  void showThatIPassedTurnMessage();

  /**
   * Signals to the user it is now their turn.
   */
  void itIsNowYourTurnMessage();

  /**
   * Checks if the Game Over was handled.
   */
  boolean getGameOverHandleState();

  /**
   * Resets the Game Over boolean.
   */
  void resetGameOverHandled();

  /**
   * Tells the player it is not their turn.
   */
  void itIsNotYourTurnMessage();

  /**
   * Updates the score on the frame.
   */
  void updateScore(int blackScore, int whiteScore);

  /**
   * Setter for the score label.
   */
  void setScoreLabel(JLabel scoreLabel);

  /**
   * Updates the state of the game.
   */
  void update();

  /**
   * Draws the board out.
   */
  void drawBoard(Graphics g, Board board);


}
