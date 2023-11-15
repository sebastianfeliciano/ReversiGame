package model;

import java.util.List;

public class MockBoard extends Board implements ReadOnlyBoardModel{
    List<Move> validMoves;

    public MockBoard(List<Move> validMoves) {
      this.validMoves = validMoves;
    }
}
