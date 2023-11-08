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
        System.out.println("Width: " + this.getWidth());
        if (this.getWidth() > 1110){
            return 1110;
        }
        else {
            return this.getWidth();
        }
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
            System.out.println("Angle: "+i+ " Side");
            int x = (int) (centerX + size * Math.cos(angle));
            int y = (int) (centerY + size * Math.sin(angle));
            hexagon.addPoint(x, y);
        }

        g.setColor(Color.LIGHT_GRAY);
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
                color = Color.LIGHT_GRAY;
                break;
        }

        g.setColor(color);
        g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }

    public void drawBoard(Graphics g, Board board) {
        int hexSize = (getWindowHeight() * getWindowWidth()) / 25000;
        int sizeOfEntireBoard = board.getBoardSize();
        int midPoint = sizeOfEntireBoard / 2;

        int hexHeight = (int) (Math.sqrt(3) * hexSize);

        int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);
        int startY = getHeight() / 2 - (sizeOfEntireBoard * hexHeight / 2);

        for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
            int currentHexsMade;

            if (currentRow <= midPoint){
                currentHexsMade = midPoint + currentRow + 1;
            }
            else {
                currentHexsMade = sizeOfEntireBoard - (currentRow - midPoint);
            }

            int spacesBefore = (sizeOfEntireBoard - currentHexsMade);

            for (int h = 0; h < currentHexsMade; h++) {
                HexShape currentHex;
                if (currentRow <= midPoint) {
                    currentHex = board.getCurrentHex(currentRow, spacesBefore + h);
                } else {
                    currentHex = board.getCurrentHex(currentRow, h);
                }
                int offSet = (sizeOfEntireBoard - currentHexsMade) * hexSize;

                int centerX = startX + offSet + h * hexSize * 7/4;
                int centerY = startY + currentRow * hexHeight;

                drawOutline(g, centerX, centerY, hexSize, currentHex.getPlayerType(), Math.PI / 6);
                drawCircleInHex(g, centerX, centerY, hexSize, currentHex.getPlayerType());
                drawCoordinates(g, currentHex, centerX - 10, centerY - 4);
            }
        }
    }

    private int getWindowHeight() {
        System.out.println("Height: " + this.getHeight());
        if (this.getHeight() > 750){
            return 750;
        }
        else {
            return this.getHeight();
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
