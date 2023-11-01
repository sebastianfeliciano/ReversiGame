import controller.Player;
import org.junit.Assert;
import org.junit.Test;

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


    HexShape bottomLeft = regularBoard.getCurrentHex(6, 0);
    HexShape btmLeftRep = new HexShape(3, -3, null);
    Assert.assertEquals(bottomLeft.getRow(), btmLeftRep.getRow());
    Assert.assertEquals(bottomLeft.getColumn(), btmLeftRep.getColumn());

    HexShape topRightHex = regularBoard.getCurrentHex(0, 6);
    HexShape topRightRepresentation = new HexShape(-3, 3, null);
    Assert.assertEquals(topRightHex.getRow(), topRightRepresentation.getRow());
    Assert.assertEquals(topRightHex.getColumn(), topRightRepresentation.getColumn());

    HexShape rightMiddle = regularBoard.getCurrentHex(3, 4);
    HexShape rightMiddleRep = new HexShape(0, 1, null);
    Assert.assertEquals(rightMiddle.getRow(), rightMiddleRep.getRow());
    Assert.assertEquals(rightMiddle.getColumn(), rightMiddleRep.getColumn());
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
    Board board1 = new Board(7);
<<<<<<< HEAD
    Assert.assertTrue(board1.isValidMove(-1, -1, PlayerType.WHITE));
=======
    Assert.assertTrue(board1.isValidMove(1, -1, PlayerType.WHITE));
>>>>>>> main
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
  public void testBoardFullInitalState() {
    Assert.assertFalse(board.isBoardFull());
  }


  /**
   * Tests that the correct amount of pieces
   * is in the game when started.
   */
  @Test
  public void testInitialCountPieces() {
    Assert.assertEquals(3, board.countPieces(PlayerType.WHITE));
    Assert.assertEquals(3, board.countPieces(PlayerType.BLACK));
  }

  /**
   * Tests that the count of hex shapes gets correctly updated, when a piece is placed.
   */
  @Test
  public void testCount() {
    Board board = new Board(11);
    Player player1 = new Player("e", PlayerType.WHITE, board);

    player1.placeKey(-1, -1);

    board.getCurrentHex(11 / 2, 11 / 2 + 1).setPlayerType(PlayerType.BLACK);
    board.getCurrentHex(11 / 2 + 1, 11 / 2).setPlayerType(PlayerType.WHITE);
    board.getCurrentHex(11 / 2, 11 / 2 - 1).setPlayerType(PlayerType.WHITE);
    board.getCurrentHex(11 / 2 + 1, 11 / 2 - 1).setPlayerType(PlayerType.BLACK);
    board.getCurrentHex(11 / 2 - 1, 11 / 2).setPlayerType(PlayerType.BLACK);
    board.getCurrentHex(11 / 2 - 1, 11 / 2 + 1).setPlayerType(PlayerType.WHITE);

    Assert.assertEquals(4, board.countPieces(PlayerType.WHITE));
    Assert.assertEquals(board.getBoardSize(), 11);
  }

  /**
   * Tests that the flip pieces method works correctly.
   */
  @Test
  public void testFlipPieces() {
    Board oldBoard = new Board(7);
    Player player1 = new Player("E", PlayerType.WHITE, oldBoard);
    Player player2 = new Player("S", PlayerType.BLACK, oldBoard);

    HexShape initialHex = oldBoard.getCurrentHex(3, 2);
    PlayerType initialType = initialHex.getPlayerType();
    Assert.assertEquals(PlayerType.EMPTY, initialType);

    player1.placeKey(-1,-1);

    HexShape postFlipHex = oldBoard.getCurrentHex(3, 2);
    PlayerType postFlipType = postFlipHex.getPlayerType();
    Assert.assertEquals(PlayerType.WHITE, postFlipType);
  }

  /**
   * Tests whether both players have passed their move.
   */
  @Test
  public void testBothPlayersPassed() {
    Board board = new Board();

    Player player1 = new Player("e", PlayerType.WHITE, board);
    Player player2 = new Player("s", PlayerType.BLACK, board);
    player1.setHasPassed(true);
    player2.setHasPassed(true);
    Assert.assertTrue(player1.hasPassed());
    Assert.assertTrue(player2.hasPassed());
  }

  /**
   * Tests whether a player has any more valid moves.
   */
  @Test
  public void testTrapped() {
    Board board = new Board();

    HexShape hex = new HexShape(0,0, null);
    hex.setPlayerType(PlayerType.EMPTY);

    Player player1 = new Player("e", PlayerType.WHITE, board);
    Player player2 = new Player("s", PlayerType.BLACK, board);
    Assert.assertFalse(board.isBoardFull());

  }
}