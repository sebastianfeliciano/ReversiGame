package adapters;

import model.BoardModel;
import provider.model.Hex;
import provider.model.IMutableModel;
import provider.model.PlayerDisc;

/**
 * Adapter class that implements IMutableModel to interact with BoardModel for mutable operations.
 */
public class IMutableModelAdapter extends IROModelAdapter implements IMutableModel {
  BoardModel model;

  /**
   * Constructs an IMutableModelAdapter with a specified BoardModel.
   *
   * @param model The BoardModel to be adapted.
   */
  public IMutableModelAdapter(BoardModel model) {
    super(model);
  }

  /**
   * Starts the game. If initializeGrid is true, initializes the game grid.
   *
   * @param initializeGrid Indicates whether to initialize the game grid.
   */
  @Override
  public void startGame(boolean initializeGrid) {
    //Initialized already in the constructor.
  }

  /**
   * Makes a move by placing a piece on the board.
   *
   * @param coordQ The Q coordinate for the move.
   * @param coordR The R coordinate for the move.
   * @param who    The player making the move.
   */
  @Override
  public void makeMove(int coordQ, int coordR, PlayerDisc who) {
    this.setLatestMove(new Hex(coordQ, coordR));
    model.placePiece(coordQ, coordR, PlayerDiscAdapter.convertToPlayerType(who));
    model.notifyObservers();
  }

  /**
   * Skips the turn of the specified player.
   *
   * @param who The player whose turn is to be skipped.
   */
  @Override
  public void skipTurn(PlayerDisc who) {
    model.playerPass(PlayerDiscAdapter.convertToPlayerType(who));
    model.notifyObservers();
  }

  /**
   * Notifies about a player's turn.
   *
   * @param player The player whose turn it is.
   */
  @Override
  public void notifyPlayerTurn(PlayerDisc player) {
    model.getCurrentTurn();
    model.notifyObservers();
  }

  /**
   * Notifies that the game is over with the winner's name.
   *
   * @param winner The name of the winning player.
   */
  @Override
  public void notifyGameOver(String winner) {
    model.isGameOver();
    model.notifyObservers();
  }

}
