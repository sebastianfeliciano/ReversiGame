package extraCreditTests;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.Mock;
import model.Move;
import model.SquareBoard;
import model.strategies.GoForCornersStrategy;

/**
 * A list of tests for the GoForCorner Strategy performed on a
 * Square board.
 */
public class ExampleGoForCornerSquare {

  @Test
  public void testNonPass() {
    SquareBoard board1 = new SquareBoard(8);
    Player player1 = new Player("Player1", PlayerType.WHITE, board1);
    Player player2 = new Player("Player2", PlayerType.BLACK, board1);
    player1.makeMove();
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, valid, sb);
    mock.playerPass(player1.getType());
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board1, gfc);
    player.makeMove();
    Assert.assertFalse(mock.getLog().toString().contains("pass"));
  }

  @Test
  public void testGetScoreWhiteBlack() {
    SquareBoard board = new SquareBoard(8);
    List<Move> valid = new ArrayList<>();
    valid.add(new Move(0, 1));
    valid.add(new Move(1, 1));
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.getScoreWhite();
    mock.getScoreBlack();
    Assert.assertTrue(mock.getLog().toString().contains("" + 2));
    Assert.assertTrue(mock.getLog().toString().contains("" + 2));
  }

  @Test
  public void testGetBoardSize() {
    SquareBoard board = new SquareBoard(8);
    List<Move> valid = new ArrayList<>();
    valid.add(new Move(0, 1));
    valid.add(new Move(1, 1));
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    player.makeMove();
    mock.getBoardSize();
    Assert.assertTrue(mock.getLog().toString().contains("" + 8));
  }

  @Test
  public void testBoardFull() {
    SquareBoard board = new SquareBoard(8);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    player.makeMove();
    mock.isBoardFull();
    Assert.assertTrue(mock.getLog().toString().contains("board is not full"));
  }

  @Test
  public void countPieces() {
    SquareBoard board = new SquareBoard(8);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    player.makeMove();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    System.out.println(sb);
    Assert.assertTrue(mock.getLog().toString().contains("2"));
  }

  @Test
  public void testIsValidMove() {
    SquareBoard board1 = new SquareBoard(8);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board1, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, valid, sb);
    mock.isValidMove(4, 3, PlayerType.WHITE);
    System.out.println(sb);
    Assert.assertTrue(mock.getLog().toString().contains("Not a valid move"));
  }


  @Test
  public void testValidCoordinate() {
    SquareBoard board = new SquareBoard(8);
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    player.makeMove();
    mock.isValidCoordinate(-11, 1);
    Assert.assertTrue(mock.getLog().toString().contains("Not a valid coordinate."));
  }

  @Test
  public void testGetValidMovesWithCaptures() {
    SquareBoard board = new SquareBoard(8);
    Board board1 = new Board();
    List<Move> valid = new ArrayList<>();
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board1, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    player.makeMove();
    mock.getValidMovesWithCaptures(player);
    Assert.assertTrue(mock.getLog().toString().contains("Checking valid :"));
  }

  @Test
  public void testIsCornerMove() {
    SquareBoard board = new SquareBoard(8);
    List<Move> valid = new ArrayList<>();
    Move move = new Move(0, 0);
    valid.add(move);
    GoForCornersStrategy gfc = new GoForCornersStrategy();
    AIPlayer player = new AIPlayer("", PlayerType.WHITE, board, gfc);
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board, valid, sb);
    mock.countPieces(PlayerType.WHITE);
    player.makeMove();
    mock.isCornerMove(move, 11);
    Assert.assertTrue(mock.getLog().toString().contains("is a corner move"));
  }


}
