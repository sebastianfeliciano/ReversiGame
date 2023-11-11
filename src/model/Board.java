package model;

import controller.DirectionsEnum;
import controller.PlayerType;

import java.util.ArrayList;
import java.util.List;

/**
 * Sets up a board for the controller to use.
 */
public class Board implements ReadOnlyBoardModel, BoardModel {
  public int BOARD_SIZE;
  public HexShape[][] cellsThatMakeTheBoard;
  private boolean whitePassed = false;
  private boolean blackPassed = false;

  /**
   * Controller used in the mine to set the size of the board.
   * A default board is size 7.
   */
  public Board() {
    this(7);
  }

  /**
   * Constructor used for testing.
   * Throws an exception if the game size is even or less than 5,
   * because a valid hexagon cannot be created from either value.
   */
  public Board(int sizeOfBoard) {
    if (sizeOfBoard < 7 || (sizeOfBoard % 2 == 0)) {
      throw new IllegalStateException("The game must be a minimum of size 5 and cannot be even!");
    }

    BOARD_SIZE = sizeOfBoard;
    cellsThatMakeTheBoard = new HexShape[BOARD_SIZE][BOARD_SIZE];
    int midPoint = BOARD_SIZE / 2;

    for (int row = 0; row < BOARD_SIZE; row++) {
      int startQ;
      int endQ;

      if (row <= midPoint) {
        startQ = midPoint - row;
        endQ = BOARD_SIZE;
      } else {
        startQ = 0;
        endQ = BOARD_SIZE - row + midPoint;
      }

      for (int column = startQ; column < endQ; column++) {
        int q = column - midPoint;
        int r = row - midPoint;
        cellsThatMakeTheBoard[row][column] = new HexShape(r, q, null);
      }
    }
    this.getCurrentHex(this.BOARD_SIZE / 2, this.BOARD_SIZE / 2 + 1).setPlayerType(PlayerType.BLACK);
    this.getCurrentHex(this.BOARD_SIZE / 2 + 1, this.BOARD_SIZE / 2).setPlayerType(PlayerType.WHITE);
    this.getCurrentHex(this.BOARD_SIZE / 2, this.BOARD_SIZE / 2 - 1).setPlayerType(PlayerType.WHITE);
    this.getCurrentHex(this.BOARD_SIZE / 2 + 1, this.BOARD_SIZE / 2 - 1).setPlayerType(PlayerType.BLACK);
    this.getCurrentHex(this.BOARD_SIZE / 2 - 1, this.BOARD_SIZE / 2).setPlayerType(PlayerType.BLACK);
    this.getCurrentHex(this.BOARD_SIZE / 2 - 1, this.BOARD_SIZE / 2 + 1).setPlayerType(PlayerType.WHITE);

  }

  /**
   * Returns the current hex shape based on row and column.
   */
  public HexShape getCurrentHex(int row, int column) {
    return cellsThatMakeTheBoard[row][column];
  }

  /**
   * Flips a player type based on passed in coordinates passed
   * in by the player.
   * Breaks out of the loop if the player, opponent,
   * or direction is invalid.
   *
   */
  public void flipPieces(int x, int y, PlayerType currentPlayer) {
    if (currentPlayer == null) {
      System.out.println("Current player is null!");
      return;
    }

    PlayerType opponent = currentPlayer.nextPlayer();
    if (opponent == null) {
      System.out.println("Opponent is null!");
      return;
    }

    for (DirectionsEnum dir : DirectionsEnum.values()) {
      if (dir == null) {
        System.out.println("Direction is null!");
        continue;
      }

      int nextQ = x + dir.getQMove();
      int nextR = y + dir.getRMove();

      HexShape currentHex = this.getCurrentHex(nextR, nextQ);
      if (currentHex == null) {
        continue;
      }

      PlayerType hexPlayer = currentHex.getPlayerType();
      if (hexPlayer == null) {
        continue;
      }

      List<HexShape> piecesToFlip = new ArrayList<>();

      while (isValidCoordinate(nextQ, nextR) && this.getCurrentHex(nextQ, nextR).getPlayerType().equals(opponent)) {
        piecesToFlip.add(getCurrentHex(nextQ, nextR));
        nextQ += dir.getQMove();
        nextR += dir.getRMove();

        currentHex = this.getCurrentHex(nextQ, nextR);
        if (currentHex == null) {
          break;
        }

        hexPlayer = currentHex.getPlayerType();
        if (hexPlayer == null) {
          break;
        }
      }

      if (isValidCoordinate(nextQ, nextR)) {
        assert hexPlayer != null;
        for (HexShape piece : piecesToFlip) {
          piece.setPlayerType(currentPlayer);
        }
      }
    }
  }

  /**
   * Determines if a valid move is passed in,
   * based on coordinates, and a player type.
   */
  public boolean isValidMove(int x, int y, PlayerType playerType) {
    if (x > this.getBoardSize()/2 || y > this.getBoardSize()/2
            || x < -this.getBoardSize()/2 || y < -this.getBoardSize()/2){
      return false;
    }
    int q = x + this.getBoardSize()/ 2;
    int r = y + this.getBoardSize() / 2;

    if (Math.abs(r) >= this.getBoardSize()) {
      return false;
    }

    if (!getCurrentHex(q, r).getPlayerType().equals(PlayerType.EMPTY)) {
      return false;
    }

    PlayerType currentPlayer = playerType;
    PlayerType opponent = currentPlayer.nextPlayer();


    for (DirectionsEnum dir : DirectionsEnum.values()) {
      int nextQ = q + dir.getQMove();
      int nextR = r + dir.getRMove();

      //opponent's piece next to the current position
      if (isValidCoordinate(nextQ, nextR) && getCurrentHex(nextR, nextQ).getPlayerType().equals(opponent)) {
        while (isValidCoordinate(nextQ, nextR) && !getCurrentHex(nextR, nextQ).getPlayerType().equals(PlayerType.EMPTY)) {
          nextQ += dir.getQMove();
          nextR += dir.getRMove();

          if (isValidCoordinate(nextQ, nextR) && getCurrentHex(nextR, nextQ).getPlayerType().equals(currentPlayer)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Determines is a valid coordinate was passed in
   * based on rows and columns.
   */
  public boolean isValidCoordinate(int q, int r) {
    return q >= 0 && q < this.getBoardSize() && r >= 0 && r < this.getBoardSize();
  }

  /**
   * Places a certain piece in the board, based on
   * row and column, and playerType.
   */
  public void placePiece(int q, int r, PlayerType type) {
    this.getCurrentHex(r, q).setPlayerType(type);
    whitePassed = false;
    blackPassed = false;
  }

  /**
   * Changes the state of each player if they passed or not.
   */
  public void playerPassed(PlayerType playerType) {
    if (playerType == PlayerType.WHITE) {
      whitePassed = true;
    } else if (playerType == PlayerType.BLACK) {
      blackPassed = true;
    }
  }

  @Override
  public int getScoreWhite(Board board) {
    if (isGameOver()) {
      return 0;
    }
    return board.countPieces(PlayerType.WHITE);
  }

  @Override
  public int getScoreBlack(Board board) {
    if (isGameOver()) {
      return 0;
    }
    return board.countPieces(PlayerType.BLACK);
  }


  /**
   * Returns the size of a board.
   */
  @Override
  public int getBoardSize() {
    return this.BOARD_SIZE;
  }

  /**
   * Determines whether a game is over
   * based on if the board is full, or if both
   * players have skipped their turn.
   */
  public boolean isGameOver() {
    return (isBoardFull() || bothPlayersPassed() || isPlayerTrapped(PlayerType.WHITE) || isPlayerTrapped(PlayerType.BLACK));
  }

  /**
   * Determines if both players have skipped their turns.
   */
  private boolean bothPlayersPassed() {
    return whitePassed && blackPassed;
  }

  /**
   * Determines whether a player is trapped, and has no valid next move.
   */
  private boolean isPlayerTrapped(PlayerType player) {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        HexShape hex = cellsThatMakeTheBoard[i][j];
        if (hex != null && hex.getPlayerType() == player) {
          if (isValidMove(i - BOARD_SIZE / 2, j - BOARD_SIZE / 2, player)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Determines if a board is completely full,
   * causing the game to be over.
   */
  public boolean isBoardFull() {
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        HexShape hex = getCurrentHex(i, j);
        if (hex == null) {
          continue;
        }
        if (hex.getPlayerType() == null || hex.getPlayerType() == PlayerType.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Counts the amount of pieces that exist in a board.
   */
  public int countPieces(PlayerType type) {
    int count = 0;
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if (getCurrentHex(i, j) != null && getCurrentHex(i, j).getPlayerType() == type) {
          count++;
        }
      }
    }
    return count;
  }

  public int getMidPoint() {
    return this.getBoardSize() / 2;
  }

  /**
   * Checks whether a player has passed their turn.
   */
  public boolean hasPlayerPassed(PlayerType type) {
    if (type == PlayerType.WHITE) {
      return whitePassed;
    } else if (type == PlayerType.BLACK) {
      return blackPassed;
    }
    return false;
  }

  public Board deepCopy() {
    Board newBoard = new Board(this.BOARD_SIZE);
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        if (this.cellsThatMakeTheBoard[i][j] != null) {
          newBoard.cellsThatMakeTheBoard[i][j] = new HexShape(
                  i, j, this.cellsThatMakeTheBoard[i][j].getPlayerType());
        }
      }
    }
    newBoard.whitePassed = this.whitePassed;
    newBoard.blackPassed = this.blackPassed;
    return newBoard;
  }
}
