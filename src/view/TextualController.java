package view;

import java.io.IOException;
import java.util.Arrays;

import controller.PlayerType;
import model.Board;
import model.HexShape;

public class TextualController implements ReversiTextualView {
  private final Board board;
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
  @Override
  public String toString() {
    StringBuilder stringMaker = new StringBuilder();


    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = sizeOfEntireBoard / 2;

    board.getCurrentHex(sizeOfEntireBoard / 2, sizeOfEntireBoard / 2 + 1).setPlayerType(PlayerType.BLACK);
    board.getCurrentHex(sizeOfEntireBoard / 2 + 1, sizeOfEntireBoard / 2).setPlayerType(PlayerType.WHITE);
    board.getCurrentHex(sizeOfEntireBoard / 2, sizeOfEntireBoard / 2 - 1).setPlayerType(PlayerType.WHITE);
    board.getCurrentHex(sizeOfEntireBoard / 2 + 1, sizeOfEntireBoard / 2 - 1).setPlayerType(PlayerType.BLACK);
    board.getCurrentHex(sizeOfEntireBoard / 2 - 1, sizeOfEntireBoard / 2).setPlayerType(PlayerType.BLACK);
    board.getCurrentHex(sizeOfEntireBoard / 2 - 1, sizeOfEntireBoard / 2 + 1).setPlayerType(PlayerType.WHITE);


    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int numOfHexagons;

      if (currentRow <= midPoint) {
        numOfHexagons = midPoint + currentRow + 1;
      } else {
        numOfHexagons = sizeOfEntireBoard - (currentRow - midPoint);
      }

      int spacesBefore = (sizeOfEntireBoard - numOfHexagons);

      for (int s = 0; s < spacesBefore; s++) {
        stringMaker.append(' ');
      }

      for (int h = 0; h < numOfHexagons; h++) {
        HexShape currentHexagon;
        if (currentRow <= midPoint) {
          currentHexagon = board.getCurrentHex(currentRow, spacesBefore + h);
          System.out.print(currentHexagon.getRow() + ",");
          System.out.println(currentHexagon.getColumn());
          System.out.println(currentHexagon.getPlayerType().toString());

        } else {
          currentHexagon = board.getCurrentHex(currentRow, h);
          System.out.print(currentHexagon.getRow() + ",");
          System.out.println(currentHexagon.getColumn());
          System.out.println(currentHexagon.getPlayerType().toString());
        }

        String currentPlayerInTheHexagon = currentHexagon.getPlayerType().toString();
        stringMaker.append(currentPlayerInTheHexagon).append(' ');
      }

      stringMaker.append('\n');
    }

    return stringMaker.toString();
  }

}

