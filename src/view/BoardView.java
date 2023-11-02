package view;
import controller.PlayerType;
import model.Board;
import model.HexShape;
import model.ReadOnlyBoardModel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.io.IOException;

public class BoardView extends JPanel {
    private ReadOnlyBoardModel board;
    public BoardView(ReadOnlyBoardModel board) {
        this.board = board;
        this.setPreferredSize(new Dimension(800, 600));
    }

//    @Override
//    public void render() throws IOException {
//
//    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int width = 50;
        int height = (int) (Math.sqrt(3) / 2 * width);

        for (int row = 0; row < board.getBoardSize(); row++) {
            for (int col = 0; col < board.getBoardSize(); col++) {
                HexShape hex = board.getCurrentHex(row, col);
                if (hex != null) {
                    int x = col * width * 3/4;
                    int y = row * height +
                            (col % 2 == 0 ? 0 : height / 2);
                    drawHexagon(g2, x, y, width, hex.getPlayerType());
                }
            }
        }
    }

    private void drawHexagon(Graphics2D g2, int x, int y, int size, PlayerType type) {
        int row = size / 2;
        int r2 = (int) (Math.sqrt(3) / 2 * row);
        GeneralPath hexagon = new GeneralPath();
        hexagon.moveTo(x + row, y);
        hexagon.lineTo(x + row / 2, y + r2);
        hexagon.lineTo(x - row / 2, y + r2);
        hexagon.lineTo(x - row, y);
        hexagon.lineTo(x - row / 2, y - r2);
        hexagon.lineTo(x + row / 2, y - r2);
        hexagon.closePath();

        if (type != null) {
            switch (type) {
                case WHITE:
                    g2.setColor(Color.WHITE);
                    break;
                case BLACK:
                    g2.setColor(Color.black);
                    break;
                default:
                    g2.setColor(Color.gray);
                    break;
            }
            g2.fill(hexagon);
        }
        g2.setColor(Color.DARK_GRAY);
        g2.draw(hexagon);
    }
}
