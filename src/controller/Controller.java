package controller;

import view.DrawUtils;
import view.FeaturesListener;
import view.ModelStatusInterface;

public class Controller implements FeaturesListener, ModelStatusInterface {
  private Player player;
  private DrawUtils draw;

  public Controller(Player player, DrawUtils draw) {
    this.player = player;
    this.draw = draw;
  }


  @Override
  public void onHexClicked(int row, int column) {
  }

  @Override
  public void onKeyPressed(int keyCode) {
  }

  @Override
  public void onGameStart() {
  }
}