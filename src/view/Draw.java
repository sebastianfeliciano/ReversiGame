package view;

import controller.PlayerType;
import model.Board;
import model.HexShape;
import model.ReadOnlyBoardModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.*;

public class Draw extends JPanel {

  static Board board = new Board(11);
  public HexShape[][] cellsThatMakeTheBoard;

  public Draw(ReadOnlyBoardModel board) {
    setPreferredSize(new Dimension(650, 650));
    setBackground(new Color(this.getWindowWidth() / 11, 34, 83));
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        System.out.println(" E X: " + e.getX());
        System.out.println(" E Y: " + e.getY());
        //findHex(e.getX(), e.getY());
        System.out.println("Hex Object: "+findHex(e.getX(), e.getY()));
        System.out.print("Hex X: "+ Objects.requireNonNull(findHex(e.getX(), e.getY())).getRow());
        System.out.print("Hex Y: "+Objects.requireNonNull(findHex(e.getX(), e.getY())).getColumn());

      }
    });
  }

  private HexShape findHex(int mouseX, int mouseY) {
    int hexSize = (getWindowHeight() * getWindowWidth()) / 15000;
    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = sizeOfEntireBoard / 2;
    double hexHeight = 3.0 / 2 * hexSize;
    int horizontalDistanceBetweenAdjacentHexagonCenters = (int) (Math.sqrt(3) * hexSize);
    int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);
    int startY = getHeight() / 2 - (sizeOfEntireBoard * horizontalDistanceBetweenAdjacentHexagonCenters / 2);

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int currentHexsMade;
      if (currentRow <= midPoint) {
        currentHexsMade = midPoint + currentRow + 1;
      } else {
        currentHexsMade = sizeOfEntireBoard - (currentRow - midPoint);
      }

      int spacesBefore = (sizeOfEntireBoard - currentHexsMade);

      for (int h = 0; h < currentHexsMade; h++) {
        int offSet = (sizeOfEntireBoard - currentHexsMade) * horizontalDistanceBetweenAdjacentHexagonCenters / 2;

        int centerX = startX + offSet + h * horizontalDistanceBetweenAdjacentHexagonCenters; //Moves the chain from top to bottom left
        int centerY = (int) Math.round(startY + currentRow * hexHeight);

        if (isPointInHex(mouseX, mouseY, centerX, centerY, hexSize)) {
          if (currentRow <= midPoint) {
            return board.getCurrentHex(currentRow, h + spacesBefore);
          } else {
            return board.getCurrentHex(currentRow, h);
          }
        }
      }
    }
    return null;
  }


  private boolean isPointInHex(int x, int y, int centerX, int centerY, int hexSize) {
    double xDistance = Math.abs(x - centerX);
    double yDistance = Math.abs(y - centerY);

    if (xDistance > hexSize * Math.sqrt(3) / 2) {
      return false;
    }
    if (yDistance > (double) (hexSize * 3) / 2) {
      return false;
    }
    if (yDistance > (double) hexSize / 2){
      return xDistance * Math.sqrt(3) / 3 + (double) hexSize / 2 < yDistance;
    }
    return true;
  }


  public int getWindowWidth() {
    if (this.getWidth() > 650) {
      return 650;
    } else {
      return this.getWidth();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawBoard(g, board);
  }

  public void drawEachHexagon(Graphics g, HexShape hex, int centerX, int centerY, int hexSize, PlayerType playerType) {
    int sides = 6;
    Polygon hexagon = new Polygon();

    for (int i = 0; i < sides; i++) {
      double angle = 2 * Math.PI / sides * i + Math.PI / 6;
      int x = (int) (centerX + hexSize * Math.cos(angle));
      int y = (int) (centerY + hexSize * Math.sin(angle));
      hexagon.addPoint(x, y);
      System.out.println("Hex X " + i + ": " + x);
      System.out.println("Hex Y: " + y);
    }

    g.setColor(Color.LIGHT_GRAY);
    g.fillPolygon(hexagon);

    g.setColor(Color.BLACK);
    g.drawPolygon(hexagon);

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


  public void drawCoordinates(Graphics g, HexShape hex, int centerX, int centerY) {
    g.setColor(Color.PINK);
    g.drawString(hex.getRow() + ", " + hex.getColumn(), centerX, centerY);
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
    int hexSize = (getWindowHeight() * getWindowWidth()) / 15000;
    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = sizeOfEntireBoard / 2;

    int horizontalDistanceBetweenAdjacentHexagonCenters = (int) (Math.sqrt(3) * hexSize);

    double hexHeight = 3.0 / 2 * hexSize;
    double theVerticalDistance = 3.0 / 4 * hexHeight;

    int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);
    int startY = getHeight() / 2 - (sizeOfEntireBoard * horizontalDistanceBetweenAdjacentHexagonCenters / 2);

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int currentHexsMade;

      if (currentRow <= midPoint) {
        currentHexsMade = midPoint + currentRow + 1;
      } else {
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
        int offSet = (sizeOfEntireBoard - currentHexsMade) * horizontalDistanceBetweenAdjacentHexagonCenters / 2;

        int centerX = startX + offSet + h * horizontalDistanceBetweenAdjacentHexagonCenters; //Moves the chain from top to bottom left
        int centerY = (int) Math.round(startY + currentRow * hexHeight); //Moves chain from left to right

        PlayerButton playerButton = new PlayerButton(currentHex);
        playerButton.setBounds(centerX - hexSize / 2, centerY - hexSize / 2, hexSize, hexSize);
        //add(playerButton);
        playerButton.setVisible(true);

        drawEachHexagon(g, currentHex, centerX, centerY, hexSize, currentHex.getPlayerType());
        drawCircleInHex(g, centerX, centerY, hexSize, currentHex.getPlayerType());
        drawCoordinates(g, currentHex, centerX - 10, centerY - 4);
      }
    }
  }

  private int getWindowHeight() {
    if (this.getHeight() > 650) {
      return 650;
    } else {
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
