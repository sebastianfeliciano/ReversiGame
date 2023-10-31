package view;
import java.io.IOException;
import java.util.Arrays;

import controller.PlayerType;
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

    // Set the middle hexagon to PlayerType.BLACK
    board.getCurrentHex(3, 3).setPlayerType(PlayerType.BLACK);
    board.getCurrentHex(3, 4).setPlayerType(PlayerType.BLACK);

    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = sizeOfEntireBoard / 2;

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int numOfHexagons;

      if (currentRow <= midPoint) {
        numOfHexagons = midPoint + currentRow + 1;
      } else {
        numOfHexagons = sizeOfEntireBoard - (currentRow - midPoint);
      }

      //1stLine
      //4 Hex
      //3 Spaces
      int spacesBefore = (sizeOfEntireBoard - numOfHexagons);

      for (int s = 0; s < spacesBefore; s++) {
        stringMaker.append(' ');
      }

      for (int h = 0; h < numOfHexagons; h++) {
        HexShape currentHexagon;
        if (currentRow <= midPoint) {
           currentHexagon = board.getCurrentHex(currentRow, spacesBefore + h);
        } else  {
           currentHexagon = board.getCurrentHex(currentRow, h);
        }

        String currentPlayerInTheHexagon = currentHexagon.getPlayerType().toString();
        stringMaker.append(currentPlayerInTheHexagon).append(' ');
      }

      stringMaker.append('\n');
    }

    return stringMaker.toString();
  }




}

