import model.Board;
import org.junit.Assert;
import org.junit.Test;
import view.Draw;

import java.awt.*;

public class ExampleDrawTests {

    Board board = new Board();
    Draw draw = new Draw(board);
    @Test
    public void testSize() {
        draw.setSize(new Dimension(600,600));
        Assert.assertEquals(draw.getSize(), new Dimension(600,600));
    }

    @Test
    public void testWidthAndHeight() {
        draw.setSize(600,600);
        Assert.assertEquals(draw.getWindowWidth(), 600);
        Assert.assertEquals(draw.getHeight(), 600);
        draw.setBackground(Color.GRAY);
        Assert.assertEquals(Color.GRAY, draw.getBackground());
    }

    @Test
    public void testDraw() {
        draw.setSize(600,600);
        Assert.assertEquals(new Color(0,34,83), draw.getBackground());
    }
}