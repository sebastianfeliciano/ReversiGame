package view;

import controller.ReversiController;
import model.Board;

public class MockGame extends Game implements GameMocked {
  private final StringBuilder log;

  /**
   * Constructor that shows the entire game played, for updating purposes.
   */
  public MockGame(ReversiController controller1, ReversiController controller2, Board board) {
    super(controller1, controller2, board);
    this.log = new StringBuilder();
  }

  @Override
  public void start() {
    this.log.append("Game has Started");
    super.start();
  }

  public String getLog() {
    return this.log.toString();
  }
}
