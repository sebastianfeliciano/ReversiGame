import org.junit.Assert;
import org.junit.Test;

import controller.PlayerType;
import view.TextualController;

public class examplePlayerTests {

  /**
   * Tests that player type strings are correct.
   */
  @Test
  public void testPlayerType() {
    Assert.assertEquals(PlayerType.WHITE.toString(), "X");
    Assert.assertEquals(PlayerType.BLACK.toString(), "O");
    Assert.assertEquals(PlayerType.EMPTY.toString(), "_");
  }

  /**
   * Tests that the textual controller throws an exception,
   * when started as null.
   */
  @Test
  public void testController() {
    Assert.assertThrows(IllegalArgumentException.class, () ->
            new TextualController(null));
  }


}
