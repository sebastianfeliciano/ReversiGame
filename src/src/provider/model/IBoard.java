package provider.model;

import java.util.HashMap;

/**
 * The interface {@code IBoard} represents a board in a game of Reversi. Given the dynamic
 * shapes a board can carry in Reversi, classes implementing this interface will represent
 * their individually shaped board. One primary implementation of this interface is a Hexagonal
 * board. This interface involves various getter methods and some methods that mutate the actual
 * board.
 */
public interface IBoard {

  /**
   * Returns the size of this Board. The size of the board differs by the shape of the board.
   * For instance, a hexagonal board's size will be represented by its radius. In the case of
   * a hexagonal board, the radius is measured by the distance between the center of the board
   * and the center of the outermost hexagon (i.e., the number of layers between the
   * origin cell - the center-most cell - and the outermost cell).
   *
   * @return the size of this Board.
   */
  public int getSize();

  /**
   * Gets all the cells in this board.
   *
   * @return a HashMap where the key represents the coordinate of the cell and the value
   *        represents the state of the cell (i.e., whether it is occupied).
   */
  public HashMap<Hex, PlayerDisc> getCells();

  /**
   * Ensures that the given size is valid. For a size to be considered valid, it must be
   * positive and not zero.
   *
   * @param size represents the size of this Board.
   */
  public void ensureValidRadius(int size);

  /**
   * Initializes the board for a game of Reversi. This method will place an equal number of
   * {@code PlayerDisc.BLACK} and {@code PlayerDisc.WHITE} cells around the center of the board.
   * This method also puts new cells into the hashmap and assigns them the appropriate coordinate.
   */
  public void initializeGrid();

  /**
   * Sets a disc at the given coordinate with the given {@code PlayerDisc}.
   * EFFECT: changes the status of the hashmap with the given coordinate to the given PlayerDisc.
   *
   * @param coordQ represents the q coordinate of the cell.
   * @param coordR represents the r coordinate of the cell.
   * @param who    represents the PlayerDisc to be placed at the given coordinate.
   */
  public void setDisc(int coordQ, int coordR, PlayerDisc who);

  /**
   * Returns the total number of discs on this board (i.e., the number of {PlayerDisc.BLACK}
   * and {PlayerDisc.WHITE} discs on this board).
   *
   * @return the total number of discs on this board.
   */
  public int getTotalDiscs();

}
