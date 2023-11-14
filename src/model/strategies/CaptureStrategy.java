package model.strategies;

import controller.PlayerType;
import model.Board;
import model.Move;

import java.util.List;

public class CaptureStrategy implements IStratedgy {

    @Override
    public Move selectMove(Board board, PlayerType player) {
        List<Move> validMoves = board.getValidMovesWithCaptures(player);
        if (validMoves.isEmpty()) {
            return null;
        }

        Move bestMove = validMoves.get(0);
        for (Move move : validMoves) {
            if (move.getPiecesCaught() > bestMove.getPiecesCaught()) {
                bestMove = move;
            } else if (move.getPiecesCaught() == bestMove.getPiecesCaught()) {
                if (move.getX() < bestMove.getX() || (move.getX() == bestMove.getX() && move.getY() < bestMove.getY())) {
                    bestMove = move;
                }
            }
        }
        return bestMove;
    }
}
