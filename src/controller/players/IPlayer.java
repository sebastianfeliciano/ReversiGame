package controller.players;

import view.DrawInterfaceMocker;
import view.IGameControlled;

/**
 * A Player interface for all text-based views, to be used in the Reversi game.
 */
public interface IPlayer {

  void makeMove();

  void setHasPassed();


  void addFeatures(DrawInterfaceMocker features);

  PlayerType getType();

  boolean getHasPassed();

  void resetHasPassed();

  String getName();

  void setMoveHandler(IGameControlled controller1);

  String getColor();
}
