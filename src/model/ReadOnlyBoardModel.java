package model;

import controller.PlayerType;

public interface ReadOnlyBoardModel {
    /**
     * what we need to add:
     * - a way to copy the board / score / currentHex
     * - visualizations
     */

    int getScoreWhite(Board board);

    int getScoreBlack(Board board);

    int getBoardSize();

    boolean isGameOver();

    boolean isBoardFull();

    int countPieces(PlayerType type);

    boolean hasPlayerPassed(PlayerType type);

    HexShape getCurrentHex(int row, int column);

    boolean isValidMove(int x, int y, PlayerType playerType);

    boolean isValidCoordinate(int q, int r);
}
