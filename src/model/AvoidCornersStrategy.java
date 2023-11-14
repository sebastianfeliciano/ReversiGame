package model;

import controller.DirectionsEnum;
import controller.PlayerType;

import java.awt.*;
import java.util.List;

public class AvoidCornersStrategy implements Strategy {

    Board board = new Board(11);

    @Override
    public DirectionsEnum chooseMove(PlayerType playerType) {
        List<DirectionsEnum> validMoves = board.getValidMoves(playerType);
        if (!validMoves.isEmpty()) {
            int randomIndex = (int) (Math.random() * validMoves.size());
            return validMoves.get(randomIndex);
        }

        return null;
    }

    private boolean isNextToCorner(Point move) {
        int x = move.x;
        int y = move.y;

        return (x == 0 && y == 1) || (x == 1 && y == 0) ||
                (x == 0 && y == board.getBoardSize() - 2) || (x == 1 && y == board.getBoardSize() - 1) ||
                (x == board.getBoardSize() - 2 && y == 0) || (x == board.getBoardSize() - 1 && y == 1) ||
                (x == board.getBoardSize() - 2 && y == board.getBoardSize() - 1) || (x == board.getBoardSize() - 1 && y == board.getBoardSize() - 2);
    }
}
