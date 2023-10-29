import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import controller.PlayerType;
import model.Board;
import model.HexShape;
import view.TextualController;

public class exampleBoardTests {

  /**
   * Tests a Board that is given an even number which
   * should not work.
   */
  @Test
  public void testingEvenBoardInvalid() {
    Assert.assertThrows(IllegalStateException.class, () -> new Board(12));
  }

  /**
   * Tests a Board that is given an odd number which
   * should work and be empty.
   */
  @Test
  public void testingOddBoardValid() {
    Board madeBoard = new Board(); //This is default as 11
    for(int i = 0; i < Board.BOARD_SIZE; i++) {
      for(int j = 0; j < Board.BOARD_SIZE; j++) {
        HexShape hexShape = new HexShape(i, j, null); // 'null' assumes PlayerType isn't needed here, adjust as necessary.
        Assert.assertEquals(madeBoard.getCurrentHex(j, i).getPlayerType(), PlayerType.EMPTY);
      }
    }
  }

  /**
   * Tests a Board that is given a negative number which
   * should not work.
   */
  @Test
  public void testingNegativeBoardValid() {
    Assert.assertThrows(IllegalStateException.class, () -> new Board(-6));
  }

  @Test
  public void testingCoordinatesOfAHexagonBasedOn2DArray() {
    Board regularBoard = new Board(7);

    //TopLeft Hexagon on the 2DArray
    HexShape topLeft = regularBoard.getCurrentHex(3, 0);
    //Coordinates Assigned to it
    HexShape topLeftRepresentation = new HexShape(-3, 0, null);
    Assert.assertEquals(topLeft.getColumn(), topLeftRepresentation.getColumn());

    //Middle Hexagon on the 2DArray
    HexShape middle = regularBoard.getCurrentHex(3, 3);
    //Coordinates Assigned to it
    HexShape middleRepresentation = new HexShape(0, 0, null);
    Assert.assertEquals(middle.getRow(), middleRepresentation.getRow());
    Assert.assertEquals(middle.getColumn(), middleRepresentation.getColumn());

    //Middle Hexagon on the 2DArray
    HexShape bottomLeft = regularBoard.getCurrentHex(0, 6);
    //Coordinates Assigned to it
    HexShape btmLeftRep = new HexShape(-3, -3, null);
    Assert.assertEquals(middle.getRow(), middleRepresentation.getRow());
    Assert.assertEquals(middle.getColumn(), middleRepresentation.getColumn());

    //WTFFFFFFF
    //TopRight Hexagon on the 2DArray Q being 6 and R being 0
    HexShape topRightHex = regularBoard.getTopRightHex();
    //Coordinates Assigned to it based on the textView
    HexShape topRightRepresentation = new HexShape(-3, 3, null);
    System.out.println("Expected Row: " + topRightRepresentation.getRow() + ", Expected Column: " + topRightRepresentation.getColumn());
    String row = topRightHex.getRow();
    String column = topRightHex.getColumn();

    System.out.println("Expected Row: " + topRightHex.getRow() + ", Expected Column: " + topRightHex.getColumn());

    Point topRightPos = regularBoard.getTopRightHexPosition();
    System.out.println("Top Right Hex Row: " + topRightPos.y);
    System.out.println("Top Right Hex Column: " + topRightPos.x);


    //Middle Hexagon on the 2DArray
    HexShape nextTibottomLeftOnRight = regularBoard.getCurrentHex(4, 3);
    //Coordinates Assigned to it
    HexShape nextTibottomLeftOnRightRep = new HexShape(0, 1, null);
    Assert.assertEquals(nextTibottomLeftOnRight.getRow(), nextTibottomLeftOnRightRep.getRow());
    Assert.assertEquals(nextTibottomLeftOnRight.getColumn(), nextTibottomLeftOnRightRep.getColumn());

  }
}
