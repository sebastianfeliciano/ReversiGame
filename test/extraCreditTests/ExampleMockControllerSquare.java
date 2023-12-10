package extraCreditTests;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.JLabel;

import controller.MockController;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.ReadOnlyBoardModel;
import model.SquareBoard;
import view.DrawUtils;

/**
 * A list of test using mock controller on a square game.
 */
public class ExampleMockControllerSquare {
  @Test
  public void testOnPass() {
    ReadOnlyBoardModel board = new SquareBoard(8);
    Player player = new Player("Human", PlayerType.BLACK, board);
    DrawUtils view = new DrawUtils(board);
    JLabel score = new JLabel("Dummy Score");
    view.setScoreLabel(score);
    Board boardReg = board.getRegularBoard();
    MockController controller1 = new MockController(player, boardReg, view);
    player.setMoveHandler(controller1);
    controller1.onPass();
    Assert.assertTrue(controller1.getLog().toString().contains("Player passed"));
  }


  @Test
  public void testOnPlayerMove() {
    ReadOnlyBoardModel board = new SquareBoard(8);
    Player player = new Player("Human", PlayerType.BLACK, board);
    DrawUtils view = new DrawUtils(board);
    JLabel score = new JLabel("Dummy Score");
    view.setScoreLabel(score);
    Board boardReg = board.getRegularBoard();
    MockController controller1 = new MockController(player, boardReg, view);
    player.setMoveHandler(controller1);
    controller1.onPlayerMove(4, 2);
    Assert.assertTrue(controller1.getLog().toString().contains("Player moved to row"));
  }
}
