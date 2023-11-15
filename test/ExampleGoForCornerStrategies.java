import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import controller.Player;
import controller.PlayerType;
import model.Board;
import model.Move;
import model.strategies.GoForCornersStrategy;

public class ExampleGoForCornerStrategies {

//  @Test
//  public void testChoosingCorners() {
//    List<Move> validMovesDone = new ArrayList<>();
//    validMovesDone.add(new Move(0, 0));
//    validMovesDone.add(new Move(3, 3));
//    Mock board = new Mock(validMovesDone);
//    Player player1 = new Player("Player 1", PlayerType.WHITE, board);
//    Player player2 = new Player("Player 1", PlayerType.BLACK, board);
//
//    GoForCornersStrategy strategy = new GoForCornersStrategy();
//    Move selectedMove = strategy.selectMove(board, player1);
//
//    Assert.assertEquals(new Move(0, 0), selectedMove); // Assert that the corner move was chosen
//  }
}

