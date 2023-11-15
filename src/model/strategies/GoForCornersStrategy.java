package model.strategies;

import controller.Player;
import model.Board;
import model.Move;
import model.ReadOnlyBoardModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class GoForCornersStrategy implements IStrategy {
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
    List<Move> cornerMoves = new ArrayList<>();
    List<Move> validMoves = board.getValidMovesWithCaptures(player);
    if (validMoves.isEmpty()) {
      logger.info("No valid moves available. Passing turn.");
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
      Move moveThen = validMoves.get(rand.nextInt(validMoves.size()));
      logger.info("Selected move: " + moveThen.getX() + ", " + moveThen.getY());
      return moveThen;
    } else {
      Move bestMove = cornerMoves.get(0);
      for (Move cornerMove : cornerMoves) {
        if (cornerMove.getPiecesCaught() > bestMove.getPiecesCaught()) {
          bestMove = cornerMove;
        }
      }
      logger.info("Selected move: " + bestMove.getX() + ", " + bestMove.getY());
      logger.info("Capturing "+bestMove.getPiecesCaught()+  " pieces!");
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
