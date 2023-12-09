package controller;

import controller.players.AIPlayer;
import controller.players.IPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.BoardModel;
import model.ReadOnlyBoardModel;
import model.strategies.CaptureStrategy;
import model.strategies.GoForCornersStrategy;
import model.strategies.IStrategy;
import model.strategies.TryTwo;
import provider.player.CaptureMaxStrat;
import adapters.FallibleReversiStratAdapters;

/**
 * A command class for the command line to work.
 */

public class Command {
  private IPlayer player1;
  private IPlayer player2;
  private int boardSize;
  private ReadOnlyBoardModel board;

  /**
   * Initializes the command line arguments.
   */
  public Command(String[] args) {
    parseArguments(args);
  }

  /**
   * Parses the arguments for the game to start.
   */
  private void parseArguments(String[] args) {
    if (args.length < 2) {
      throw new IllegalArgumentException(
              "Insufficient arguments. Expected at least" +
                      " board size and player 1 type or strategy.");
    }

    this.boardSize = Integer.parseInt(args[0]);
    this.board = new Board(boardSize);

    player1 = findThePlayer(args, 1);
    player2 = findThePlayer(args, 2);
  }

  /**
   * Determines, the player made.
   */
  private IPlayer findThePlayer(String[] args, int playerNumber) {
    String playerType = "human";
    String strategy = null;
    int index = -1;

    if (playerNumber == 1) {
      index = 1;
    } else if (playerNumber == 2 && args.length > 2) {
      index = 2;
    }

    if (index != -1 && args.length > index) {
      if (isStrategy(args[index])) {
        playerType = "ai";
        strategy = args[index];
      } else {
        playerType = args[index];
      }
    }

    PlayerType pType = (playerNumber == 1) ? PlayerType.BLACK : PlayerType.WHITE;
    return createPlayer(pType, playerType, strategy);
  }


  /**
   * Checks to see if a strategy was correctly, made.
   */
  private boolean isStrategy(String input) {
    switch (input.toLowerCase()) {
      case "capture":
      case "corner":
      case "capture corner":
      case "corner capture":
      case "capture capture":
      case "corner corner":
      case "providerstrategy1":
      case "providerstrategy2":
        return true;
      default:
        return false;
    }
  }

  /**
   * Creates a player.
   */
  private IPlayer createPlayer(PlayerType playerType, String playerTypeStr, String strategyStr) {
    if (playerTypeStr.equalsIgnoreCase("ai")) {
      if ((strategyStr.equalsIgnoreCase("providerstrategy1")
              || strategyStr.equalsIgnoreCase("providerstrategy2"))
              && playerType == PlayerType.BLACK) {
        throw new IllegalArgumentException("Player 1 cannot use providerstrategy1");
      }
      IStrategy strategy = getStrategy(strategyStr);
      return new AIPlayer("AI Player", playerType, board, strategy);
    } else {
      return new Player("Human Player", playerType, board);
    }
  }


  /**
   * Makes the Strategy based on the string passed.
   */
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
      case "providerstrategy1":
        return new FallibleReversiStratAdapters(new CaptureMaxStrat()) {
        };
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
  public IPlayer getPlayer1() {
    return player1;
  }

  /**
   * Returns the second player.
   */
  public IPlayer getPlayer2() {
    return player2;
  }

  /**
   * Gets the regular board, from the readOnly Board that the players use.
   */
  public BoardModel getBoard() {
    return board.getRegularBoard();
  }
}
