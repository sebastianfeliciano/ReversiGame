package adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import controller.DirectionsEnum;
import controller.players.Player;
import controller.players.PlayerType;
import model.HexShape;
import model.ReadOnlyBoardModel;
import provider.controller.IModelFeatures;
import provider.model.Hex;
import provider.model.IBoard;
import provider.model.IMutableModel;
import provider.model.IROModel;
import provider.model.PlayerDisc;
import provider.model.Status;

/**
 * Adapter class that implements IROModel to interact with
 * ReadOnlyBoardModel for read-only operations.
 */
public class IROModelAdapter implements IROModel {
  private Hex latestMove;
  ReadOnlyBoardModel model;

  /**
   * Constructs an IROModelAdapter with a specified ReadOnlyBoardModel.
   *
   * @param model The ReadOnlyBoardModel to be adapted.
   */
  public IROModelAdapter(ReadOnlyBoardModel model) {
    this.model = Objects.requireNonNull(model);

  }

  /**
   * Checks if the model is over or not.
   */
  @Override
  public boolean isGameOver() {
    return model.isGameOver();
  }

  /**
   * Gets the score of a specified player.
   *
   * @param p The player disc type (BLACK, WHITE, EMPTY).
   * @return The score of the specified player.
   * @throws IllegalArgumentException if the player disc is null.
   * @throws IllegalStateException    if the player disc is EMPTY.
   */
  @Override
  public int getScore(PlayerDisc p) {
    if (p == null) {
      throw new IllegalArgumentException("PlayerDisc cannot be null.");
    }
    switch (p) {
      case BLACK:
        return model.getScoreBlack();
      case WHITE:
        return model.getScoreWhite();
      case EMPTY:
        throw new IllegalStateException("Cannot get the score as empty");
      default:
        return 3; //Initial start of the game.
    }
  }

  /**
   * Determines the winner of the game.
   *
   * @return The disc type of the winning player, or EMPTY if there's a tie or the game isn't over.
   */
  @Override
  public PlayerDisc getWinner() {
    int black = model.countPieces(PlayerDiscAdapter.convertToPlayerType(PlayerDisc.BLACK));
    int white = model.countPieces(PlayerDiscAdapter.convertToPlayerType(PlayerDisc.WHITE));
    PlayerDisc disc = PlayerDisc.EMPTY;
    if (black > white) {
      disc = PlayerDisc.BLACK;
    }
    if (black < white) {
      disc = PlayerDisc.WHITE;
    }
    return disc;
  }

  /**
   * Gets the current status of the game (Playing, Won, or Stalemate).
   *
   * @return The current status of the game.
   */
  @Override
  public Status getStatus() {
    if (model.isGameOver()) {
      if (this.getWinner() == PlayerDisc.EMPTY) {
        return Status.Stalemate;
      } else {
        return Status.Won;
      }
    } else {
      return Status.Playing;
    }
  }

  /**
   * Gets the radius of the game board.
   *
   * @return The radius of the board.
   */
  @Override
  public int getRadius() {
    return model.getBoardSize() - 1;
  }

  /**
   * Checks if the board is full.
   *
   * @return true if the board is full, false otherwise.
   */
  @Override
  public boolean isBoardFull() {
    return model.isBoardFull();
  }

  /**
   * Gets the board in its current state.
   *
   * @return An IBoard representation of the current game board.
   */
  @Override
  public IBoard getBoard() {
    return new IBoardAdapter(model.getRegularBoard());
  }

  /**
   * Gets the player who has the next turn.
   *
   * @return The disc type of the next player to make a move.
   */
  @Override
  public PlayerDisc getNextPlayerTurn() {
    return PlayerDiscAdapter.convertToPlayerDisc(model.getCurrentTurn().nextPlayer());
  }

  /**
   * Counts the number of discs of a specific type on the board.
   *
   * @param playerDisc The type of disc to count.
   * @return The number of discs of the specified type.
   */
  @Override
  public int countDiscs(PlayerDisc playerDisc) {
    PlayerType k = PlayerDiscAdapter.convertToPlayerType(playerDisc);
    return model.countPieces(k);
  }

  /**
   * Gets the disc type at a specific board coordinate.
   *
   * @param coordQ The Q coordinate.
   * @param coordR The R coordinate.
   * @return The disc type at the specified coordinates.
   */
  @Override
  public PlayerDisc getDiscAt(int coordQ, int coordR) {
    return PlayerDiscAdapter.convertToPlayerDisc(model.getCurrentHex(coordR,
            coordQ).getPlayerType());
  }

  /**
   * Calculates the number of legal moves available for the current player.
   *
   * @return The number of legal moves available.
   */
  @Override
  public int getNumOfLegalMovesForCurrentPlayer() {
    if (model.getCurrentTurn() == PlayerType.WHITE) {
      return model.getValidMovesWithCaptures(new Player("Dummy",
              PlayerType.WHITE, model)).size();
    } else {
      return model.getValidMovesWithCaptures(new Player("Dummy",
              PlayerType.BLACK, model)).size();
    }
  }

  /**
   * Checks if a given coordinate is a valid position on the board.
   *
   * @param q The Q coordinate.
   * @param r The R coordinate.
   * @return true if the coordinate is valid, false otherwise.
   */
  @Override
  public boolean isLegal(int q, int r) {
    int arrayIndexQ = q + model.getBoardSize() / 2;
    int arrayIndexR = r + model.getBoardSize() / 2;

    if (!isValidCoordinate(arrayIndexQ, arrayIndexR)) {
      return false;
    }

    HexShape hex = model.getCurrentHex(arrayIndexR, arrayIndexQ);
    if (hex == null || hex.getPlayerType() != PlayerType.EMPTY) {
      return false;
    }

    PlayerType currentPlayer = model.getCurrentTurn();
    PlayerType opponent = (currentPlayer == PlayerType.BLACK) ? PlayerType.WHITE : PlayerType.BLACK;

    for (DirectionsEnum dir : DirectionsEnum.values()) {
      if (isItAValidLineOfPieces(arrayIndexQ, arrayIndexR, dir, currentPlayer, opponent)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Checks there is a valid line of pieces based on the direction and the coordinates.
   *
   * @param q The Q coordinate.
   * @param r The R coordinate.
   * @return true if the coordinate is valid, false otherwise.
   */
  private boolean isItAValidLineOfPieces(int q, int r, DirectionsEnum dir,
                                         PlayerType currentPlayer, PlayerType opponent) {
    int nextQ = q + dir.getQMove();
    int nextR = r + dir.getRMove();

    if (!isValidCoordinate(nextQ, nextR)) {
      return false;
    }

    HexShape nextHex = model.getCurrentHex(nextR, nextQ);
    if (nextHex == null || nextHex.getPlayerType() != opponent) {
      return false;
    }

    nextQ += dir.getQMove();
    nextR += dir.getRMove();

    while (isValidCoordinate(nextQ, nextR)) {
      nextHex = model.getCurrentHex(nextR, nextQ);

      if (nextHex == null || nextHex.getPlayerType() == PlayerType.EMPTY) {
        return false;
      }

      if (nextHex.getPlayerType() == currentPlayer) {
        return true;
      }

      nextQ += dir.getQMove();
      nextR += dir.getRMove();
    }

    return false;
  }


  private boolean isValidCoordinate(int q, int r) {
    return model.isValidCoordinate(q, r);
  }


  /**
   * Gets a list of neighboring hexes for a given coordinate.
   *
   * @param q the q-coordinate of the hex whose neighbors we want to retrieve.
   * @param r the r-coordinate of the hex whose neighbors we want to retrieve.
   * @return the list.
   */
  @Override
  public List<Hex> getInboundNeighbors(int q, int r) {
    List<Hex> neighbors = new ArrayList<>();

    for (int direction = 0; direction < 6; direction++) {
      int newQ = q;
      int newR = r;

      switch (direction) {
        case 0:
          newQ = q - 1;
          break;
        case 1:
          newR = r - 1;
          break;
        case 2:
          newQ = q + 1;
          newR = r - 1;
          break;
        case 3:
          newQ = q + 1;
          break;
        case 4:
          newR = r + 1;
          break;
        case 5:
          newQ = q - 1;
          newR = r + 1;
          break;
        default:
          throw new IllegalArgumentException("Error finding inbound neighbors!");
      }

      if (model.isValidCoordinate(newQ, newR)) {
        HexShape neighborHexShape = model.getCurrentHex(newQ + model.getBoardSize() / 2,
                newR + model.getBoardSize() / 2);
        if (neighborHexShape != null) {
          neighbors.add(new Hex(newQ, newR));
        }
      }
    }
    return neighbors;
  }


  /**
   * Created a list of all the valid paths on a board.
   *
   * @param startHex represents the cell that a player could potentially play a disc on.
   * @return a list of hexes.
   */
  @Override
  public List<List<Hex>> getAllValidPaths(Hex startHex) {
    List<List<Hex>> allValidPaths = new ArrayList<>();

    PlayerType currentPlayer = model.getCurrentTurn();
    PlayerType opponent = currentPlayer.nextPlayer();

    for (DirectionsEnum dir : DirectionsEnum.values()) {
      List<Hex> validPath = new ArrayList<>();
      int nextQ = startHex.getQ() + dir.getQMove();
      int nextR = startHex.getR() + dir.getRMove();

      int arrayIndexQ = nextQ + model.getBoardSize() / 2;
      int arrayIndexR = nextR + model.getBoardSize() / 2;

      while (model.isValidCoordinate(arrayIndexQ, arrayIndexR)) {
        HexShape nextHexShape = model.getCurrentHex(arrayIndexR, arrayIndexQ);
        if (nextHexShape == null || nextHexShape.getPlayerType() != opponent) {
          break;
        }

        validPath.add(new Hex(nextQ, nextR));
        nextQ += dir.getQMove();
        nextR += dir.getRMove();

        arrayIndexQ = nextQ + model.getBoardSize() / 2;
        arrayIndexR = nextR + model.getBoardSize() / 2;

        if (model.isValidCoordinate(arrayIndexQ, arrayIndexR)) {
          nextHexShape = model.getCurrentHex(arrayIndexR, arrayIndexQ);
          if (nextHexShape != null && nextHexShape.getPlayerType() == currentPlayer) {
            allValidPaths.add(validPath);
            break;
          }
        }
      }
    }

    return allValidPaths;
  }


  /**
   * Helper for setting the latestMove.
   */
  public void setLatestMove(Hex move) {
    this.latestMove = move;
  }

  /**
   * Gets the latest move done, if null, will return
   * Optional.empty().
   */
  @Override
  public Optional<Hex> getLatestMove() {
    return Optional.ofNullable(latestMove);
  }

  /**
   * Add features to the interface.
   */
  @Override
  public void addFeatures(IModelFeatures features) {
    // Features is assigned in another class. Do not need to
    // reassign.
  }


  /**
   * Gets the deep copied version of the model.
   */
  @Override
  public IMutableModel getPreviousModel(IROModel currentModel) {
    return new IMutableModelAdapter(model.deepCopy());
  }

  /**
   * Checks if the game has started, and this starts when a player does a move.
   */
  @Override
  public boolean isGameStarted() {
    return latestMove != null;
  }
}
