import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import controller.PlayerType;
import model.Board;
import model.HexShape;
import view.TextualController;

public class exampleBoardTests {

  Board board = new Board(11);


  /**
   * Tests a Board that is given an even number which
   * should not work.
   */

  @Test
  public void testingEvenBoardInvalid() {
    Assert.assertThrows(IllegalStateException.class, () -> new Board(12));
  }

  /**
   * Tests a Board that is given a negative number which
   * should not work.
   */
  @Test
  public void testingNegativeBoardValid() {
    Assert.assertThrows(IllegalStateException.class, () -> new Board(-6));
  }

  @Test
  public void testingCoordinatesOfAHexagonBasedOn2DArray() {
    Board regularBoard = new Board(7);

    HexShape topLeft = regularBoard.getCurrentHex(0, 3);
    HexShape topLeftRepresentation = new HexShape(-3, 0, null);
    Assert.assertEquals(topLeft.getColumn(), topLeftRepresentation.getColumn());

    HexShape middle = regularBoard.getCurrentHex(3, 3);
    HexShape middleRepresentation = new HexShape(0, 0, null);
    Assert.assertEquals(middle.getRow(), middleRepresentation.getRow());
    Assert.assertEquals(middle.getColumn(), middleRepresentation.getColumn());

//    //Middle Hexagon on the 2DArray
//    HexShape bottomLeft = regularBoard.getCurrentHex(6, 0);
//    //Coordinates Assigned to it
//    HexShape btmLeftRep = new HexShape(-3, -3, null);
//    Assert.assertEquals(bottomLeft.getRow(), btmLeftRep.getRow());
//    Assert.assertEquals(bottomLeft.getColumn(), btmLeftRep.getColumn());
//
//
//    HexShape topRightHex = regularBoard.getTopRightHex();
//    //Coordinates Assigned to it based on the textView
//    HexShape topRightRepresentation = new HexShape(-3, 3, null);
//    System.out.println("Expected Row: " + topRightRepresentation.getRow() + ", Expected Column: " + topRightRepresentation.getColumn());
//    String row = topRightHex.getRow();
//    String column = topRightHex.getColumn();
//
//    System.out.println("Expected Row: " + topRightHex.getRow() + ", Expected Column: " + topRightHex.getColumn());
//
//    Point topRightPos = regularBoard.getTopRightHexPosition();
//    System.out.println("Top Right Hex Row: " + topRightPos.y);
//    System.out.println("Top Right Hex Column: " + topRightPos.x);

    HexShape nextBottomLeftOnRight = regularBoard.getCurrentHex(3, 4);
    HexShape nextToBottomLeftOnRightRep = new HexShape(0, 1, null);
    Assert.assertEquals(nextBottomLeftOnRight.getRow(), nextToBottomLeftOnRightRep.getRow());
    Assert.assertEquals(nextBottomLeftOnRight.getColumn(), nextToBottomLeftOnRightRep.getColumn());


    HexShape mostBottomRight = regularBoard.getCurrentHex(6, 3);
    //mostBottomRight(3, 0)

    //0, 3
    System.out.println("Y:" + mostBottomRight.getRow());
    System.out.println("X:" + mostBottomRight.getColumn());

    HexShape topRight = regularBoard.getCurrentHex(0, 6);
    //mostBottomRight(3, 0)

    //0, 3
    System.out.print("X:" + topRight.getColumn());
    System.out.print("Y:" + topRight.getRow());

  }


  /**
   * Tests the Initial board
   * prints out the empty and white/black
   * players correctly.
   */

  @Test
  public void testInitialBoard() {
    TextualController controller = new TextualController(board);
    Assert.assertEquals(controller.toString(),
            "     _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "  _ _ _ _ _ _ _ _ _ \n" +
                    " _ _ _ _ O X _ _ _ _ \n" +
                    "_ _ _ _ X _ O _ _ _ _ \n" +
                    " _ _ _ _ O X _ _ _ _ \n" +
                    "  _ _ _ _ _ _ _ _ _ \n" +
                    "   _ _ _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _ \n" +
                    "     _ _ _ _ _ _ \n");

  }


  /**
   * Tests that the board constructor is not null,
   * and that the size is eleven.
   */

  @Test
  public void testBoard() {
    Assert.assertNotNull(board);
    Assert.assertEquals(11, board.getBoardSize());
  }

//  @Test
//  public void testGetCurrentHex() {
//    HexShape hex = new HexShape(0,0, PlayerType.EMPTY);
//    Assert.assertNotNull(hex);
//  }

  /**
   * Tests that a valid coordinate is passed into the game.
   */
  @Test
  public void testValidCoordinate() {
    Assert.assertTrue(board.isValidCoordinate(0, 0));
    Assert.assertTrue(board.isValidCoordinate(10, 10));
  }


  /**
   * Tests that a valid move is passed into the game.
   */
  @Test
  public void testValidMove() {
    Assert.assertTrue(board.isValidMove(1, -1, PlayerType.WHITE));
  }

  /**
   * Tests that a piece is correctly placed in the board.
   */
  @Test
  public void testPlacePiece() {
    board.placePiece(3, 3, PlayerType.WHITE);
    Assert.assertEquals(PlayerType.WHITE, board.
            getCurrentHex(3, 3).getPlayerType());
  }

  /**
   * Tests that a board is full when it's first started.
   */
  @Test
  public void testBoardFull() {
    Assert.assertFalse(board.isBoardFull());
  }


  /**
   * Tests that the correct amount of pieces
   * is in the game when started.
   */
  @Test
  public void testCountPieces() {
    Assert.assertEquals(0, board.countPieces(PlayerType.WHITE));
  }
}