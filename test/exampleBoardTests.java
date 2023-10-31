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

//    HexShape topLeft = regularBoard.getCurrentHex(0, 3);
//    HexShape topLeftRepresentation = new HexShape(-3, 0, null);
//    Assert.assertEquals(topLeft.getColumn(), topLeftRepresentation.getColumn());
//
//    HexShape middle = regularBoard.getCurrentHex(3, 3);
//    HexShape middleRepresentation = new HexShape(0, 0, null);
//    Assert.assertEquals(middle.getRow(), middleRepresentation.getRow());
//    Assert.assertEquals(middle.getColumn(), middleRepresentation.getColumn());
//
////    //Middle Hexagon on the 2DArray
////    HexShape bottomLeft = regularBoard.getCurrentHex(6, 0);
////    //Coordinates Assigned to it
////    HexShape btmLeftRep = new HexShape(-3, -3, null);
////    Assert.assertEquals(bottomLeft.getRow(), btmLeftRep.getRow());
////    Assert.assertEquals(bottomLeft.getColumn(), btmLeftRep.getColumn());
////
////
////    HexShape topRightHex = regularBoard.getTopRightHex();
////    //Coordinates Assigned to it based on the textView
////    HexShape topRightRepresentation = new HexShape(-3, 3, null);
////    System.out.println("Expected Row: " + topRightRepresentation.getRow() + ", Expected Column: " + topRightRepresentation.getColumn());
////    String row = topRightHex.getRow();
////    String column = topRightHex.getColumn();
////
////    System.out.println("Expected Row: " + topRightHex.getRow() + ", Expected Column: " + topRightHex.getColumn());
////
////    Point topRightPos = regularBoard.getTopRightHexPosition();
////    System.out.println("Top Right Hex Row: " + topRightPos.y);
////    System.out.println("Top Right Hex Column: " + topRightPos.x);
//
//    HexShape nextBottomLeftOnRight = regularBoard.getCurrentHex(3, 4);
//    HexShape nextToBottomLeftOnRightRep = new HexShape(0, 1, null);
//    Assert.assertEquals(nextBottomLeftOnRight.getRow(), nextToBottomLeftOnRightRep.getRow());
//    Assert.assertEquals(nextBottomLeftOnRight.getColumn(), nextToBottomLeftOnRightRep.getColumn());




    HexShape mostBottomRight = regularBoard.getCurrentHex(6, 3);
    //mostBottomRight(3, 0)

    //0, 3
    System.out.println("Y:" + mostBottomRight.getRow());
    System.out.println("X:" + mostBottomRight.getColumn());



    HexShape topRIght = regularBoard.getCurrentHex(0, 6);
    //mostBottomRight(3, 0)

    //0, 3
    System.out.print("X:" + topRIght.getColumn());
    System.out.print("Y:" + topRIght.getRow());

  }

  @Test
  public void testBoardString() {
    Board board = new Board(11);
    TextualController controller = new TextualController(board);
    Assert.assertEquals(controller.toString(),
              "    _ _ _ _ _ _ \n" +
                    "    _ _ _ _ _ _ _\n" +
                    "   _ _ _ _ _ _ _ _\n" +
                    "  _ _ _ _ _ _ _ _ _\n" +
                    " _ _ _ _ _ _ _ _ _ _\n" +
                    "_ _ _ _ _ _ _ _ _ _ _\n" +
                    " _ _ _ _ _ _ _ _ _ _\n" +
                    "  _ _ _ _ _ _ _ _ _\n" +
                    "   _ _ _ _ _ _ _ _\n" +
                    "    _ _ _ _ _ _ _\n" +
                    "     _ _ _ _ _ _");

  }

}
