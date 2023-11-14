import controller.AIPlayer;
import controller.Player;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import controller.PlayerType;
import model.Board;
import model.HexShape;
import model.Move;
import view.TextualController;

public class exampleBoardTests {

  Board board = new Board(11);

  /**
   * Tests that two flips are being correctly made.
   */
  @Test
  public void testingTwoFlips() {
    Board board = new Board(7);
    Player player1 = new Player("S", PlayerType.WHITE, board);
    Player player2 = new Player("E", PlayerType.WHITE, board);
    PlayerType playerOneType = PlayerType.BLACK; // Assuming Player One is BLACK
    PlayerType playerTwoType = PlayerType.WHITE; // Assuming Player Two is WHITE

    player1.placeKey(-1, -1); // Player One places piece at (-1,-1)
    player2.setHasPassed();       // Player Two passes
    player1.placeKey(-2, 1);  // Player One places piece at (-2,1)

    PlayerType hexAt42PlayerType = board.getCurrentHex(2, 4).getPlayerType();
    if (hexAt42PlayerType == playerOneType) {
      System.out.println("The hex at (4,2) matches Player One's type.");
    } else {
      System.out.println("The hex at (4,2) does NOT match Player One's type. It's " + hexAt42PlayerType);
    }
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


//    public List<Move> getValidMovesWithCaptures(PlayerType player) {
//        List<Move> validMoves = new ArrayList<>();
//        for (int currentRow = 0; currentRow < BOARD_SIZE; currentRow++) {
//            for (int currentColumn = 0; currentColumn < BOARD_SIZE; currentColumn++) {
//                System.out.println(getCurrentHex(currentRow,currentColumn));
//            }
//        }
//        return validMoves;
//    }


  @Test
  public void testCalculatePiecesWithAValidMove() {
    Board defaultBoard = new Board(7);
    Player player1 = new Player("Player 1", PlayerType.WHITE, defaultBoard);
    Player player2 = new Player("Player 2", PlayerType.BLACK, defaultBoard);
    int k = defaultBoard.calculateCaptures(-1, -1, player1.getType(), defaultBoard);
    Assert.assertEquals(k, 1);
  }

  @Test
  public void testGetValidMovesWithCaptures() {
    Board defaultBoard = new Board(7);
    Player player1 = new Player("Player 1", PlayerType.WHITE, defaultBoard);
    Player player2 = new Player("Player 2", PlayerType.BLACK, defaultBoard);
    List<Move> validMoves = defaultBoard.getValidMovesWithCaptures(player1);

    // Define the expected moves
    List<Move> expectedMoves = new ArrayList<>();
    expectedMoves.add(new Move(-1, -1, 1));
    expectedMoves.add(new Move(-2, 1, 1));
    expectedMoves.add(new Move(-1, 2, 1));
    expectedMoves.add(new Move(1, 1, 1));
    expectedMoves.add(new Move(2, -1, 1));
    expectedMoves.add(new Move(1, -2, 1));

    Assert.assertEquals(expectedMoves.size(), validMoves.size());

    for (Move expectedMove : expectedMoves) {
      Assert.assertTrue(validMoves.contains(expectedMove));

    }

    for (Move validMove : validMoves) {
      Assert.assertTrue(expectedMoves.contains(validMove));
    }
  }

  }



