package model.strategies;

import controller.Player;
import model.Board;
import model.Move;
import model.ReadOnlyBoardModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AvoidCornersStrategy implements IStrategy {
    Board board = new Board();
    @Override
    public Move selectMove(ReadOnlyBoardModel board, Player player) {
        List<Move> validMoves = board.getValidMovesWithCaptures(player);
        if (validMoves.isEmpty()) {
            player.setHasPassed();
            return null;
        }

        List<Move> nonCorners = filterNonCornerMoves(validMoves, board.getBoardSize());

        if (!nonCorners.isEmpty()) {
            return nonCorners.get(new Random().nextInt(nonCorners.size()));
        }
        return validMoves.get(new Random().nextInt(validMoves.size()));
    }

    private List<Move> filterNonCornerMoves(List<Move> moves, int boardSize) {
        List<Move> nonCorners = new ArrayList<>();

        for (Move move : moves) {
            if (!board.isCornerMove(move, boardSize)) {
                nonCorners.add(move);
            }
        }
        return nonCorners;
    }
}
