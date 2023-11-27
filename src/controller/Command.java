package controller;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.ReadOnlyBoardModel;
import model.strategies.*;
import view.DrawUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Command {
  private Player player1;
  private Player player2;
  private int boardSize;
  private ReadOnlyBoardModel board;
  private Scanner scanner;



  public Command() {
     scanner = new Scanner(System.in);
  }

  public void prompt() {
    setupBoard();
    player1 = createPlayer(PlayerType.BLACK);
    player2 = createPlayer(PlayerType.WHITE);
  }

  private void setupBoard() {
    System.out.println("Enter the board size: ");
    try {
      boardSize = scanner.nextInt();
      scanner.nextLine();
      this.board = new Board(boardSize);
    } catch (InputMismatchException e) {
      System.out.println("Invalid input. Please enter a number.");
      scanner.nextLine();
      setupBoard();
    }
  }

  private Player createPlayer(PlayerType playerType) {
    System.out.println("Choose " + PlayerType.playerGameString(playerType) + " player type (human/ai)");
    String playerTypeInput = scanner.nextLine();

    if (playerTypeInput.equalsIgnoreCase("ai")) {
      IStrategy strategy = selectStrategy();
      return new AIPlayer("AI Player", playerType, (Board) board, strategy);
    } else {
      return new Player("Human Player", playerType, (Board) board);
    }
  }

  private IStrategy selectStrategy() {
    System.out.println("Please choose a strategy for the AI: capture, corner, or two strategies");
    String strategyInput = scanner.nextLine();
    return getStrategy(strategyInput, (Board) board, scanner);
  }

  private IStrategy getStrategy(String strategy, Board board, Scanner scanner) {
    switch (strategy.toLowerCase()) {
      case "capture":
        return new CaptureStrategy();
      case "corner":
        return new GoForCornersStrategy();
      case "two strategies":
        return getCompositeStrategy(scanner);
      default:
        throw new IllegalArgumentException("Invalid strategy: " + strategy);
    }
  }

  private IStrategy getCompositeStrategy(Scanner scanner) {
    System.out.println("Choose the primary strategy (capture/corner):");
    String primaryStrategyInput = scanner.nextLine();
    IStrategy primaryStrategy = getSingleStrategy(primaryStrategyInput);

    System.out.println("Choose the secondary strategy (capture/corner):");
    String secondaryStrategyInput = scanner.nextLine();
    IStrategy secondaryStrategy = getSingleStrategy(secondaryStrategyInput);

    return new TryTwo(primaryStrategy, secondaryStrategy);
  }

  private IStrategy getSingleStrategy(String strategy) {
    switch (strategy.toLowerCase()) {
      case "capture":
        return new CaptureStrategy();
      case "corner":
        return new GoForCornersStrategy();
      default:
        throw new IllegalArgumentException("Invalid strategy: " + strategy);
    }
  }


  public int getBoardSize() {
    return boardSize;
  }

  public Player getPlayer1() {
    return player1;
  }

  public Player getPlayer2() {
    return player2;
  }

  public ReadOnlyBoardModel getBoard() {
    return board;
  }
  public void close() {
    if (scanner != null) {
      scanner.close();
    }
  }

}
