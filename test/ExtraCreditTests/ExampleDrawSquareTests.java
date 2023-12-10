package ExtraCreditTests;

import org.junit.Assert;
import org.junit.Test;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import model.BoardModel;
import model.SquareBoard;
import view.DrawSquareUtils;

public class ExampleDrawSquareTests {


  BoardModel board = new SquareBoard(8);
  DrawSquareUtils draw = new DrawSquareUtils(board);

  @Test
  public void testSize() {
    draw.setSize(new Dimension(600, 600));
    Assert.assertEquals(draw.getSize(), new Dimension(600, 600));
  }

  @Test
  public void testInitialization() {
    JFrame frame = new JFrame();
    DrawSquareUtils draw = new DrawSquareUtils(new SquareBoard(12));
    frame.add(draw);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setVisible(true);
    Assert.assertNotNull(draw);
  }

  @Test
  public void testWidthAndHeight() {
    draw.setSize(600, 600);
    Assert.assertEquals(draw.getWindowWidth(), 600);
    Assert.assertEquals(draw.getHeight(), 600);
    draw.setBackground(Color.GRAY);
    Assert.assertEquals(Color.GRAY, draw.getBackground());
  }

  @Test
  public void testGetHexSize() {
    DrawSquareUtils draw = new DrawSquareUtils(new SquareBoard(12));
    draw.setSize(600, 600);
    int hexSize = draw.getHexSize();
    System.out.print(draw.getHexSize());
    Assert.assertTrue(hexSize > 0 && hexSize % 2 == 0);
  }

  @Test
  public void testIsPointInHex() {
    DrawSquareUtils draw = new DrawSquareUtils(new SquareBoard(12));
    int x = draw.getWidth() / 2;
    int y = draw.getHeight() / 2;

    int hexSize = draw.getHexSize();
    boolean isPointInHex = draw.isPointInHex(x, y, x, y, hexSize);
    Assert.assertTrue(isPointInHex);
  }

  @Test
  public void testGetWindowWidth() {
    DrawSquareUtils draw = new DrawSquareUtils(new SquareBoard(12));

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(draw);

    draw.setPreferredSize(new Dimension(650, 650));

    frame.pack();
    frame.setVisible(true);

    int windowWidth = draw.getWindowWidth();
    System.out.println(draw.getWindowWidth());

    Assert.assertEquals(650, windowWidth);
  }


  @Test
  public void testGetWindowHeight() {
    DrawSquareUtils draw = new DrawSquareUtils(new SquareBoard(12));
    draw.setSize(600, 600);
    int windowHeight = draw.getWindowHeight();
    Assert.assertEquals(windowHeight, 600);
  }


  @Test
  public void testDraw() {
    draw.setSize(600, 600);
    Assert.assertEquals(new Color(0, 34, 83), draw.getBackground());
  }
}
