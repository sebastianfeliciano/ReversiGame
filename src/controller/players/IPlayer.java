package controller.players;

import controller.ReversiController;
import view.DrawInterfaceMocker;

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

  void setMoveHandler(ReversiController controller1);

  String getColor();
}
