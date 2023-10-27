package view;
import java.io.IOException;
import java.util.Arrays;

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

    //Gets the size of the board
    int sizeOfEntireBoard = Board.BOARD_SIZE;

    //For 7/2 = 3 is the middle line from 0 to max size
    int middleRowIndex = sizeOfEntireBoard / 2;


    //Start at the top, then go to the max sizeOfThe Board
    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int allowedNumberOfHexagons;

      // Determine the number of characters for the current line.

      //if 0 <= 3
      //num characters = 7/2 = 3 +0 + 1

      //1 <= 3 -> 7/2 = 3 + 1+ 1

      //2 < = 3 -> 7/2 = 3 + 3 + 1 = 7
      if (currentRow <= middleRowIndex) {
        allowedNumberOfHexagons = (Board.BOARD_SIZE / 2) + currentRow + 1 ;
      } else {
        //11 - (0 -3) = 14

        //currentRow = 4 -> 6
        //currentRow = 5 -> 5
        //currentRow = 6 -> 4




        //
        allowedNumberOfHexagons = Board.BOARD_SIZE - (currentRow - middleRowIndex);
      }

      //Track the number of spaces avalible per line
      int numOfSpaces = (sizeOfEntireBoard - allowedNumberOfHexagons);

      //Track the number of spaces for each row
      for (int spaces = 0; spaces < numOfSpaces + 1; spaces++) {
        stringMaker.append(' ');
      }

      // Append characters for the current line
      for (int currentNumOfHexagons = 0; currentNumOfHexagons < allowedNumberOfHexagons; currentNumOfHexagons++) {
        //This is one character (X) or (_ )
        HexShape currentHexagon = Board.getCurrentHex(currentNumOfHexagons, currentRow);

        String currentPlayerInTheHexagon = currentHexagon.getPlayerType().toString();
        stringMaker.append(currentPlayerInTheHexagon).append(' ');
      }

      stringMaker.append('\n');
    }
    return stringMaker.toString();
  }
}

