package view;
import java.io.IOException;

import model.Board;

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

    int sizeOfEntireBoard;


    if (Board.BOARD_SIZE % 2 != 0) {
      sizeOfEntireBoard = Board.BOARD_SIZE;
    } else {
      sizeOfEntireBoard = Board.BOARD_SIZE - 1;
    }

    int middleRowIndex = sizeOfEntireBoard / 2;


    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int numberOfCharacters;

      // Determine the number of characters for the current line.
      if (currentRow <= middleRowIndex) {
        numberOfCharacters = (Board.BOARD_SIZE / 2) + currentRow + 1;
      } else {
        numberOfCharacters = Board.BOARD_SIZE - (currentRow - middleRowIndex);
      }

      //Track the number of spaces avalible per line
      int numOfSpaces = (sizeOfEntireBoard - numberOfCharacters);

      //Track the number of spaces for each row
      for (int spaces = 0; spaces < numOfSpaces; spaces++) {
        stringMaker.append(' ');
      }

      // Append characters for the current line
      for (int currentChar = 0; currentChar < numberOfCharacters; currentChar++) {
        //This is one character (X) or (_ )
        stringMaker.append(board.getCurrentCell(currentChar, currentRow)).append(' ');
      }

      stringMaker.append('\n');
    }
    return stringMaker.toString();
  }
}
