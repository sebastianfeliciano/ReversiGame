package provider.view;

import java.io.IOException;
import java.util.Objects;

import provider.model.Hex;
import provider.model.IBoard;
import provider.model.IROModel;
import provider.model.PlayerDisc;

/**
 * Represents a textual view of the cs3500.reversi.Reversi game.
 * This view is used to display the game state in a printed format, and it is what the player
 * will see while they are playing the game. This View takes in a IROModel to ensure that
 * the View does not have access to mutating the model.
 */
public class ReversiTextualView implements IView {
  private final IBoard board;
  private final IROModel model;
  private final Appendable out;
  private final int boardRadius;

  /**
   * Constructs a new ReversiTextualView with the given Board, IMutableModel, and Appendable.
   *
   * @param board the Board to be used in this view.
   * @param model the IMutableModel to be used in this view.
   * @param out   the Appendable to be used in this view.
   * @throws IllegalArgumentException if the given Board, IMutableModel, or Appendable is null.
   * @throws IllegalArgumentException if the given Board and IMutableModel are not the same size.
   */
  public ReversiTextualView(IBoard board, IROModel model, Appendable out) {
    if (board == null || model == null || out == null) {
      throw new IllegalArgumentException("Can't have a null argument");
    } else if (board.getSize() != model.getRadius()) {
      throw new IllegalArgumentException("Board and model must be the same size");
    }
    this.model = model;
    this.board = board;
    // need to - 1 because it should be not inclusive (0-based index)
    this.boardRadius = board.getSize() - 1;
    this.out = out;
  }

  /**
   * Prints an individual row of the board.
   *
   * @param r      the r coordinate of the row.
   * @param startQ the starting q coordinate of the row.
   * @param endQ   the ending q coordinate of the row.
   * @return a StringBuilder representing the row.
   */
  private StringBuilder printRows(int r, int startQ, int endQ) {
    StringBuilder sb = new StringBuilder();

    for (int curQ = startQ; curQ <= endQ; curQ++) {
      PlayerDisc curHex = board.getCells().get(new Hex(curQ, r));
      if (curHex == null) {
        throw new IllegalArgumentException("Invalid hex");
      } else if (curHex.equals(PlayerDisc.BLACK)) {
        sb.append("X ");
      } else if (curHex.equals(PlayerDisc.WHITE)) {
        sb.append("O ");
      } else {
        sb.append("_ ");
      }
    }
    appendNewLine(sb);
    return sb;
  }

  /**
   * Prints the entire board by appending each row on a new line to a StringBuilder.
   * The board is printed in a hexagonal shape by printing the top half of the board first with
   * proper spacing, then the bottom half with proper spacing.
   *
   * @return a StringBuilder representing the entire board.
   */
  protected StringBuilder printBoard() {
    StringBuilder sb = new StringBuilder();

    // top half of the hexagon board
    renderHalf(sb, -boardRadius, 0, 1);
    // bottom half of the hexagon board
    renderHalf(sb, 1, boardRadius, -1);

    return sb;
  }

  /**
   * Renders the top or bottom half of the board.
   *
   * @param sb          the StringBuilder to append to.
   * @param startingR   the starting r coordinate of the half.
   * @param endingR     the ending r coordinate of the half.
   * @param topOrBottom 1 if top half, -1 if bottom half.
   */
  private void renderHalf(StringBuilder sb, int startingR, int endingR, int topOrBottom) {
    for (int r = startingR; r <= endingR; r++) {
      int startQ = Math.max(-boardRadius, -r - boardRadius);
      int endQ = Math.min(boardRadius, -r + boardRadius);

      for (int leftMargin = 0; leftMargin < -r * topOrBottom; leftMargin++) {
        appendSpace(sb);
      }
      sb.append(printRows(r, startQ, endQ));
    }
  }

  /**
   * Prints a statement dictating who's turn it is.
   *
   * @return a StringBuilder with relevant player's turn.
   */
  protected StringBuilder printPlayerTurn() {
    StringBuilder sb = new StringBuilder();
    switch (model.getNextPlayerTurn()) {
      case BLACK:
        sb.append("It's Black's (X) Turn!");
        appendNewLine(sb);
        break;
      case WHITE:
        sb.append("It's White's (O) Turn!");
        appendNewLine(sb);
        break;
      default:
        throw new IllegalArgumentException("Invalid player");
    }
    return sb;
  }

  /**
   * Prints a line with each players' scores.
   *
   * @return a descriptive StringBuilder with two scores and their players
   */
  protected StringBuilder printScores() {
    StringBuilder sb = new StringBuilder();
    sb.append("Current Score -> Black: ");
    sb.append(model.getScore(PlayerDisc.BLACK));
    sb.append(" vs. White: ");
    sb.append(model.getScore(PlayerDisc.WHITE));
    appendNewLine(sb);

    return sb;
  }

  /**
   * a helper method that appends a new line to existing StringBuilder.
   *
   * @param sb the existing/given StringBuilder that is getting a new line added
   */
  private void appendNewLine(StringBuilder sb) {
    sb.append("\n");
  }

  /**
   * a helper method that appends an empty space to existing StringBuilder.
   *
   * @param sb the existing/given StringBuilder that is getting a new space added
   */
  private void appendSpace(StringBuilder sb) {
    sb.append(" ");
  }

  /**
   * Renders the game state to the Appendable.
   *
   * @throws RuntimeException if the Appendable fails to render.
   */
  public void render() {
    try {
      out.append(printPlayerTurn());
      out.append(printScores());
      out.append(printBoard());
    } catch (IOException e) {
      throw new RuntimeException("Failed to render");
    }
  }

  /**
   * Determines if this ReversiTextualView is equal to the given Object.
   *
   * @param o the Object to be compared to this ReversiTextualView.
   * @return true if the given Object is a ReversiTextualView and has the same Board and
   *        model as this ReversiTextualView
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    } else if (!(o instanceof ReversiTextualView)) {
      return false;
    } else {
      ReversiTextualView that = (ReversiTextualView) o;
      return this.board.equals(that.board)
              && this.model.equals(that.model)
              && this.out.equals(that.out)
              && this.boardRadius == that.boardRadius;
    }
  }

  /**
   * Returns the hashcode of this ReversiTextualView.
   *
   * @return and integer representing the hashcode of this ReversiTextualView.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.board, this.model, this.out, this.boardRadius);
  }

  /**
   * A human-readable String representation of this ReversiTextualView.
   *
   * @return a String representation of this ReversiTextualView object.
   */
  @Override
  public String toString() {
    return "ReversiTextualView{"
            + "board=" + board
            + ", model=" + model
            + ", out=" + out
            + ", boardRadius=" + boardRadius
            + "}";
  }

}
