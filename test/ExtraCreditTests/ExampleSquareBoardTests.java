package ExtraCreditTests;

import controller.players.IPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Move;
import model.Shape;
import model.Square;
import model.SquareBoard;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ExampleSquareBoardTests {

  private SquareBoard squareBoard;

  @Before
  public void setUp() {
    squareBoard = new SquareBoard(8);
  }

  @Test
  public void testEnsureValidRadius() {

    try {
      squareBoard.ensureValidRadius(8);
    } catch (IllegalStateException e) {
      fail("Should not throw an exception for a valid radius.");
    }

    try {
      squareBoard.ensureValidRadius(4);
      fail("Should throw an exception for an invalid radius.");
    } catch (IllegalStateException e) {
      assertEquals("The game must be a minimum of size 6 and cannot be odd!", e.getMessage());
    }

    try {
      squareBoard.ensureValidRadius(7);
      fail("Should throw an exception for an invalid radius.");
    } catch (IllegalStateException e) {
      assertEquals("The game must be a minimum of size 6 and cannot be odd!", e.getMessage());
    }
  }

  @Test
  public void testMakeBoard() {
    for (int row = 0; row < 8; row++) {
      for (int col = 0; col < 8; col++) {
        Shape cell = squareBoard.getCurrentHex(row, col);
        Assert.assertNotNull(cell);
      }
    }
  }


  @Test
  public void testInitialization() {
    Assert.assertSame(squareBoard.getCurrentHex(3, 3).getPlayerType(), PlayerType.BLACK);
    Assert.assertSame(squareBoard.getCurrentHex(4, 3).getPlayerType(), PlayerType.WHITE);
    Assert.assertSame(squareBoard.getCurrentHex(3, 4).getPlayerType(), PlayerType.WHITE);
    Assert.assertSame(squareBoard.getCurrentHex(4, 4).getPlayerType(), PlayerType.BLACK);
  }

  @Test
  public void testIsValidMove() {
    Assert.assertTrue(squareBoard.isValidMove(4, 2, PlayerType.BLACK));
    squareBoard.placePiece(4, 2, PlayerType.BLACK);
    Assert.assertFalse(squareBoard.isValidMove(3, 3, PlayerType.WHITE));
    Assert.assertFalse(squareBoard.isValidMove(8, 8, PlayerType.WHITE));
  }

  @Test
  public void testVerticalFlipPieces() {
    squareBoard.placePiece(5, 3, PlayerType.BLACK);
    squareBoard.flipPieces(5, 3, PlayerType.BLACK);

    Assert.assertEquals(PlayerType.BLACK, squareBoard.getCurrentHex(3, 5).getPlayerType());
    Assert.assertEquals(PlayerType.BLACK, squareBoard.getCurrentHex(3, 4).getPlayerType());
    Assert.assertEquals(PlayerType.BLACK, squareBoard.getCurrentHex(3, 3).getPlayerType());
  }

  @Test
  public void testHorizontalFlipPieces() {
    squareBoard.placePiece(4, 2, PlayerType.BLACK);
    squareBoard.flipPieces(4, 2, PlayerType.BLACK);
    Assert.assertEquals(PlayerType.BLACK, squareBoard.getCurrentHex(4, 4).getPlayerType());
    Assert.assertEquals(PlayerType.BLACK, squareBoard.getCurrentHex(3, 4).getPlayerType());
    Assert.assertEquals(PlayerType.BLACK, squareBoard.getCurrentHex(2, 4).getPlayerType());
  }


  @Test
  public void testDiagonalFlipPieces() {
    squareBoard.placePiece(4, 2, PlayerType.BLACK);
    squareBoard.flipPieces(4, 2, PlayerType.BLACK);
    squareBoard.placePiece(3, 2, PlayerType.WHITE);
    squareBoard.flipPieces(3, 2, PlayerType.WHITE);

    squareBoard.placePiece(2, 2, PlayerType.BLACK);
    squareBoard.flipPieces(2, 2, PlayerType.BLACK);

    Assert.assertEquals(PlayerType.BLACK, squareBoard.getCurrentHex(2, 2).getPlayerType());
    Assert.assertEquals(PlayerType.BLACK, squareBoard.getCurrentHex(3, 3).getPlayerType());
    Assert.assertEquals(PlayerType.BLACK, squareBoard.getCurrentHex(4, 4).getPlayerType());
  }

  @Test
  public void testGetValidMovesWithCaptures() {
    IPlayer player = new Player("S", PlayerType.BLACK, squareBoard);
    List<Move> validMoves = squareBoard.getValidMovesWithCaptures(player);
    assertEquals(4, validMoves.size());

    squareBoard.placePiece(4, 2, PlayerType.BLACK);
    squareBoard.flipPieces(4, 2, PlayerType.BLACK);

    IPlayer player2 = new Player("K", PlayerType.WHITE, squareBoard);
    List<Move> validMoves2 = squareBoard.getValidMovesWithCaptures(player2);
    assertEquals(3, validMoves2.size());
  }

}
