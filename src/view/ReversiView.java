package view;

import controller.PlayerType;
import model.Board;
import model.HexShape;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Represents the view of a Reversi Board.
 */
public interface ReversiView {

  HexShape findHex(int mouseX, int mouseY);

  int getHexSize();

  boolean isPointInHex(int x, int y, int centerX, int centerY, int hexSize);

  int getWindowWidth();

  void drawEachHexagon(Graphics g, HexShape hex,
                       int centerX, int centerY, int hexSize, PlayerType playerType);

  Color getColor(PlayerType playerType);

  void drawBoard(Graphics g, Board board);

  int getWindowHeight();
}
