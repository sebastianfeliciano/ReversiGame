package model;

import controller.DirectionsEnum;
import controller.PlayerType;

import java.util.List;

public interface ReadOnlyBoardModel {

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

    List<DirectionsEnum> getValidMoves(PlayerType player);
}
