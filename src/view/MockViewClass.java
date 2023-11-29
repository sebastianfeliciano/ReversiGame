package view;

import javax.swing.*;

import model.ReadOnlyBoardModel;

public class MockViewClass extends DrawUtils implements DrawInterfaceMocker {
  private final StringBuilder log;

  public MockViewClass(ReadOnlyBoardModel board) {
    super(board);
    this.log = new StringBuilder();
  }


  @Override
  public void handleGameOver() {
    this.log.append("Handled Game Over");
    super.handleGameOver();
  }

  @Override
  public void showInvalidMoveMessage() {
    this.log.append("Invalid Move Pop Up");
    super.showInvalidMoveMessage();

  }

  @Override
  public void showThatIPassedTurnMessage() {
    this.log.append("I passed turn Pop Up.");
    super.showThatIPassedTurnMessage();
  }

  @Override
  public void itIsNowYourTurnMessage() {
    this.log.append("It is now my turn pop up.");
    super.itIsNowYourTurnMessage();

  }

  @Override
  public boolean getGameOverHandleState() {
    this.log.append("Getting Game Over Handle State.");
    return false;
  }

  @Override
  public void resetGameOverHandled() {
    this.log.append("Resetting Game Over Handling.");
    super.resetGameOverHandled();
  }

  @Override
  public void itIsNotYourTurnMessage() {
    this.log.append("It is not your turn pop up.");
    super.itIsNotYourTurnMessage();
  }

  @Override
  public void updateScore(int blackScore, int whiteScore) {
    this.log.append("Updated Score.");
    super.updateScore(blackScore, whiteScore);
  }

  @Override
  public void setScoreLabel(JLabel scoreLabel) {
    this.log.append("Setting Score.");
    super.setScoreLabel(scoreLabel);
  }

  public String getLog() {
    return this.log.toString();
  }
}
