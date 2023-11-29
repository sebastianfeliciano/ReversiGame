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

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A command class for the command line to work.
 */
public class Command {
  private Player player1;
  private Player player2;
  private int boardSize;
  private ReadOnlyBoardModel board;
  private Scanner scanner;


  /**
   * Initializes the scanner for the command line.
   */
  public Command() {
    scanner = new Scanner(System.in);
  }

  /**
   * Created the prompt and makes the game.
   */
  public void prompt() {
    setupBoard();
    player1 = createPlayer(PlayerType.BLACK);
    player2 = createPlayer(PlayerType.WHITE);
  }

  /**
   * Sets up the board, to be played upon.
   */
  private void setupBoard() {
    System.out.println("Enter the board size: ");
    try {
      boardSize = scanner.nextInt();
      scanner.nextLine();
      if ((boardSize >= 5) && (boardSize % 2 == 0)) {
        System.out.println("Invalid input. The Board size must be greater than or equal to " +
                "5 and not even!");
        scanner.nextLine();
        setupBoard();
      }
      this.board = new Board(boardSize);
    } catch (InputMismatchException e) {
      System.out.println("Invalid input. The Board size must be greater than or equal to " +
              "5 and not even!");
      scanner.nextLine();
      setupBoard();
    }
  }

  /**
   * Creates a single player for the game, depending on human or ai.
   */
  private Player createPlayer(PlayerType playerType) {
    System.out.println("Choose "
            + PlayerType.playerGameString(playerType) + " player type (human/ai)");
    String playerTypeInput = scanner.nextLine();
    if (playerTypeInput.equalsIgnoreCase("ai")) {
      IStrategy strategy = selectStrategy();
      return new AIPlayer("AI Player", playerType, board, strategy);
    } else {
      return new Player("Human Player", playerType, board);
    }
  }

  /**
   * Selected a strategy for the ai.
   */
  private IStrategy selectStrategy() {
    System.out.println("Please choose a strategy for the AI: capture, corner, or two strategies");
    String strategyInput = scanner.nextLine();
    return getStrategy(strategyInput, (Board) board, scanner);
  }

  /**
   * Gets the strategy inputted and matches to the ai.
   */
  private IStrategy getStrategy(String strategy, Board board, Scanner scanner) {
    while (true) {
      switch (strategy.toLowerCase()) {
        case "capture":
          return new CaptureStrategy();
        case "corner":
          return new GoForCornersStrategy();
        case "two strategies":
          return getCompositeStrategy(scanner);
        default:
          System.out.println("Invalid strategy: "
                  + strategy + ". Please choose capture, corner, or two strategies.");
          strategy = scanner.nextLine();
      }
    }
  }

  /**
   * Gets the CompositeStrategy that is chosen by Two Strategies and combines two strategies.
   */
  private IStrategy getCompositeStrategy(Scanner scanner) {
    System.out.println("Choose the primary strategy (capture/corner):");
    String primaryStrategyInput = scanner.nextLine();
    IStrategy primaryStrategy = getSingleStrategy(primaryStrategyInput);

    System.out.println("Choose the secondary strategy (capture/corner):");
    String secondaryStrategyInput = scanner.nextLine();
    IStrategy secondaryStrategy = getSingleStrategy(secondaryStrategyInput);

    return new TryTwo(primaryStrategy, secondaryStrategy);
  }

  /**
   * Gets all the possibly singular strategies.
   */
  private IStrategy getSingleStrategy(String strategy) {
    while (true) {
      switch (strategy.toLowerCase()) {
        case "capture":
          return new CaptureStrategy();
        case "corner":
          return new GoForCornersStrategy();
        default:
          System.out.println("Invalid strategy: "
                  + strategy + ". Please choose capture or corner.");
          strategy = scanner.nextLine();
      }
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

  /**
   * Closes further input for the scanner.
   */
  public void close() {
    if (scanner != null) {
      scanner.close();
    }
  }

}
