package model;

import controller.PlayerType;

public interface BoardModel {
    void flipPieces(int x, int y, PlayerType currentPlayer);

    void playerPass(PlayerType playerType);

    void placePiece(int q, int r, PlayerType type);
}
