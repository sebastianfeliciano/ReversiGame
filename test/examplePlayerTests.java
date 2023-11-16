import controller.Player;
import controller.PlayerType;
import model.Board;

import org.junit.Assert;
import org.junit.Test;

import view.TextualController;

public class examplePlayerTests {

  /**
   * Tests that player type strings are correct.
   */
  @Test
  public void testPlayerType() {
    Assert.assertEquals(PlayerType.WHITE.toString(), "X");
    Assert.assertEquals(PlayerType.BLACK.toString(), "O");
    Assert.assertEquals(PlayerType.EMPTY.toString(), "_");
  }

  /**
   * Tests that the textual controller throws an exception,
   * when started as null.
   */
  @Test
  public void testController() {
    Assert.assertThrows(IllegalArgumentException.class, () ->
            new TextualController(null));
  }

  /**
   * Tests that the name of a player is correct.
   */
  @Test
  public void testGetName() {
    Board firstBoard = new Board();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
    Assert.assertEquals(one.getName(), "Sebastian");
    Assert.assertEquals(two.getName(), "Christian");
  }

  /**
   * Tests the type of a certain player.
   */
  @Test
  public void testGetType() {
    Board firstBoard = new Board();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
    Assert.assertEquals(one.getType(), PlayerType.BLACK);
    Assert.assertEquals(two.getType(), PlayerType.WHITE);
  }

  /**
   * Tests that a player is correctly passing.
   */
  @Test
  public void hasPassed() {
    Board firstBoard = new Board();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
  }

  /**
   * Tests that two players are be passed.
   */
  @Test
  public void setHasPassed() {
    Board firstBoard = new Board();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
  }

  /**
   * Tests that a piece is being correctly placed.
   */
  @Test
  public void testPlaceKey() {
    Board firstBoard = new Board(7);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    Assert.assertEquals(firstBoard.getCurrentHex(2, 2).getPlayerType(),
            PlayerType.EMPTY);
    one.placeKey(-1, -1);
    Assert.assertEquals(firstBoard.getCurrentHex(2, 2).getPlayerType(),
            PlayerType.WHITE);
  }

  /**
   * Tests that an invalid key placed throws an exception.
   */
  @Test
  public void testInValidPlaceKey() {
    Board firstBoard = new Board(7);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    Assert.assertThrows(IllegalArgumentException.class, () -> one.placeKey(-7, -7));
  }

  /**
   * Tests that two players are correctly being initiated.
   */
  @Test
  public void testHasPassedInitalState() {
    Board firstBoard = new Board(7);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    Assert.assertFalse(one.hasPassed);
    Assert.assertFalse(two.hasPassed);
  }
}