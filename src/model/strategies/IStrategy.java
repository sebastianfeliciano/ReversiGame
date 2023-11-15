package model.strategies;

import controller.Player;
import controller.PlayerType;
import model.Board;
import model.Move;
import model.ReadOnlyBoardModel;

public interface IStrategy {

    Move selectMove(ReadOnlyBoardModel board, Player player);
}
