package view;


import javax.swing.*;

import controller.Player;
import controller.PlayerType;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.HexShape;
import view.Draw;

public class HexButton extends JButton {
  private HexShape hex;
  private PlayerType type;

  public HexButton(HexShape hex) {
    this.hex = hex;
    hex.setButton(this);
    setBorderPainted(false);
    setContentAreaFilled(false);
    setFocusPainted(false);
    setOpaque(false);
    this.type = PlayerType.EMPTY;

    setPreferredSize(new Dimension(100, 100));
    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        System.out.println(hex.getRow() + ", " + hex.getColumn());
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

  }

  private Polygon createHexagon(int centerX, int centerY, int size) {
    Polygon hexagon = new Polygon();
    for (int i = 0; i < 6; i++) {
      double angle = Math.PI / 3 * i + Math.PI / 6;
      int x = (int) (centerX + size * Math.cos(angle));
      int y = (int) (centerY + size * Math.sin(angle));
      hexagon.addPoint(x, y);
    }
    return hexagon;
  }

  public void setPlayerType(PlayerType playerType) {
    type = playerType;
  }
}
