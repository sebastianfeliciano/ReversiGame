package adapters;

import java.util.HashMap;
import java.util.Objects;

import controller.players.PlayerType;
import model.BoardModel;
import model.HexShape;
import provider.model.Hex;
import provider.model.IBoard;
import provider.model.PlayerDisc;

/**
 * Adapter class that implements IBoard interface to interact with BoardModel.
 */
public class IBoardAdapter implements IBoard {
  private final BoardModel board;

  /**
   * Constructs an IBoardAdapter with a specified BoardModel.
   *
   * @param board The BoardModel to be adapted.
   */
  public IBoardAdapter(BoardModel board) {
    this.board = Objects.requireNonNull(board);
  }

  /**
   * Gets the size of the board.
   *
   * @return the size of the board.
   */
  @Override
  public int getSize() {
    return board.getBoardSize();
  }


  /**
   * Gets the cells of the board as a map of Hex to PlayerDisc.
   *
   * @return a map of Hex to PlayerDisc representing the board state.
   */
  @Override
  public HashMap<Hex, PlayerDisc> getCells() {
    HexShape[][] hexShapes = board.getRegularBoard().getCellsThatMakeTheBoard();
    HashMap<Hex, PlayerDisc> map = new HashMap<>();

    for (HexShape[] shape : hexShapes) {
      for (HexShape hexShape : shape) {
        if (hexShape == null) {
          continue;
        }
        Hex hex = new Hex(Integer.parseInt(hexShape.getColumn()),
                Integer.parseInt(hexShape.getRow()));
        map.put(hex, PlayerDiscAdapter.convertToPlayerDisc(hexShape.getPlayerType()));
      }
    }
    return map;
  }

  /**
   * Ensures the board has a valid radius.
   *
   * @param size The size to validate.
   */
  @Override
  public void ensureValidRadius(int size) {
    //Initialized in the constructor
    //Can be considered redundant code, but
    //for the purpose of this assignment I have to do this
    //to show clarity.
    board.ensureValidRadius(size);
  }

  /**
   * Obtains the PlayerDisc on a Hex, based on the provider.
   * @param hex which is the objects of the board.
   * @return playerDisc.
   */
  public PlayerDisc getPlayerDiscForHex(Hex hex) {
    // Assuming getCells() returns the mapping of Hex to PlayerDisc
    HashMap<Hex, PlayerDisc> cells = getCells();
    return cells.getOrDefault(hex, PlayerDisc.EMPTY);
  }

  /**
   * Initializes the grid of the board.
   */
  @Override
  public void initializeGrid() {
    //Initialized in the constructor
    //Can be considered redundant code, but
    //for the purpose of this assignment I have to do this
    //to show clarity.
    board.initializeBoard();
  }

  /**
   * Places a disc on the board.
   *
   * @param coordQ The Q coordinate.
   * @param coordR The R coordinate.
   * @param who    The player disc to place.
   */
  @Override
  public void setDisc(int coordQ, int coordR, PlayerDisc who) {
    board.placePiece(coordQ, coordR, PlayerDiscAdapter.convertToPlayerType(who));
  }

  /**
   * Gets the total number of discs on the board.
   *
   * @return the total number of discs.
   */
  @Override
  public int getTotalDiscs() {
    int k = board.countPieces(PlayerType.WHITE);
    int q = board.countPieces(PlayerType.BLACK);
    return k + q;
  }
}
