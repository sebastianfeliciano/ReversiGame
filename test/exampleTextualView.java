import org.junit.Assert;
import org.junit.Test;

import view.TextualController;

public class exampleTextualView {

  /**
   * Tests that the controller throws an exception when passed in a null.
   */
  @Test
  public void testController() {
    Assert.assertThrows(IllegalArgumentException.class, () ->
            new TextualController(null));
  }
}

