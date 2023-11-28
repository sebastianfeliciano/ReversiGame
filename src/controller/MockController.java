package controller;

import controller.players.PlayerType;
import model.Board;

public class MockController implements PlayerActionListener, ModelStatusListener {
    ReversiController controller;
    Board board;
    StringBuilder log = new StringBuilder();

    @Override
    public void onPlayerMove(int row, int column) {
        log.append("Player moved to row: ").append(row).append(", column: ").append(column).append("\n");
    }

    @Override
    public void onPass() {
        if (board.hasPlayerPassed(PlayerType.WHITE)) {
            log.append("Player passed. \n");
        }
    }

//    @Override
//    public void updateScore() {
//        log.append("Score White").append(board.getScoreWhite());
//    }

    public String getLog() {
        return log.toString();
    }

    @Override
    public void onGameStart() {
        if (!board.isGameOver()) {
            log.append("Game started.");
        }
    }

    @Override
    public void onGameEnd(PlayerType type) {
        if (board.isGameOver()) {
            log.append("Game over!");
        }
    }

    @Override
    public void onPlayerChanged(PlayerType player) {
        log.append("Player ").append(player).append("changed.");
    }

    @Override
    public void onInvalidMove() {
        if (board.isValidMove(11, 11, PlayerType.WHITE)) {
            log.append("Invalid move");
        }
    }

    @Override
    public void onBoardUpdate() {
        log.append("Board updated");
    }
}
