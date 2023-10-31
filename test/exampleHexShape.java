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

  @Test
  public void testGetSValue() {
    HexShape hex = new HexShape(7, 7, PlayerType.EMPTY);
    Assert.assertEquals(hex.getSValue(), "-14");
    HexShape hexExample = new HexShape(-1, 0, PlayerType.EMPTY);
    Assert.assertEquals(hexExample.getSValue(), "1");
  }

  @Test
  public void testGetPlayerType() {
    HexShape hex = new HexShape(7, 7, PlayerType.EMPTY);
    Assert.assertEquals(hex.getPlayerType(), PlayerType.EMPTY);
    HexShape stuff = new HexShape(7, 7, null);
    Assert.assertEquals(stuff.getPlayerType(), PlayerType.EMPTY);
    HexShape stuffWithBlack = new HexShape(7, 7, PlayerType.BLACK);
    Assert.assertEquals(stuffWithBlack.getPlayerType(), PlayerType.BLACK);
  }


  @Test
  public void testSetPlayerType() {
    HexShape hex = new HexShape(7, 7, PlayerType.EMPTY);
    Assert.assertEquals(hex.getPlayerType(), PlayerType.EMPTY);
    HexShape stuff = new HexShape(7, 7, null);
    Assert.assertEquals(stuff.getPlayerType(), PlayerType.EMPTY);
    HexShape stuffWithBlack = new HexShape(7, 7, PlayerType.BLACK);
    Assert.assertEquals(stuffWithBlack.getPlayerType(), PlayerType.BLACK);

    hex.setPlayerType(PlayerType.BLACK);
    Assert.assertEquals(hex.getPlayerType(), PlayerType.BLACK);

    stuff.setPlayerType(PlayerType.BLACK);
    Assert.assertEquals(stuff.getPlayerType(), PlayerType.BLACK);

    stuffWithBlack.setPlayerType(PlayerType.WHITE);
    Assert.assertEquals(stuffWithBlack.getPlayerType(), PlayerType.WHITE);

  }
}
