import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controller.ReversiController;
import model.Board;

public class Game {
  private Timer timer;
  private final ReversiController controller1, controller2;
  private final Board board;

  public Game(ReversiController controller1, ReversiController controller2, Board board) {
    this.controller1 = controller1;
    this.controller2 = controller2;
    this.board = board;
  }

  public void start() {
    int delay = 1000;
    ActionListener taskPerformer = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (!board.isGameOver()) {
          controller1.update();
          controller2.update();
        } else {
          stop();
        }
      }
    };
    timer = new Timer(delay, taskPerformer);
    timer.start();
  }

  public void stop() {
    if (timer != null) {
      timer.stop();
    }
  }
}
