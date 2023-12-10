package model;

import controller.players.PlayerType;
import view.PlayerButton;

/**
 * Interface that represents a single shape.
 */
public interface Shape {

  PlayerType getPlayerType();


  /**
   * Method that will help for testing.
   */
  String getColumn();

  /**
   * Returns the row value.
   */
  String getRow();


  PlayerType setPlayerType(PlayerType playerType);

  void setButton(PlayerButton playerButton);
}
