import org.junit.Assert;
import org.testng.annotations.Test;

import controller.AIPlayer;
import controller.Player;
import controller.PlayerType;
import model.Board;
import model.Move;
import model.strategies.CaptureStrategy;

public class ExampleCaptureStrategies {

  @Test
  public void testTopRightChosenFromStartOfGame(){
    CaptureStrategy strategy = new CaptureStrategy();
    Board mockBoard = new Board(7);
    AIPlayer player1 = new AIPlayer("Player 1", PlayerType.WHITE, mockBoard, strategy);
    Player player2 = new Player("Player 2", PlayerType.BLACK, mockBoard);
    Move selectedMove = strategy.selectMove(mockBoard, player1);
    Move expectedMove = new Move(1,-2,1);
    Assert.assertEquals(expectedMove, selectedMove);
    Move selectedMove2 = strategy.selectMove(mockBoard, player2);
    Assert.assertEquals(expectedMove, selectedMove2);
  }

}
