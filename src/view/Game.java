package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import controller.ReversiController;
import controller.players.AIPlayer;
import model.Board;

/**
 * Representing the game playing so that each controller can update one by one,
 * this is important for two AIs.
 */
public class Game implements GameMocked {
  private Timer timer;
  private final ReversiController controller1;
  private final ReversiController controller2;
  private final Board board;

  /**
   * Constructor that shows the entire game played, for updating purposes.
   */
  public Game(ReversiController controller1, ReversiController controller2, Board board) {
    this.controller1 = controller1;
    this.controller2 = controller2;
    this.board = board;
  }

  /**
   * Starts the game updating mechanism.
   */
  public void start() {
//    int delay = 1000;
//    timer = new Timer(delay, new ActionListener() {
//      @Override
//      public void actionPerformed(ActionEvent evt) {
//        if (controller1.getPlayer() instanceof AIPlayer) {
//          controller1.update();
//        }
//        if(controller2.getPlayer() instanceof AIPlayer) {
//          controller2.update();
//        }
//        else {
//          stop();
//        }

        while (!board.isGameOver()) {
          controller1.update();
          controller2.update();
        }
          stop();
        }
//      }
//    });
//    timer.start();


  /**
   * Stops the timer for the game.
   */
  public void stop() {
    if (timer != null) {
      timer.stop();
    }
  }
}