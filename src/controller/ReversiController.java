package controller;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import view.DrawUtils;
import view.Observer;
import view.PlayerActionListener;

public class ReversiController implements PlayerActionListener, Observer, MoveHandler {
  private final Player player;
  private Board board;
  private DrawUtils view;
  private boolean turnMessageDisplayed = false;
  private boolean isUpdating = false;


  public ReversiController(Player player, Board board, DrawUtils view) {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }
    this.player = player;
    this.board = board;
    this.view = view;
    board.addObserver(this);
    view.resetGameOverHandled();
  }

  public void placeKey(int x, int y) {
    if (!board.isValidMove(x, y, player.getType())) {
      view.showInvalidMoveMessage();
      return;
    }
    if (x > board.getBoardSize() / 2 || x < -board.getBoardSize()
            || y > board.getBoardSize() / 2 || y < -board.getBoardSize()) {
      view.showInvalidMoveMessage();
      return;
    }
    int q = x + board.getBoardSize() / 2;
    int r = y + board.getBoardSize() / 2;
    board.placePiece(q, r, player.getType());
    board.flipPieces(q, r, player.getType());
  }


//  @Override
//  public void onPlayerMove(int row, int column) {
//
//    if (handleTurn() && !board.isGameOver()) {
//      return;
//    }
//
//    // Player makes a move
//    if (player instanceof AIPlayer) {
//      ((AIPlayer) player).makeMove();
//      this.placeKey(row, column);
//    } else if (board.isValidMove(row, column, player.getType())) {
//      this.placeKey(row, column);
//      player.resetHasPassed();
//      resetOpponentPassedState();
//      checkAndUpdateGameState();
//    } else {
//      view.showInvalidMoveMessage();
//    }
//  }

  @Override
  public void onPlayerMove(int row, int column) {
    if (handleTurn() || board.isGameOver()) {
      return;
    }

    // If AI Player, execute its move
//    if (player instanceof AIPlayer) {
//      ((AIPlayer) player).makeMove();
//    } else {
    // For human players, validate and make the move
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

  private void resetOpponentPassedState() {
    if (player.getType() == PlayerType.WHITE) {
      board.resetBlackPassed();
    } else {
      board.resetWhitePassed();
    }
  }

  @Override
  public void onPass() {
    if (handleTurn()) {
      return;
    }
    board.playerPass(player.getType());
    if (!(player instanceof AIPlayer)) {
      view.showThatIPassedTurnMessage();
    }
    player.setHasPassed();
    checkAndUpdateGameState();
  }

  @Override
  public void update() {
    System.out.println("Update called for player: " + player.getColor());
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
            if (player instanceof AIPlayer) {
              ((AIPlayer) player).makeMove();
              if (player.getHasPassed()) {
                onPass();
              }
              board.notifyObservers();
              checkAndUpdateGameState();
            } else {
              view.ItIsNowYourTurnMessage();
              turnMessageDisplayed = true;
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

  private void updateScoreInView() {
    int blackScore = board.getScoreBlack();
    int whiteScore = board.getScoreWhite();
    view.updateScore(blackScore, whiteScore);
  }

  @Override
  public void onGameOver() {
    view.handleGameOver();
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
    turnMessageDisplayed = false; // Reset turn message display
  }

  @Override
  public void handleMove(Player player, int row, int column) {
    this.placeKey(row, column);
  }

}
