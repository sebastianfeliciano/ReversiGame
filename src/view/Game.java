package view;

import javax.swing.Timer;

import model.Board;
import model.BoardModel;

/**
 * Represents the game logic for a Reversi game.
 * This class is responsible for managing the game flow,
 * particularly when two AI players are involved.
 * It controls the sequence of moves and updates the game state.
 */
public class Game implements GameMocked {
  private Timer timer;
  private final IGameControlled controller1;
  private final IGameControlled controller2;
  private final Board board;

  /**
   * Constructs a new Game instance with the specified controllers and board model.
   *
   * @param controller1 The first game controller, responsible for one side of the game.
   * @param controller2 The second game controller, responsible for the other side of the game.
   * @param board       The model of the board, containing the current game state.
   */
  public Game(IGameControlled controller1, IGameControlled controller2, BoardModel board) {
    this.controller1 = controller1;
    this.controller2 = controller2;
    this.board = board.getRegularBoard();
  }

  /**
   * Starts the game loop, alternating updates between the two controllers until the game is over.
   */
  public void start() {
    while (!board.isGameOver()) {
      controller1.update();
      controller2.update();
    }
    handleGameOver();
  }

  /**
   * Handles the end of the game, notifying both controllers that the game is over.
   */
  private void handleGameOver() {
    controller1.onGameOver();
    controller2.onGameOver();
  }

  /**
   * Determines the winner of the game based on the scores of white and black players.
   *
   * @return A string indicating the winner ("WHITE", "BLACK", or "Tie").
   */
  private String determineWinner() {
    int whiteScore = board.getScoreWhite();
    int blackScore = board.getScoreBlack();
    if (whiteScore > blackScore) {
      return "WHITE";
    } else if (blackScore > whiteScore) {
      return "BLACK";
    } else {
      return "Tie";
    }
  }
}
