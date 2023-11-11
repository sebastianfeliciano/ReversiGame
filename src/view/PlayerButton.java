package view;


import javax.swing.*;

import controller.PlayerType;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import model.Board;
import model.HexShape;

public class PlayerButton extends JButton implements MouseListener {
  private HexShape hex;
  private PlayerType type;
  private Board b;


  public PlayerButton(HexShape hex) {
    this.hex = hex;
    hex.setButton(this);
    setBorderPainted(false);
    setContentAreaFilled(false);
    setFocusPainted(false);
    setOpaque(false);
    this.type = PlayerType.EMPTY;
    Board b1 = this.b;

    setPreferredSize(new Dimension(100, 100));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

  }
  public void setPlayerType(PlayerType playerType) {
    this.type = playerType;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
