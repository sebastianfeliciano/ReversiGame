import org.junit.Assert;
import org.junit.Test;

import controller.PlayerType;
import model.Board;

public class exampleBoardTests {

  /**
   * Tests a Board that is given an even number which
   * should not work.
   */
  @Test
  public void testingEvenBoardInvalid() {
    Assert.assertThrows(IllegalStateException.class, () -> new Board(12));
  }

  /**
   * Tests a Board that is given an odd number which
   * should work and be empty.
   */
  @Test
  public void testingOddBoardValid() {
    Board madeBoard = new Board(); //This is default as 11
    for(int i = 0; i < Board.BOARD_SIZE; i++) {
      for(int j = 0; j < Board.BOARD_SIZE; j++) {
        Assert.assertEquals(madeBoard.getCurrentHex(i, j), PlayerType.EMPTY);
      }
    }
  }

  /**
   * Tests a Board that is given a negative number which
   * should not work.
   */
  @Test
  public void testingNegavtiveBoardValid() {
    Assert.assertThrows(IllegalStateException.class, () -> new Board(-6));
  }
}
