package controller;

import controller.players.IPlayer;
import controller.players.PlayerType;

import model.BoardModel;


import view.IGameControlled;
import view.ReversiView;

/**
 * The main controller for a player to interact with a board through the view.
 * Adhering to OOD principles.
 */
public class ReversiController implements IGameControlled {
  private final IPlayer player;
  private BoardModel board;
  private ReversiView view;
  private boolean turnMessageDisplayed = false;
  private boolean isUpdating = false;


  /**
   * The constructor, that sets up the observers and make sure the game isn't over when started.
   * A controller consists of a player, board, and view.
   */
  public ReversiController(IPlayer player, BoardModel board, ReversiView view) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    this.player = player;
    this.board = board;
    this.view = view;
    board.addObserver(this);
    view.resetGameOverHandled();
  }

  /**
   * Places the piece on the board.
   */
  private void placeKey(int x, int y) {
    board.placePiece(x, y, player.getType());
    board.flipPieces(x, y, player.getType());
  }

  /**
   * Controller, moves for the player onto the board.
   */

  public void onPlayerMove(int row, int column) {
    if (handleTurn() || board.isGameOver()) {
      return;
    }
    if (board.isValidMove(row, column, player.getType())) {
      this.placeKey(row, column);
      player.resetHasPassed();
      resetOpponentPassedState();
    } else {
      view.showInvalidMoveMessage();
      return;
    }
    checkAndUpdateGameState();
  }

  /**
   * Resets the opponent's passed state, so it doesn't keep if one have passed.
   */
  private void resetOpponentPassedState() {
    if (player.getType() == PlayerType.WHITE) {
      board.resetBlackPassed();
    } else {
      board.resetWhitePassed();
    }
  }

  /**
   * The Controller signals that the player has passed and sends messages.
   */

  public void onPass() {
    if (handleTurn()) {
      return;
    }
    board.playerPass(player.getType());
    if (!(player.getName().equals("Computer"))) {
      view.showThatIPassedTurnMessage();
    }
    player.setHasPassed();
    checkAndUpdateGameState();
  }

  /**
   * Updates the controller.
   */

  public void update() {
    if (isUpdating) {
      return;
    }
    try {
      isUpdating = true;
      if (board.isGameOver() && !view.getGameOverHandleState()) {
        view.handleGameOver();
        return;
      }
      updateScoreInView();
      if (board.isGameOver()) {
        view.handleGameOver();
      } else {
        if (board.isPlayerTurn(player)) {
          if (!turnMessageDisplayed) {
            if (player.getName().equals("Computer")) {
              player.makeMove();
              if (player.getHasPassed()) {
                onPass();
                board.switchTurns();
              }
              view.update();
              board.notifyObservers();
              checkAndUpdateGameState();
            } else {
              if (!player.getName().equals("Computer")) {
                view.itIsNowYourTurnMessage();
                turnMessageDisplayed = true;
              }

            }
          }
        } else {
          turnMessageDisplayed = false;
        }
        view.updateBoard(board);
        view.requestFocusInWindow();
      }
    } finally {
      isUpdating = false;
    }
  }

  /**
   * Updates the score in the view.
   */
  private void updateScoreInView() {
    int blackScore = board.getScoreBlack();
    int whiteScore = board.getScoreWhite();
    view.updateScore(blackScore, whiteScore);
  }

  /**
   * Tells the view to do something, when the game is over.
   */

  public void onGameOver() {
    view.handleGameOver();
    view.update();
  }


  private boolean handleTurn() {
    if (!board.isPlayerTurn(player)) {
      view.itIsNotYourTurnMessage();
      return true;
    }
    return false;
  }

  private void checkAndUpdateGameState() {
    view.updateBoard(board);

    if (board.isGameOver()) {
      view.handleGameOver();
      return;
    }

    // Reset opponent's 'passed' state and switch turns
    if (player.getType() == PlayerType.WHITE) {
      board.resetBlackPassed();
    } else {
      board.resetWhitePassed();
    }

    board.switchTurns();
    turnMessageDisplayed = false;
  }


  public void handleMove(IPlayer player, int row, int column) {
    System.out.println(player.getName() + " is Placing");
    this.placeKey(row, column);
  }

  public IPlayer getPlayer() {
    return this.player;
  }

}
