package model.strategies;

import controller.PlayerType;
import model.Board;
import model.Move;

public interface IStratedgy {
    Move selectMove(Board board, PlayerType player);
}
