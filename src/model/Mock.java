package model;

import controller.Player;
import controller.PlayerType;

import java.util.List;

public class Mock extends Board
        implements ReadOnlyBoardModel, BoardModel {
    List<Move> validMoves;

    public Mock(List<Move> validMoves) {
        this.validMoves = validMoves;
    }

    @Override
    public void flipPieces(int x, int y, PlayerType currentPlayer) {

    }

    @Override
    public void playerPass(PlayerType playerType) {

    }

    @Override
    public int getScoreWhite() {
        return 0;
    }

    @Override
    public int getScoreBlack() {
        return 0;
    }

    @Override
    public int getBoardSize() {
        return 0;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public boolean isBoardFull() {
        return false;
    }

    @Override
    public int countPieces(PlayerType type) {
        return 0;
    }

    @Override
    public boolean hasPlayerPassed(PlayerType type) {
        return false;
    }

    @Override
    public HexShape getCurrentHex(int row, int column) {
        return null;
    }

    @Override
    public boolean isValidMove(int x, int y, PlayerType playerType) {
        return false;
    }

    @Override
    public boolean isValidCoordinate(int q, int r) {
        return false;
    }

    @Override
    public List<Move> getValidMovesWithCaptures(Player player) {
        return validMoves;
    }

    @Override
    public boolean isCornerMove(Move move, int boardSize) {
        return true;
    }
}
