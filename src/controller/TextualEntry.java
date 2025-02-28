package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.List;

import controller.players.Player;
import controller.players.PlayerType;
import model.Board;

/**
 * Main entry point for players to play Reversi.
 */
public final class TextualEntry {
  /**
   * Main userInputs points for players to play Reversi.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to REVERSI!");
    System.out.println("What size board would you like (0 for default)?");

    Board currentBoard;
    int chosenSizeOfBoard = scanner.nextInt();
    scanner.nextLine();

    try {
      if (chosenSizeOfBoard == 0) {
        currentBoard = new Board();
      } else {
        currentBoard = new Board(chosenSizeOfBoard);
      }
      TextualController boardGenerator = new TextualController(currentBoard);
      System.out.println(boardGenerator);
    } catch (InputMismatchException e) {
      throw new InputMismatchException("Put a valid number!");
    }

    System.out.println("How many People are Playing?");
    int numberOfPlayers = scanner.nextInt();
    scanner.nextLine();

    if (numberOfPlayers <= 0 || numberOfPlayers > 2) {
      throw new IllegalArgumentException("The Number of " +
              "Players Must be either 1 or 2 to play this game");
    }

    List<Player> players = new ArrayList<>();

    for (int i = 0; i < numberOfPlayers; i++) {
      System.out.println("Enter Player " + (i + 1) + " name:");
      String playerName = scanner.next();
      scanner.nextLine();
      PlayerType type = (i == 0) ? PlayerType.WHITE : PlayerType.BLACK;
      players.add(new Player(playerName, type, currentBoard));
    }

    if (numberOfPlayers == 1) {
      players.add(new Player("Computer", PlayerType.BLACK, currentBoard));
    }

    for (Player player : players) {
      //player.setHasPassed();
    }

    TextualController boardGenerator0 = new TextualController(currentBoard);
    System.out.println(boardGenerator0);

    boolean isGameOver = false;

    while (!isGameOver) {
      for (Player player : players) {
        boolean validMoveMade = false;

        if (player.getName().equals("Computer")) {
          System.out.println("Computer's turn!");

          while (!validMoveMade) {
            int x = new Random().nextInt(currentBoard.getBoardSize());
            int y = new Random().nextInt(currentBoard.getBoardSize());
            try {
              //player.placeKey(x, y);
              validMoveMade = true;
            } catch (IllegalArgumentException e) {
              // Silently catch for the computer, it will keep trying random positions
            }
          }
        } else {
          if (currentBoard.hasPlayerPassed(player.getType())) {
            System.out.println(player.getName() + " has passed their turn.");
            continue;
          }

          System.out.println(player.getName() + "'s Turn \uD83D\uDE08 ("
                  + player.getType().toString() + ")");

          while (!validMoveMade) {
            System.out.println("Enter your move (e.g., x,y coordinates), or " +
                    "'pass' to skip your turn:");

            try {
              String playerInput = scanner.nextLine().trim();

              if (playerInput.equalsIgnoreCase("pass")) {
                currentBoard.playerPass(player.getType());
                validMoveMade = true;
                break;
              } else {
                String[] split = playerInput.split(",");
                int firstCoordinate = Integer.parseInt(split[0]);
                int secondCoordinate = Integer.parseInt(split[1]);

                //player.placeKey(firstCoordinate, secondCoordinate);
                validMoveMade = true;

                TextualController boardGenerator = new TextualController(currentBoard);
                boardGenerator.render();
              }
            } catch (NumberFormatException e) {
              System.out.println("Invalid input format. Please enter coordinates in the form x,y.");
            } catch (IllegalArgumentException e) {
              System.out.println(e.getMessage());
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          }
        }

        if (currentBoard.hasPlayerPassed(PlayerType.WHITE)
                && currentBoard.hasPlayerPassed(PlayerType.BLACK)) {
          System.out.println("Both players have passed consecutively. Ending game.");
          isGameOver = true;
          break;
        }
      }

      isGameOver = isGameOver || currentBoard.isGameOver();
    }

    System.out.println("The game is over!");
    int whiteCount = currentBoard.countPieces(PlayerType.WHITE);
    int blackCount = currentBoard.countPieces(PlayerType.BLACK);

    System.out.println("White pieces: " + whiteCount);
    System.out.println("Black pieces: " + blackCount);

    if (whiteCount > blackCount) {
      System.out.println("White wins!");
    } else if (blackCount > whiteCount) {
      System.out.println("Black wins!");
    } else {
      System.out.println("It's a tie!");
    }
  }
}