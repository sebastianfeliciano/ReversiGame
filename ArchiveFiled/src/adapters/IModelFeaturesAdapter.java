package adapters;

import controller.players.IPlayer;
import model.BoardModel;
import provider.controller.IModelFeatures;
import provider.model.IMutableModel;
import provider.model.PlayerDisc;
import model.Board;
import view.IGameControlled;
import view.ReversiView;

/**
 * Adapter class that implements the IModelFeatures interface to bridge between
 * the model and the view in a Reversi game.
 */
public class IModelFeaturesAdapter implements IModelFeatures, IGameControlled {
  private BoardModel board;
  private final controller.players.IPlayer player;

  private final ReversiView view;

  /**
   * Constructs an IModelFeaturesAdapter.
   *
   * @param player The player controller.
   * @param board  The model representing the game board.
   * @param view   The view of the Reversi game.
   */
  public IModelFeaturesAdapter(controller.players.IPlayer player,
                               BoardModel board, ReversiView view) {
    this.board = board;
    this.view = view;
    this.player = player;

  }

  /**
   * Handles the player's turn.
   *
   * @param player The player disc representing the current player.
   */
  @Override
  public void handlePlayerTurn(PlayerDisc player) {
    if (board.getCurrentTurn() == PlayerDiscAdapter.convertToPlayerType(player)) {
      view.itIsNowYourTurnMessage();
      if (this.player.getHasPassed()) {
        board.playerPass(PlayerDiscAdapter.convertToPlayerType(player));
        board.switchTurns();
        view.showThatIPassedTurnMessage();
      } else {
        if (!this.player.getName().equals("Computer")) {
          view.requestFocusInWindow();
        } else {
          this.player.makeMove();
          board.switchTurns();
        }
      }
    } else {
      view.itIsNotYourTurnMessage();
    }
  }

  /**
   * Sets the game model.
   *
   * @param m The mutable model to be set.
   * @throws IllegalArgumentException If the model is not compatible.
   */
  @Override
  public void setModel(IMutableModel m) {
    if (m instanceof Board) {
      this.board = (Board) m;
    } else {
      throw new IllegalArgumentException("Model not compatible");
    }
  }

  /**
   * Handles the end of the game and displays the winner.
   *
   * @param winner The name of the winning player.
   */
  @Override
  public void handleGameOver(String winner) {
    view.update();
    int black = board.getScoreBlack();
    int white = board.getScoreWhite();

    view.updateScore(black, white);
    board.checkGameOver();

    if (board.isGameOver()) {
      view.handleGameOver();
    }
  }


  @Override
  public void handleMove(IPlayer player, int row, int column) {
    // Empty implementation because the method is handled elsewhere.
  }

  @Override
  public void update() {
    // No content is added here as the update logic is managed in another
    // part of the applicatione.
  }

  @Override
  public void onGameOver() {
    // Left empty as the game over logic is implemented elsewhere.
  }

  @Override
  public IPlayer getPlayer() {
    return this.player;
  }

  @Override
  public void onPlayerMove(int row, int column) {
    // Intentionally empty; the logic for player movement
    // is handled in a different part of the system.
  }

  @Override
  public void onPass() {
    // Not implemented here as the pass action is controlled
    // elsewhere.
  }

}
