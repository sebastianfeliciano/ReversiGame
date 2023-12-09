package view;

import controller.MoveHandler;
import controller.players.IPlayer;

/**
 * Represents an interface for a game-controlled view in a game application. This interface
 * combines the functionality of handling player actions, observing game state changes, and
 * managing moves. It is designed to provide a contract for implementing the necessary control
 * mechanisms in a game view.
 */
public interface IGameControlled extends PlayerActionListener, Observer, MoveHandler {

  /**
   * Updates the view based on the current state of the game. This method should be called
   * whenever there is a change in the game state that needs to be reflected in the view.
   */
  void update();

  /**
   * Retrieves the current player in the game.
   *
   * @return The current player as an IPlayer instance.
   */
  IPlayer getPlayer();
}

