package controller;

import model.Board;

/**
 * Represents a single player in a reversi game.
 */
public class Player implements IPlayer {
    private final String name;
    private final PlayerType type;
    final Board board;
    public boolean hasPassed;

    /**
     * Constructor for player with a name, a player type,
     * and a board.
     */
    public Player(String name, PlayerType type, Board board) {
        this.name = name;
        this.type = type;
        this.board = board;
        this.hasPassed = false;
    }

    /**
     * Returns the name of a player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of a player.
     */
    public PlayerType getType() {
        return type;
    }

    /**
     * The player passes their turns.
     */
    public void setHasPassed() {
        this.hasPassed = !hasPassed;
    }

    /**
     * Places a valid move on the board.
     */
    @Override
    public void placeKey(int x, int y) {
        if (!board.isValidMove(x, y, this.getType())) {
            throw new IllegalArgumentException("Not a Valid Move!");
        }
        if (x > board.getBoardSize() / 2 || x < -board.getBoardSize() || y > board.getBoardSize() / 2 || y < -board.getBoardSize()) {
            throw new IllegalArgumentException("Not a Valid Move!");
        }
        int q = x + board.getBoardSize() / 2;
        int r = y + board.getBoardSize() / 2;
        board.placePiece(q, r, this.getType());
        board.flipPieces(q, r, this.getType());
    }
}