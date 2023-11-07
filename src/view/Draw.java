package view;

import controller.PlayerType;
import model.Board;
import model.HexShape;
import model.ReadOnlyBoardModel;

import java.awt.*;
import javax.swing.*;

public class Draw extends JPanel {

    static Board board = new Board(11);

    public Draw(ReadOnlyBoardModel board) {
        setPreferredSize(new Dimension(650, 650));
        setBackground(Color.darkGray);
    }

    public int getWindowWidth() {
        System.out.println(this.getWidth());
        return this.getWidth();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g, board);
    }

    public void drawOutline(Graphics g, int centerX, int centerY, int size, PlayerType playerType, double rotation) {
        int sides = 6;
        Polygon hexagon = new Polygon();

        for (int i = 0; i < sides; i++) {
            double angle = 2 * Math.PI / sides * i + rotation;
            int x = (int) (centerX + size * Math.cos(angle));
            int y = (int) (centerY + size * Math.sin(angle));
            hexagon.addPoint(x, y);
        }

        g.setColor(Color.GRAY);
        g.fillPolygon(hexagon);

        g.setColor(Color.BLACK);
        g.drawPolygon(hexagon);
    }

    public void drawCoordinates(Graphics g, HexShape hex, int centerX, int centerY) {
        g.setColor(Color.PINK);
        g.drawString(hex.getRow() + ", "  + hex.getColumn(), centerX, centerY);
    }

    private void drawCircleInHex(Graphics g, int centerX, int centerY, int hexSize, PlayerType playerType) {
        int radius = hexSize / 2;

        Color color;
        switch (playerType) {
            case BLACK:
                color = Color.BLACK;
                break;
            case WHITE:
                color = Color.WHITE;
                break;
            default:
                color = Color.GRAY;
                break;
        }

        g.setColor(color);
        g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }

    public void drawBoard(Graphics g, Board board) {
        int hexSize = getWindowWidth() / 30;
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

                    centerX += hexSize / 2;

                    drawOutline(g, centerX, centerY, hexSize, hexShape.getPlayerType(), Math.PI / 6);
                    drawCircleInHex(g, centerX, centerY, hexSize, hexShape.getPlayerType());
                    drawCoordinates(g, hexShape, centerX - 10, centerY - 4);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hexagon Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Draw(board));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
