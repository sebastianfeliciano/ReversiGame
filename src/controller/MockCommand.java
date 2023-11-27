package controller;

import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.BoardModel;
import model.Mock;

public class MockCommand extends Command {

    private int boardSize;
    private Player player1;
    private Player player2;
    private Board board;
    private StringBuilder log;

    public MockCommand(int boardSize, Player player1, Player player2, Board board) {
        this.boardSize = boardSize;
        this.player1 = player1;
        this.player2 = player2;
        this.board = board;
        this.log = new StringBuilder();
    }

    @Override
    public void prompt() {

        if (player1.getType() == PlayerType.WHITE || player2.getType() == PlayerType.WHITE) {
            log.append("Please choose a strategy for the AI: capture, corner, or two strategies");
        }

        log.append("Enter the board size:")
                .append("Choose Player 1 player type (human/ai)")
                .append("Choose Player 2 player type (human/ai)");
        super.prompt();
    }
}
