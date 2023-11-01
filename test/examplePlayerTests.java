import org.junit.Assert;
import org.junit.Test;

import controller.Player;
import controller.PlayerType;
import model.Board;
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
   * Tests that the textual controller returns the correct names of players.
   */
  @Test
  public void testGetName() {
    Board firstBoard = new Board();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
    Assert.assertEquals(one.getName(), "Sebastian");
    Assert.assertEquals(two.getName(), "Christian");
  }

<<<<<<< HEAD

  /**
   * Tests that the textual controller returns the correct types of players.
   */
=======
  @Test
  public void testGetName() {
    Board firstBoard = new Board();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
    Assert.assertEquals(one.getName(), "Sebastian");
    Assert.assertEquals(two.getName(), "Christian");
  }


>>>>>>> main
  @Test
  public void testGetType() {
    Board firstBoard = new Board();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
    Assert.assertEquals(one.getType(), PlayerType.BLACK);
    Assert.assertEquals(two.getType(), PlayerType.WHITE);
  }

<<<<<<< HEAD
  /**
   * Tests that the textual controller returns if the player has passed or not.
   */
=======

>>>>>>> main
  @Test
  public void hasPassed() {
    Board firstBoard = new Board();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
  }

<<<<<<< HEAD
  /**
   * Tests that the textual controller returns that the player has passed.
   */
=======

>>>>>>> main
  @Test
  public void setHasPassed() {
    Board firstBoard = new Board();
    Player one = new Player("Sebastian", PlayerType.BLACK, firstBoard);
    Player two = new Player("Christian", PlayerType.WHITE, firstBoard);
  }

<<<<<<< HEAD
  /**
   * Tests that a key is correctly placed in the board.
   */
=======

>>>>>>> main
  @Test
  public void testPlaceKey() {
    Board firstBoard = new Board(7);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    Assert.assertEquals(firstBoard.getCurrentHex(2, 2).getPlayerType(),
            PlayerType.EMPTY);
    one.placeKey(-1,-1);
    Assert.assertEquals(firstBoard.getCurrentHex(2, 2).getPlayerType(),
            PlayerType.WHITE);
  }


<<<<<<< HEAD
  /**
   * Tests that an invalid key is placed in the board.
   */
=======
>>>>>>> main
  @Test
  public void testInValidPlaceKey() {
    Board firstBoard = new Board(7);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    Assert.assertThrows(IllegalArgumentException.class, () -> one.placeKey(-7, -7));
  }

<<<<<<< HEAD
  /**
   * Tests that a key is correctly seeing that a player has skipped their turn.
   */
  @Test
  public void testSetHasPassed() {
=======

  @Test
  public void testSettedHasPassed() {
>>>>>>> main
    Board firstBoard = new Board(7);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    Assert.assertFalse(one.hasPassed());
    Assert.assertFalse(two.hasPassed());

    one.setHasPassed(true);
    two.setHasPassed(true);
    Assert.assertTrue(one.hasPassed());
    Assert.assertTrue(two.hasPassed());
  }

<<<<<<< HEAD
  /**
   * Tests that when a player skips their turn, the board stays the same.
   */
=======
>>>>>>> main
  @Test
  public void testHasPassedInitalState() {
    Board firstBoard = new Board(7);
    Player one = new Player("Sebastian", PlayerType.WHITE, firstBoard);
    Player two = new Player("Christian", PlayerType.BLACK, firstBoard);
    Assert.assertFalse(one.hasPassed());
    Assert.assertFalse(two.hasPassed());
  }
<<<<<<< HEAD
=======


>>>>>>> main
}
