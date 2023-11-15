package model.strategies;

import controller.Player;
import model.Board;
import model.Move;
import model.ReadOnlyBoardModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GoForCornersStrategy implements IStrategy {
  @Override
  public Move selectMove(ReadOnlyBoardModel board, Player player) {
    List<Move> cornerMoves = new ArrayList<>();
    List<Move> validMoves = board.getValidMovesWithCaptures(player);
    if (validMoves.isEmpty()) {
      player.setHasPassed();
      return null;
    }

    for (Move move : validMoves) {
      if (isCornerMove(move, board.getBoardSize())) {
        cornerMoves.add(move);
      }
    }

    if (cornerMoves.isEmpty()) {
      Random rand = new Random();
      return validMoves.get(rand.nextInt(validMoves.size()));
    } else {
      Move bestMove = cornerMoves.get(0);
      for (Move cornerMove : cornerMoves) {
        if (cornerMove.getPiecesCaught() > bestMove.getPiecesCaught()) {
          bestMove = cornerMove;
        }
      }
      return bestMove;
    }
  }


    private boolean isCornerMove (Move move,int boardSize){
      int x = move.getX();
      int y = move.getY();
      return (x == 0 && y == (boardSize / 2) || (x == 0 && y == (-(boardSize / 2))) ||
              (x == (boardSize / 2) && y == -(boardSize / 2)) ||
              (x == (-(boardSize / 2)) && y == (boardSize / 2)));
    }
  }
