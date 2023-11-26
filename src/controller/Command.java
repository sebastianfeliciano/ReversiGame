package controller;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.ReadOnlyBoardModel;
import model.strategies.*;
import view.DrawUtils;

import java.util.Scanner;
public class Command {
    Board board;
    private int boardSize;
    private PlayerType player1;
    private PlayerType player2;
    private IStrategy player1Strategy;
    private IStrategy player2Strategy;


    public void prompt() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the board size: ");
        boardSize = scanner.nextInt();
        System.out.println("Choose player 1 type (human/ai) " +
                        "Type h for human " +
                        "and ai for ai");

        player1 = getPlayerType(scanner.next());


        if (player1 == PlayerType.WHITE || player2 == PlayerType.WHITE) {
            System.out.println("Choose a strategy (capture, corner, complete, try-two) ");
            player1Strategy = (getStrategy(scanner.next()));
            player2Strategy = (getStrategy(scanner.next()));
        }

        System.out.println("Choose player 2 type (human/ai) " +
                "Type h for human " +
                "and ai for ai");
        player2 = getPlayerType(scanner.next());


        if (player1 == PlayerType.WHITE && player2 == PlayerType.WHITE) {
            throw new IllegalArgumentException("Cannot have two ai players");
        }
    }

    private IStrategy getStrategy(String strategy) {
        switch (strategy.toLowerCase()) {
            case "capture":
                new AIPlayer("ai", PlayerType.WHITE, board, new CaptureStrategy());
            case "corner":
                new AIPlayer("ai", PlayerType.WHITE, board, new GoForCornersStrategy());
            case "complete":
                new AIPlayer("ai", PlayerType.WHITE, board, new CompleteStrategyFromFallible());
                //add one for try-two
            default:
                throw new IllegalArgumentException("Invalid strategy.");
        }
    }

    public DrawUtils createGame() {
        AIPlayer player1 = new AIPlayer("Player 1", this.player1, board, player1Strategy);
        AIPlayer player2 = new AIPlayer("Player 2", this.player1, board, player2Strategy);
        ReadOnlyBoardModel board = new Board(boardSize);
        return new DrawUtils(board);
    }

    private PlayerType getPlayerType(String type) {
        if (type.equalsIgnoreCase("h")) {
            return PlayerType.BLACK;
        } else if (type.equalsIgnoreCase("ai")) {
            return PlayerType.WHITE;
        } else {
            throw new IllegalArgumentException("Invalid player type" + type);
        }
    }

    public int getBoardSize() {
        return boardSize;
    }

    public PlayerType getPlayer1Type() {
        return player1;
    }

    public PlayerType getPlayer2Type() {
        return player2;
    }
}
