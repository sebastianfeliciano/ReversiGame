package extraCreditTests;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Move;
import model.ReadOnlyBoardModel;
import model.SquareBoard;
import model.strategies.CaptureStrategy;
import model.strategies.GoForCornersStrategy;
import model.strategies.TryTwo;

/**
 * A list of tests using chained strategies on a square board.
 */
public class ExampleTryTwoSquare {
  @Test
  public void testingCaptureStrategyFirst() {
    TryTwo easy = new TryTwo(new CaptureStrategy(), new GoForCornersStrategy());
    SquareBoard mockBoard = new SquareBoard(8);
    Player player1 = new Player("Player 2", PlayerType.WHITE, mockBoard);
    AIPlayer player2 = new AIPlayer("Player 1", PlayerType.BLACK, mockBoard, easy);
    player1.makeMove();
    ReadOnlyBoardModel copy = mockBoard.getReadOnlyBoard();
    Optional<Move> selectedMove = easy.selectMove(copy, player2);
    Optional<Move> expectedMove = Optional.of(new Move(4, 2, 1));
    Assert.assertEquals(selectedMove.get().getX(), expectedMove.get().getX());
  }

  @Test
  public void testingGoForCornersFirst() {
    TryTwo easy = new TryTwo(new GoForCornersStrategy(), new CaptureStrategy());
    SquareBoard mockBoard = new SquareBoard(8);
    Player player1 = new Player("Player 2", PlayerType.WHITE, mockBoard);
    AIPlayer player2 = new AIPlayer("Player 1", PlayerType.BLACK, mockBoard, easy);
    player1.makeMove();
    ReadOnlyBoardModel copy = mockBoard.getReadOnlyBoard();
    Optional<Move> selectedMove = easy.selectMove(copy, player2);
    Optional<Move> expectedMove = Optional.of(new Move(5, 3, 1));
    Assert.assertEquals(selectedMove.get().getX(), expectedMove.get().getX());
  }

  @Test
  public void testingDoingTheSecondStrategy() {
    TryTwo easy = new TryTwo((dummyBoard, dummyPlayer) -> Optional.empty(), new CaptureStrategy());
    SquareBoard mockBoard = new SquareBoard(8);
    Player player1 = new Player("Player 2", PlayerType.WHITE, mockBoard);
    AIPlayer player2 = new AIPlayer("Player 1", PlayerType.BLACK, mockBoard, easy);
    player1.makeMove();
    ReadOnlyBoardModel copy = mockBoard.getReadOnlyBoard();
    Optional<Move> selectedMove = easy.selectMove(copy, player2);
    Optional<Move> expectedMove = Optional.of(new Move(4, 2, 1));
    Assert.assertEquals(selectedMove.get().getX(), expectedMove.get().getX());
  }


}
