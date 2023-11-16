package controller;

import java.util.Optional;

import model.Board;
import model.strategies.FallibleHexGameStrategy;
import model.strategies.IStrategy;
import model.Move;

/**
 * Represents a computer player in the reversi game.
 */
public class AIPlayer extends Player {
    private final IStrategy strategy;

    /**
     * Constructor for an AIPlayer.
     */
    public AIPlayer(String name, PlayerType type, Board board, IStrategy strategy) {
        super("Computer", type, board);
        this.strategy = strategy;
        this.hasPassed = false;
    }

    /**
     * Makes a move for the AIPlayer.
     */
    public void makeMove() {
        Optional<Move> selectedMove = strategy.selectMove(this.board, this);
        if (selectedMove.isPresent()) {
            super.placeKey(selectedMove.get().getX(), selectedMove.get().getY());
        } else {
            this.setHasPassed();
        }
    }
}
