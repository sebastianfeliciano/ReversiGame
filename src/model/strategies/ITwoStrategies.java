package model.strategies;

import controller.Player;
import model.Board;
import model.Move;

public interface ITwoStrategies extends IStrategy {
  Move selectMove(Board board, Player player);
}

