package adapters;

import java.util.Objects;

import controller.players.PlayerType;
import model.BoardModel;
import provider.controller.IViewFeatures;
import provider.model.Hex;
import provider.model.IBoard;
import provider.model.PlayerDisc;
import provider.player.IPlayer;
import provider.view.IGraphicalReversiView;

/**
 * Adapter class that implements the IViewFeatures interface to bridge
 * between the model and the graphical view in a Reversi game.
 */
public class IViewFeaturesAdapter implements IViewFeatures {
  IBoard model;
  BoardModel modelP;
  private IGraphicalReversiView v;
  private IPlayer player;

  /**
   * Constructs an IViewFeaturesAdapter.
   *
   * @param player The player controller.
   * @param modelP The model representing the game board.
   * @param v      The graphical view of the Reversi game.
   */
  public IViewFeaturesAdapter(IPlayer player, BoardModel modelP, IGraphicalReversiView v) {
    this.modelP = Objects.requireNonNull(modelP);
    this.player = Objects.requireNonNull(player);
    this.v = Objects.requireNonNull(v);
    if (modelP.isGameOver()) {
      String winner = "Tie";
      int white = modelP.countPieces(PlayerDiscAdapter.convertToPlayerType(PlayerDisc.WHITE));
      int black = modelP.countPieces(PlayerDiscAdapter.convertToPlayerType(PlayerDisc.BLACK));

      if (white > black) {
        winner = "WHITE";
      }

      if (black > white) {
        winner = "BLACK";
      }
      v.displayGameOver(winner);
    }
  }

  /**
   * Skips the current player's turn.
   */
  @Override
  public void skipTurn() {
    if (modelP.getCurrentTurn() == PlayerDiscAdapter.convertToPlayerType(player.getPlayerDisc())) {
      modelP.playerPass(PlayerDiscAdapter.convertToPlayerType(player.getPlayerDisc()));
      if (modelP.isGameOver()) {
        notifyGameOver();
      }
      modelP.switchTurns();
    } else {
      v.displayErrorMessage("Not your turn!");
    }
  }

  /**
   * Places a disc on the board at the specified position.
   *
   * @param selectedHexQ The Q-coordinate of the hexagonal cell.
   * @param selectedHexR The R-coordinate of the hexagonal cell.
   */
  @Override
  public void placeDisc(int selectedHexQ, int selectedHexR) {
    HexAdapter hexAdapter = new HexAdapter(new Hex(selectedHexQ,
            selectedHexR), modelP.getCurrentTurn());

    int x = hexAdapter.getQ() - (modelP.getBoardSize() / 2);
    int y = hexAdapter.getR() - (modelP.getBoardSize() / 2);

    if (!modelP.isGameOver()) {
      if (modelP.getCurrentTurn()
              == PlayerDiscAdapter.convertToPlayerType(player.getPlayerDisc())) {
        if (modelP.isValidCoordinate(hexAdapter.getQ() + (modelP.getBoardSize() / 2),
                hexAdapter.getR() + (modelP.getBoardSize() / 2))) {
          if (modelP.isValidMove(selectedHexQ, selectedHexR,
                  PlayerDiscAdapter.convertToPlayerType(player.getPlayerDisc()))) {
            modelP.placePiece(hexAdapter.getQ() + (modelP.getBoardSize() / 2),
                    hexAdapter.getR() + (modelP.getBoardSize() / 2), PlayerType.WHITE);
            modelP.flipPieces(hexAdapter.getQ() + (modelP.getBoardSize() / 2),
                    hexAdapter.getR() + (modelP.getBoardSize() / 2), PlayerType.WHITE);
            modelP.switchTurns();
          } else {
            v.displayErrorMessage("Invalid move");
          }
        } else {
          v.displayErrorMessage("Invalid move");
        }
      } else {
        v.displayErrorMessage("Not your turn!");
      }
    } else {
      notifyGameOver();
    }
  }

  /**
   * Helper for notifying the game is over.
   */
  private void notifyGameOver() {
    String winner = determineWinner();
    v.displayGameOver(winner);
  }

  /**
   * Determines the winner based on the largest amount of pieces
   * on the board.
   *
   * @return string.
   */
  private String determineWinner() {
    int white = modelP.countPieces(PlayerDiscAdapter.convertToPlayerType(PlayerDisc.WHITE));
    int black = modelP.countPieces(PlayerDiscAdapter.convertToPlayerType(PlayerDisc.BLACK));
    if (white > black) {
      return "WHITE";
    } else if (black > white) {
      return "BLACK";
    } else {
      return "Tie";
    }
  }

  /**
   * Sets the graphical view for the adapter.
   *
   * @param v The graphical Reversi view to be set.
   */
  @Override
  public void setView(IGraphicalReversiView v) {
    this.v = v;
  }

  /**
   * Sets the player for the view adapter.
   *
   * @param player The player to be set.
   */
  @Override
  public void setPlayer(IPlayer player) {
    this.player = player;
  }
}
