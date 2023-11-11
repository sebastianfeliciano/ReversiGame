package view;

import model.HexShape;

public class Polygon extends java.awt.Polygon {
  HexShape hex;

  public Polygon(HexShape hex){
    this.hex = hex;
  }
}
