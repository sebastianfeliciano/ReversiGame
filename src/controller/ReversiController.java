package controller;

import model.Board;
import view.DrawUtils;

public class ReversiController implements PlayerActionListener, ModelStatusListener {
    private Player player;
    private AIPlayer ai;
    private DrawUtils view;

    private Board board;
    public ReversiController(Player player, Board board, DrawUtils view) {
        this.player = player;
        this.board = board;
        this.view = view;
    }

    public void registerListeners(DrawUtils view) {

    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGameEnd(PlayerType type) {

    }

    @Override
    public void onPlayerChanged(PlayerType current) {
        if (current == player.getType()) {
            //view.updateBoard(current.getModel().getBoard());
        }
    }

    @Override
    public void onInvalidMove() {

    }

    @Override
    public void onBoardUpdate() {

    }

    @Override
    public void onPlayerMove(int row, int column) {
//        try {
//            player.placeKey(row, column);
//        } catch (InvalidMoveException e) {
//            handleInvalidMove(e);
//        }
    }

    @Override
    public void onPass() {
//        try {
//            player.setHasPassed();
//        } catch (InvalidMoveException e) {
//            handleInvalidmove(e);
//        }
      }
}
