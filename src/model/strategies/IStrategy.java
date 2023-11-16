package model.strategies;

import java.util.Optional;

import controller.Player;
import controller.PlayerType;
import model.Board;
import model.Move;
import model.ReadOnlyBoardModel;

public interface IStrategy {

  Optional<Move> selectMove(ReadOnlyBoardModel board, Player player);
}
