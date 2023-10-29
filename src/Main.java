import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

import controller.Player;
import controller.PlayerType;
import model.Board;
import view.TextualController;


public final class Main {
  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome to REVERSI!");
    System.out.println("How many People are Playing?");

    //Number of Players wanted in the game
    int numberOfPlayers;
    numberOfPlayers = scanner.nextInt();
    scanner.nextLine();

    try {
      if (numberOfPlayers <= 0 || numberOfPlayers > 2) {
        throw new IllegalArgumentException("The Number of Players Must be either" +
                " 1 or 2 to play this game");
      }
    } catch (InputMismatchException e) {
      throw new InputMismatchException("Put a valid number!");
    }

    List<Player> players = new ArrayList<>();

    for (int i = 0; i < numberOfPlayers; i++) {
      System.out.println("Enter Player " + (i + 1) + " name:");
      String playerName = scanner.next();
      PlayerType type;
      if (i == 0) {
        type = PlayerType.WHITE;
      } else {
        type = PlayerType.BLACK;
      }
      players.add(new Player(playerName, type));
      //System.out.println("controller.Player " + (i + 1) + ": " + players.get(i).getName());
    }


    System.out.println("What size board would you like (0 for default)?");
    try {
      int chosenSizeOfBoard = scanner.nextInt();
      scanner.nextLine();
      if (chosenSizeOfBoard == 0) {
        Board empty = new Board();
        TextualController boardGenerator = new TextualController(empty);
        System.out.println(boardGenerator);
      } else {
        Board empty = new Board(chosenSizeOfBoard);
        TextualController boardGenerator = new TextualController(empty);
        System.out.println(boardGenerator);
      }
    } catch (InputMismatchException e) {
      throw new InputMismatchException("Put a valid number!");
    }

    //While Game isnt over
    for (Player player : players) {
      System.out.println(player.getName() + "'s Turn \uD83D\uDE08");
      System.out.println("Enter your move in the form of x,y coordinates:");
      try {
        String playerInput = scanner.nextLine().trim();
        String[] split = playerInput.split(",");
        int firstCoordinate = Integer.parseInt(split[0]);
        int secondCoordinate = Integer.parseInt(split[1]);
        //Check if there is an empty move or a valid move

        //model.Board.setCell(firstCoordinate, secondCoordinate);


      } catch (NumberFormatException e) {
        System.out.println(e);
      }
    }

    }
  }
