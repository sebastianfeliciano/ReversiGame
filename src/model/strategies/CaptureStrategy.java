package model.strategies;

import controller.Player;
import model.Board;
import model.Move;
import model.ReadOnlyBoardModel;

import java.util.List;

public class CaptureStrategy implements IStrategy {
  @Override
  public Move selectMove(ReadOnlyBoardModel board, Player player) {
    List<Move> validMoves = board.getValidMovesWithCaptures(player);
    if (validMoves.isEmpty()) {
      player.setHasPassed();
      return null;
    }
    Move bestMove = validMoves.get(0);
    for (Move move : validMoves) {
      if (move.getPiecesCaught() > bestMove.getPiecesCaught()) {
        bestMove = move;
      }
      else if (move.getPiecesCaught() == bestMove.getPiecesCaught()) {
        if (move.getY() < bestMove.getY() || (move.getX() == bestMove.getX() && move.getY() < bestMove.getY())) {
          bestMove = move;
        }
      }
    }
    return bestMove;
  }
}

