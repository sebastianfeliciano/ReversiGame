package controller.players;

import java.util.Optional;

import controller.ReversiController;
import model.ReadOnlyBoardModel;
import model.strategies.IStrategy;
import model.Move;
import view.IGameControlled;

/**
 * Represents a computer player in the reversi game.
 */
public class AIPlayer extends Player implements IPlayer, TurnAIPopUp {
  private final IStrategy strategy;
  protected IGameControlled controller1;

  /**
   * Constructor for an AIPlayer.
   */
  public AIPlayer(String name, PlayerType type, ReadOnlyBoardModel board, IStrategy strategy) {
    super("Computer", type, board);
    this.strategy = strategy;
    this.hasPassed = false;
  }

  public void setMoveHandler(IGameControlled controller1) {
    this.controller1 = controller1;
  }

  /**
   * Makes a move for the Player.
   */
  public void makeMove() {
    Optional<Move> selectedMove = strategy.selectMove(this.board, this);
    if (selectedMove.isPresent()) {
      this.makeMove(selectedMove.get().getX(), selectedMove.get().getY());
    } else {
      this.setHasPassed();
    }
  }

  /**
   * Make move helper, that tells the controller to handle the move since,
   * the controller does the move, not the player.
   */
  public void makeMove(int row, int column) {
    if (controller1 != null) {
      controller1.handleMove(this, row, column);
    }
  }


  /**
   * Tells the AI, to make the move.
   */
  @Override
  public void itIsNowYourTurnMessage() {
    this.makeMove();
  }
}
