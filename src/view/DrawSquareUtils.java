package view;

import java.awt.Color;
import java.awt.Graphics;

import model.Board;
import model.Shape;
import controller.players.PlayerType;
import model.ReadOnlyBoardModel;

/**
 * The Class that draws the square game out.
 */
public class DrawSquareUtils extends DrawUtils implements ReversiView, DrawInterfaceMocker {
  ReadOnlyBoardModel board;

  public DrawSquareUtils(ReadOnlyBoardModel board) {
    super(board);
    this.board = board;
  }

  @Override
  public void drawBoard(Graphics g, Board board) {
    int squareSize = getSquareSize();
    int sizeOfEntireBoard = board.getBoardSize();

    for (int row = 0; row < sizeOfEntireBoard; row++) {
      for (int col = 0; col < sizeOfEntireBoard; col++) {
        Shape square = board.getCurrentHex(row, col);
        int x = col * squareSize;
        int y = row * squareSize;

        drawEachHexagon(g, square, x, y, squareSize, square.getPlayerType());
      }
    }
  }

  @Override
  public void drawEachHexagon(Graphics g, Shape hex, int x, int y,
                              int squareSize, PlayerType playerType) {
    // Determine the color of the square based on the player type
    if (hex.equals(getFirstClickedHex())) {
      g.setColor(Color.CYAN);
    } else if (hex.equals(getHoveredHex())) {
      g.setColor(Color.GREEN);
    } else {
      g.setColor(Color.LIGHT_GRAY);
    }


    g.fillRect(x, y, squareSize, squareSize);

    g.setColor(Color.BLACK);
    g.drawRect(x, y, squareSize, squareSize);


    if (hex.getPlayerType() != PlayerType.EMPTY) {
      g.setColor(getColor(playerType));
      int pieceDiameter = (int) (squareSize * 0.8); // The piece will cover 80% of the square
      int offsetX = (squareSize - pieceDiameter) / 2; // Center the piece inside the square
      int offsetY = (squareSize - pieceDiameter) / 2; // Center the piece inside the square
      g.fillOval(x + offsetX, y + offsetY, pieceDiameter, pieceDiameter);
    }
  }

  @Override
  public Shape findHex(int mouseX, int mouseY) {
    int squareSize = getSquareSize();
    int sizeOfEntireBoard = board.getBoardSize();

    for (int row = 0; row < sizeOfEntireBoard; row++) {
      for (int col = 0; col < sizeOfEntireBoard; col++) {
        int x = col * squareSize;
        int y = row * squareSize;

        if (isPointInSquare(mouseX, mouseY, x, y, squareSize)) {
          return board.getCurrentHex(row, col);
        }
      }
    }
    return null;
  }

  public boolean isPointInSquare(int x, int y, int squareX, int squareY, int squareSize) {
    return x >= squareX && x < squareX + squareSize && y >= squareY && y < squareY + squareSize;
  }


  public int getSquareSize() {
    return Math.min(getWindowWidth() / board.getBoardSize(),
            getWindowHeight() / board.getBoardSize()) - 10;
  }
}
