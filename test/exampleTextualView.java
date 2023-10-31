import org.junit.Assert;
import org.junit.Test;

import view.TextualController;

public class exampleTextualView {
  @Test
  public void testController() {
    Assert.assertThrows(IllegalArgumentException.class, () ->
            new TextualController(null));
  }
}

