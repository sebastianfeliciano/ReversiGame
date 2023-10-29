package view;
import java.io.IOException;
import java.util.Arrays;

import model.Board;
import model.HexShape;

public class TextualController implements ReversiTextualView {
  private Board board;
  private final Appendable output;

  /**
   * view.TextualController constructor that only takes in the model which
   * is the first game state.
   */
  public TextualController(Board board) {
    if (board == null) {
      throw new IllegalArgumentException("model.Board cannot be null");
    }
    this.board = board;
    this.output = System.out;
  }

  /**
   * Constructor for view.TextualController.
   */
  public TextualController(Board board, Appendable output) {
    if (board == null) {
      throw new IllegalArgumentException("model.Board cannot be null");
    }
    this.board = board;
    this.output = output;
  }


  /**
   * Renders the game using the appendable builder method.
   */
  @Override
  public void render() throws IOException {
    output.append(toString());
  }


  /**
   * Returns a to-Commandline view
   * representation of the Reversi model.Board Model.
   */
//  @Override
//  public String toString() {
//    StringBuilder stringMaker = new StringBuilder();
//    for (HexShape[] hex : Board.cellsThatMakeTheBoard) {
//      stringMaker.append(Arrays.toString(hex)).append(" ");
//    }
//    return stringMaker.toString();
//  }
  @Override
  public String toString() {
    StringBuilder stringMaker = new StringBuilder();

    int sizeOfEntireBoard = Board.BOARD_SIZE;

    int middleRowIndex = sizeOfEntireBoard / 2;

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int allowedNumberOfHexagons;

      if (currentRow <= middleRowIndex) {
        allowedNumberOfHexagons = (sizeOfEntireBoard / 2) + currentRow + 1;
      } else {
        allowedNumberOfHexagons = sizeOfEntireBoard - (currentRow - middleRowIndex);
      }

      int numOfSpaces = (sizeOfEntireBoard - allowedNumberOfHexagons);

      for (int spaces = 0; spaces < numOfSpaces; spaces++) {
        stringMaker.append(' ');
      }

      for (int currentNumOfHexagons = 0; currentNumOfHexagons < allowedNumberOfHexagons; currentNumOfHexagons++) {
        HexShape currentHexagon = board.getCurrentHex(currentRow, allowedNumberOfHexagons - 1);
        String currentPlayerInTheHexagon = currentHexagon.getPlayerType().toString();
        stringMaker.append(currentPlayerInTheHexagon).append(' ');
      }

      stringMaker.append('\n');
    }

    return stringMaker.toString();
  }
}

