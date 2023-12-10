package view;

import controller.ReversiController;
import controller.players.PlayerType;
import model.Board;
import model.BoardModel;
import model.HexShape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents the view of a Reversi Board in a hexagonal grid format. This interface
 * defines methods for updating the view, handling user interactions, and drawing
 * various components of the Reversi game.
 */
public interface ReversiView extends DrawInterfaceMocker {

  /**
   * Updates the view of the Reversi board. This method is typically called
   * when the game state changes.
   */
  void update();

  /**
   * Finds the corresponding hex based on the mouse position.
   *
   * @param mouseX The x-coordinate of the mouse position.
   * @param mouseY The y-coordinate of the mouse position.
   * @return The HexShape that the mouse is currently over.
   */
  HexShape findHex(int mouseX, int mouseY);

  /**
   * Returns the size of a single hexagon in the grid.
   *
   * @return The size of one hexagon.
   */
  int getHexSize();

  /**
   * Checks if a given point lies within a hexagon.
   *
   * @param x       The x-coordinate of the point.
   * @param y       The y-coordinate of the point.
   * @param centerX The x-coordinate of the center of the hexagon.
   * @param centerY The y-coordinate of the center of the hexagon.
   * @param hexSize The size of the hexagon.
   * @return True if the point is inside the hexagon, false otherwise.
   */
  boolean isPointInHex(int x, int y, int centerX, int centerY, int hexSize);

  /**
   * Returns the width of the window displaying the board.
   *
   * @return The width of the window.
   */
  int getWindowWidth();

  /**
   * Draws each hexagon on the board.
   *
   * @param g          The Graphics object used for drawing.
   * @param hex        The HexShape to be drawn.
   * @param centerX    The x-coordinate of the center of the hexagon.
   * @param centerY    The y-coordinate of the center of the hexagon.
   * @param hexSize    The size of the hexagon.
   * @param playerType The type of the player for coloring.
   */
  void drawEachHexagon(Graphics g,
                       HexShape hex, int centerX, int centerY, int hexSize, PlayerType playerType);

  /**
   * Returns the color associated with a specific player type.
   *
   * @param playerType The type of the player.
   * @return The color associated with the given player type.
   */
  Color getColor(PlayerType playerType);

  /**
   * Draws the entire Reversi board.
   *
   * @param g     The Graphics object used for drawing.
   * @param board The Board object representing the state of the game.
   */
  void drawBoard(Graphics g, Board board);

  /**
   * Returns the height of the window displaying the board.
   *
   * @return The height of the window.
   */
  int getWindowHeight();

  /**
   * Updates the board model.
   *
   * @param board The new BoardModel to be displayed.
   */
  void updateBoard(BoardModel board);

  /**
   * Requests focus in the window for handling user input.
   *
   * @return True if focus was successfully requested, false otherwise.
   */
  boolean requestFocusInWindow();

  /**
   * Sets the event listener for the view.
   *
   * @param controller The controller that handles events.
   */
  void setEventListener(ReversiController controller);

}
