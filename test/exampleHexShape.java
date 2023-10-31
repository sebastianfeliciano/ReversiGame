import org.junit.Assert;
import org.junit.Test;

import controller.PlayerType;
import model.HexShape;

public class exampleHexShape {

  /**
   * Tests that the columns and rows are being correctly initiated.
   */
  @Test
  public void testColumnAndRow() {
    HexShape hex = new HexShape(7, 7, PlayerType.EMPTY);
    Assert.assertEquals("7", hex.getRow());
    Assert.assertEquals("7", hex.getColumn());
    Assert.assertEquals(hex.getPlayerType(), PlayerType.EMPTY);
  }

  /**
   * Tests that the s value is equal to -q - r.
   */
  @Test
  public void testS() {
    HexShape hex = new HexShape(7, 7, PlayerType.EMPTY);
    Assert.assertEquals(hex.getSValue(), "-14");
  }
}
