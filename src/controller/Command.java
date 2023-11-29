package controller;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.ReadOnlyBoardModel;
import model.strategies.CaptureStrategy;
import model.strategies.GoForCornersStrategy;
import model.strategies.IStrategy;
import model.strategies.TryTwo;
/**
 * A command class for the command line to work.
 */

public class Command {
  private Player player1;
  private Player player2;
  private int boardSize;
  private ReadOnlyBoardModel board;

  /**
   * Initializes the command line arguments.
   */
  public Command(String[] args) {
    parseArguments(args);
  }

  private void parseArguments(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException
              ("Insufficient arguments. Expected at least" +
                      " board size and player 1 type or strategy.");
    }

    this.boardSize = Integer.parseInt(args[0]);
    this.board = new Board(boardSize);

    String player1Type = "human";
    String player1Strategy = null;
    String player2Type = "human";
    String player2Strategy = null;

    if (isStrategy(args[1])) {
      player1Type = "ai";
      player1Strategy = args[1];
    } else {
      player1Type = args[1];
    }

    if (args.length > 2) {
      if (!isStrategy(args[1])) {
        if (isStrategy(args[2])) {
          player2Type = "ai";
          player2Strategy = args[2];
        } else {
          player2Type = args[2];
        }
      } else {
        player2Strategy = args[2];
      }
    }

    if (player1Type.equalsIgnoreCase("ai") && args.length > 3 && !isStrategy(args[1])) {
      player1Strategy = args[3];
    }

    player1 = createPlayer(PlayerType.BLACK, player1Type, player1Strategy);
    player2 = createPlayer(PlayerType.WHITE, player2Type, player2Strategy);
  }

  private boolean isStrategy(String input) {
    switch (input.toLowerCase()) {
      case "capture":
      case "corner":
      case "capture corner":
      case "corner capture":
      case "capture capture":
      case "corner corner":
        return true;
      default:
        return false;
    }
  }

  private Player createPlayer(PlayerType playerType, String playerTypeStr, String strategyStr) {
    if (playerTypeStr.equalsIgnoreCase("ai")) {
      IStrategy strategy = getStrategy(strategyStr);
      return new AIPlayer("AI Player", playerType, board, strategy);
    } else {
      return new Player("Human Player", playerType, board);
    }
  }

  private IStrategy getStrategy(String strategyStr) {
    switch (strategyStr.toLowerCase()) {
      case "capture":
      case "capture capture":
        return new CaptureStrategy();
      case "corner":
      case "corner corner":
        return new GoForCornersStrategy();
      case "capture corner":
        return new TryTwo(new CaptureStrategy(), new GoForCornersStrategy());
      case "corner capture":
        return new TryTwo(new GoForCornersStrategy(), new CaptureStrategy());
      default:
        throw new IllegalArgumentException("Invalid strategy: " + strategyStr);
    }
  }

  /**
   * Returns the boardSize that is inputted.
   */
  public int getBoardSize() {
    return boardSize;
  }

  /**
   * Returns the first player.
   */
  public Player getPlayer1() {
    return player1;
  }

  /**
   * Returns the second player.
   */
  public Player getPlayer2() {
    return player2;
  }

  /**
   * Gets the regular board, from the readOnly Board that the players use.
   */
  public Board getBoard() {
    return board.getRegularBoard();
  }
}
