import controller.PlayerType;
import model.Board;
import org.junit.Assert;
import org.junit.Test;
import view.TextualController;

public class exampleBoardTests {

  @Test
  public void testPlayerType() {
    Board board = new Board();
    Assert.assertEquals(board.getCurrentCell(1, 1), PlayerType.EMPTY);
  }

  @Test
  public void testBoardString() {
    Board board = new Board();
    TextualController controller = new TextualController(board);
    Assert.assertEquals(controller.toString(),
            "     _ _ _ _ _ _ \n" +
            "    _ _ _ _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ \n" +
            "  _ _ _ _ _ _ _ _ _ \n" +
            " _ _ _ _ _ _ _ _ _ _ \n" +
            "_ _ _ _ _ _ _ _ _ _ _ \n" +
            " _ _ _ _ _ _ _ _ _ _ \n" +
            "  _ _ _ _ _ _ _ _ _ \n" +
            "   _ _ _ _ _ _ _ _ \n" +
            "    _ _ _ _ _ _ _ \n" +
            "     _ _ _ _ _ _ ");
  }
}
