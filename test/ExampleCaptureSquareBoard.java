import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.Mock;
import model.Move;
import model.ReadOnlyBoardModel;
import model.SquareBoard;
import model.strategies.CaptureStrategy;

/**
 * A list of tests for the capture strategy on a square board.
 */
public class ExampleCaptureSquareBoard {

  @Test
  public void testFirstMoveMadeByAIPlayer() {
    CaptureStrategy strategy = new CaptureStrategy();
    SquareBoard mockBoard = new SquareBoard(8);
    AIPlayer player1 = new AIPlayer("Player 1", PlayerType.WHITE, mockBoard, strategy);
    mockBoard.placePiece(4, 2, PlayerType.BLACK);
    Optional<Move> selectedMove = strategy.selectMove(mockBoard, player1);
    Optional<Move> expectedMove = Optional.of(new Move(4, 1, 1));
    Assert.assertEquals(expectedMove, selectedMove);
  }

  @Test
  public void testMaxFlipChosenAfterPlayer1Goes() {
    CaptureStrategy strategy = new CaptureStrategy();
    SquareBoard mockBoard = new SquareBoard(8);
    Player player1 = new Player("Player 2", PlayerType.WHITE, mockBoard);
    AIPlayer player2 = new AIPlayer("Player 1", PlayerType.BLACK, mockBoard, strategy);
    player1.makeMove();
    ReadOnlyBoardModel copy = mockBoard.getReadOnlyBoard();
    Optional<Move> selectedMove = strategy.selectMove(copy, player2);
    Optional<Move> expectedMove = Optional.of(new Move(4, 2, 1));
    Assert.assertEquals(selectedMove.get().getX(), expectedMove.get().getX());
  }

  @Test
  public void testChoosingTheRightCoordinateWithListOfValidMoves() {
    CaptureStrategy strategy = new CaptureStrategy();
    Board mockBoard = new Board(7);
    AIPlayer player1 = new AIPlayer("Player 1", PlayerType.WHITE, mockBoard, strategy);
    Player player2 = new Player("Player 2", PlayerType.BLACK, mockBoard);
    Optional<Move> selectedMove = strategy.selectMove(mockBoard, player1);

    List<Move> allAvalibleMovesForAI = mockBoard.getValidMovesWithCaptures(player1);

    List<Move> expectedAllAvalibleMovesForAI = new ArrayList<>();
    Move first = new Move(1, -2, 1);
    Move two = new Move(-1, -1, 1);
    Move three = new Move(2, -1, 1);
    Move four = new Move(-2, 1, 1);
    Move five = new Move(1, 1, 1);
    Move six = new Move(-1, 2, 1);
    expectedAllAvalibleMovesForAI.add(first);
    expectedAllAvalibleMovesForAI.add(two);
    expectedAllAvalibleMovesForAI.add(three);
    expectedAllAvalibleMovesForAI.add(four);
    expectedAllAvalibleMovesForAI.add(five);
    expectedAllAvalibleMovesForAI.add(six);

    for (Move move : allAvalibleMovesForAI) {
      Assert.assertTrue(expectedAllAvalibleMovesForAI.contains(move));
    }

    for (Move move : expectedAllAvalibleMovesForAI) {
      Assert.assertTrue(allAvalibleMovesForAI.contains(move));
    }

    //Getting the most topLeft Coordinate since all pieces placed are 1.
    Optional<Move> expectedMove = Optional.of(new Move(1, -2, 1));

    Assert.assertEquals(expectedMove, selectedMove);
    Optional<Move> selectedMove2 = strategy.selectMove(mockBoard, player2);
    Assert.assertEquals(expectedMove, selectedMove2);
  }


  @Test
  public void testPassDidNotOccur() {
    SquareBoard board1 = new SquareBoard(8);
    Player player1 = new Player("Player1", PlayerType.WHITE, board1);
    Player player2 = new Player("Player2", PlayerType.BLACK, board1);
    Assert.assertEquals(2, board1.countPieces(PlayerType.WHITE));
    Assert.assertEquals(2, board1.countPieces(PlayerType.BLACK));
    List<Move> valid = new ArrayList<>();
    CaptureStrategy ct = new CaptureStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, valid, sb);
    mock.hasPlayerPassed(player1.getType());
    System.out.println(sb);
  }

  @Test
  public void testGetBoardSize() {
    SquareBoard board1 = new SquareBoard(8);
    Player player1 = new Player("Player1", PlayerType.WHITE, board1);
    Player player2 = new Player("Player2", PlayerType.BLACK, board1);
    Assert.assertEquals(2, board1.countPieces(PlayerType.WHITE));
    Assert.assertEquals(2, board1.countPieces(PlayerType.BLACK));
    player1.makeMove();
    List<Move> valid = new ArrayList<>();
    CaptureStrategy ct = new CaptureStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, valid, sb);
    AIPlayer player = new AIPlayer("e", PlayerType.WHITE, board1, ct);
    player.makeMove();
    mock.getBoardSize();
    Assert.assertTrue(mock.getLog().toString().contains("8"));
  }

  @Test
  public void isNotFull() {
    SquareBoard board1 = new SquareBoard(8);
    Player player1 = new Player("Player1", PlayerType.WHITE, board1);
    Assert.assertEquals(2, board1.countPieces(PlayerType.WHITE));
    Assert.assertEquals(2, board1.countPieces(PlayerType.BLACK));
    player1.makeMove();
    List<Move> valid = new ArrayList<>();
    CaptureStrategy ct = new CaptureStrategy();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, valid, sb);
    AIPlayer player = new AIPlayer("e", PlayerType.WHITE, board1, ct);
    player.makeMove();
    mock.isBoardFull();
    System.out.println(sb);
    Assert.assertTrue(mock.getLog().toString().contains("The board is not full."));
  }

  @Test
  public void countPieces() {
    SquareBoard board1 = new SquareBoard(8);
    Assert.assertEquals(2, board1.countPieces(PlayerType.WHITE));
    Assert.assertEquals(2, board1.countPieces(PlayerType.BLACK));

    CaptureStrategy ct = new CaptureStrategy();
    SquareBoard board2 = new SquareBoard(8);
    List<Move> validMoves = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, validMoves, sb);
    AIPlayer player4 = new AIPlayer("Player1", PlayerType.WHITE, board2, ct);
    Player player3 = new Player("Player2", PlayerType.BLACK, board2);

    player3.makeMove();
    player4.makeMove();

    mock.countPieces(PlayerType.WHITE);
    Assert.assertTrue(mock.getLog().toString().contains("Counting"));
    Assert.assertTrue(mock.getLog().toString().contains("2"));
  }

  @Test
  public void testIsValidMove() {
    SquareBoard board1 = new SquareBoard(8);
    Assert.assertEquals(2, board1.countPieces(PlayerType.WHITE));
    Assert.assertEquals(2, board1.countPieces(PlayerType.BLACK));
    CaptureStrategy ct = new CaptureStrategy();
    SquareBoard board2 = new SquareBoard(8);
    List<Move> validMoves = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, validMoves, sb);
    AIPlayer player4 = new AIPlayer("Player1", PlayerType.WHITE, board2, ct);
    Player player3 = new Player("Player2", PlayerType.BLACK, board2);
    player3.makeMove();
    player4.makeMove();
    mock.isValidMove(3, 2, PlayerType.WHITE);
    Assert.assertTrue(mock.getLog().toString().contains("Valid move"));
  }

  @Test
  public void testIsValidCoordinate() {
    SquareBoard board1 = new SquareBoard(8);
    List<Move> validMoves = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, validMoves, sb);
    mock.isValidCoordinate(0, 0);
    Assert.assertTrue(mock.getLog().toString().contains("Valid coordinate."));
  }

  @Test
  public void getValidMovesWithCaptures() {
    SquareBoard board1 = new SquareBoard(8);
    Player player1 = new Player("Player1", PlayerType.WHITE, board1);
    Player player2 = new Player("Player2", PlayerType.BLACK, board1);
    Assert.assertEquals(2, board1.countPieces(PlayerType.WHITE));
    Assert.assertEquals(2, board1.countPieces(PlayerType.BLACK));
    CaptureStrategy ct = new CaptureStrategy();
    SquareBoard board2 = new SquareBoard(8);
    List<Move> validMoves = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, validMoves, sb);
    AIPlayer player4 = new AIPlayer("Player1", PlayerType.WHITE, board2, ct);
    Player player3 = new Player("Player2", PlayerType.BLACK, board2);
    player3.makeMove();
    player4.makeMove();
    mock.getValidMovesWithCaptures(player1);
    System.out.println(sb);
    Assert.assertTrue(mock.getLog().toString().contains("Checking valid"));
  }

  @Test
  public void testIsCorner() {
    SquareBoard board1 = new SquareBoard(8);
    List<Move> validMoves = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    Mock mock = new Mock(board1, validMoves, sb);
    Move move = new Move(0, 0);
    mock.isCornerMove(move, 8);
    Assert.assertTrue(mock.getLog().toString().contains("is a corner move"));
  }


}
