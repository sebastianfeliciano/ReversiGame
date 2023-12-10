package controller;


import model.BoardModel;
import model.Shape;

/**
 * Textual View representation of a square board.
 */
public class SquareTextView extends TextualController {
  BoardModel board;

  public SquareTextView(BoardModel board) {
    super(board);
    this.board = board;
  }


  /**
   * Returns a to-Commandline view
   * representation of the Reversi model.Board Model.
   */
  @Override
  public String toString() {
    StringBuilder stringMaker = new StringBuilder();
    int sizeOfEntireBoard = board.getBoardSize();

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      for (int columns = 0; columns < sizeOfEntireBoard; columns++) {
        Shape square = board.getCurrentHex(currentRow, columns);
        String currentPlayerInTheHexagon = square.getPlayerType().toString();
        stringMaker.append(currentPlayerInTheHexagon).append(' ');
      }
      stringMaker.append('\n');
    }
    return stringMaker.toString();
  }
}
