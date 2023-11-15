package model.strategies;

import controller.Player;
import model.Board;
import model.Move;
import model.ReadOnlyBoardModel;

import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CaptureStrategy implements IStrategy {
  private static final Logger logger = Logger.getLogger(CaptureStrategy.class.getName());

  static {
    try {
      FileHandler fileHandler = new FileHandler("strategy-transcript.txt");
      fileHandler.setFormatter(new SimpleFormatter());
      logger.addHandler(fileHandler);
    } catch (Exception e) {
      logger.warning("Failed to initialize logger file handler: " + e.getMessage());
    }
  }

  @Override
  public Move selectMove(ReadOnlyBoardModel board, Player player) {
    logger.info("Selecting move for player: " + player.getName());
    List<Move> validMoves = board.getValidMovesWithCaptures(player);

    if (validMoves.isEmpty()) {
      logger.info("No valid moves available. Passing turn.");
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
    logger.info("Selected move: " + bestMove.getX() + ", " + bestMove.getY());
    logger.info("Capturing "+bestMove.getPiecesCaught()+  " pieces!");
    return bestMove;
  }
}

