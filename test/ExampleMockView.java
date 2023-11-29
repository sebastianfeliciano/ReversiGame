import org.junit.Assert;
import org.junit.Test;

import javax.swing.*;

import controller.MockController;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.ReadOnlyBoardModel;
import view.DrawUtils;
import view.MockViewClass;

public class ExampleMockView {

  @Test
  public void testHandleGameOver() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);
    Player player2 = new Player("Human2", PlayerType.WHITE, board);

    Board boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);

    view.handleGameOver();

    Assert.assertTrue(view.getLog().contains("Handled Game Over"));
  }

  @Test
  public void testShowInvalidMoveMessage() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);

    Board boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);

    MockController controller1 = new MockController(player, boardReg, view);

    player.setMoveHandler(controller1);

    controller1.onPlayerMove(-3,0);
    Assert.assertTrue(view.getLog().contains("Invalid Move Pop Up"));
  }


  @Test
  public void testShowThatIPassedTurnMessage() {
    ReadOnlyBoardModel board = new Board(7);
    Player player = new Player("Human", PlayerType.BLACK, board);

    Board boardReg = board.getRegularBoard();
    MockViewClass view = new MockViewClass(board);

    MockController controller1 = new MockController(player, boardReg, view);

    player.setMoveHandler(controller1);

    controller1.onPlayerMove(-3,0);
    Assert.assertTrue(view.getLog().contains("Invalid Move Pop Up"));

  }

  @Test
  public void testItIsNowYourTurnMessage() {

  }

  @Test
  public void testGetGameOverHandleState() {

  }


  @Test
  public void testResetGameOverHandled() {

  }


  @Test
  public void testItIsNotYourTurnMessage() {
  }


  @Test
  public void testUpdateScore() {

  }

  @Test
  public void testSetScoreLabel() {

  }


}
