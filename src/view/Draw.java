package view;

import controller.PlayerType;
import model.Board;
import model.HexShape;

import java.awt.*;
import javax.swing.*;

public class Draw extends JPanel {

    Board board = new Board(11);

    public Draw() {
        setPreferredSize(new Dimension(650, 650));
        setBackground(Color.darkGray);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBoard(g, board);

    }

    public void drawHexagon(Graphics g, int centerX, int centerY, int size, PlayerType playerType) {
        int sides = 6;

        Polygon hexagon = new Polygon();

        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI / sides * i;
            int x = (int) (centerX + size * Math.cos(angle));
            int y = (int) (centerY + size * Math.sin(angle));
            hexagon.addPoint(x, y);
        }

        if (playerType == PlayerType.BLACK) {
            g.setColor(Color.BLACK);
        } else if (playerType == PlayerType.WHITE) {
            g.setColor(Color.WHITE);
        } else {
            g.setColor(Color.GRAY);
        }

        g.fillPolygon(hexagon);

        g.setColor(Color.BLACK);
        g.drawPolygon(hexagon);
    }


    public void drawBoard(Graphics g, Board board) {
        int hexSize = 30;
        int boardSize = board.getBoardSize();
        int midPoint = boardSize / 2;
        int hexHeight = (int) (Math.sqrt(3) * hexSize);

        int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);
        int startY = getHeight() / 2 - (boardSize * hexHeight / 2);

        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                HexShape hexShape = board.getCurrentHex(row, col);
                if (hexShape != null) {
                    int centerX = startX + col * hexSize * 3 / 2;
                    int centerY = startY + row * hexHeight;

                    if (col % 2 == 0) {
                        centerY += hexHeight / 2;
                    }

                    drawHexagon(g, centerX, centerY, hexSize, hexShape.getPlayerType());
                }
            }
        }
    }



    public static void main(String[] args) {
        JFrame frame = new JFrame("Hexagon Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new Draw());

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
