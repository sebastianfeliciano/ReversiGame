package model.strategies;

import controller.Player;
import controller.PlayerType;
import model.Board;
import model.Move;

public interface IStrategy {
    Move selectMove(Board board, Player player);
}
