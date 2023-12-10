package extraCreditTests;

import controller.SquareTextView;

import org.junit.Assert;
import org.junit.Test;

import controller.TextualController;
import model.BoardModel;
import model.SquareBoard;

/**
 * Tests that tes the textual view.
 */
public class ExampleSquareTextual {

  /**
   * Tests that the controller throws an exception when passed in a null.
   */
  @Test
  public void testController() {
    Assert.assertThrows(IllegalArgumentException.class, () ->
            new TextualController(null));
  }

  /**
   * Tests that the initial board correctly prints out as a string.
   */
  @Test
  public void testInitialBoard() {
    BoardModel board = new SquareBoard(6);
    TextualController controller = new SquareTextView(board);
    Assert.assertEquals(controller.toString(), "_ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ \n" +
            "_ _ O X _ _ \n" +
            "_ _ X O _ _ \n" +
            "_ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ \n"
    );

  }

}