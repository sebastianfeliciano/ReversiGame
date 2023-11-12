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
}