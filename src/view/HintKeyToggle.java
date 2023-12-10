package view;


import controller.players.PlayerType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * The HintKeyToggle class extends KeyAdapter to provide keyboard interaction
 * functionality for toggling hints in a Reversi game view.
 */
public class HintKeyToggle extends KeyAdapter {
  private final HintSystem hintSystem;
  private final PlayerType playerType;
  private final ReversiView componentToRepaint;
  private boolean showHints = true;


  /**
   * Constructs a new HintKeyToggle.
   *
   * @param hintSystem The HintSystem instance to control hints.
   * @param playerType The type of the player (BLACK or WHITE) for whom hints are to be toggled.
   */
  public HintKeyToggle(HintSystem hintSystem, PlayerType playerType,
                       ReversiView componentToRepaint) {
    this.hintSystem = hintSystem;
    this.playerType = playerType;
    this.componentToRepaint = componentToRepaint;
    componentToRepaint.requestFocusInWindow();
  }

  /**
   * Invoked when a key has been pressed. Specifically, it checks for
   * the 'H' key press to toggle hints.
   *
   * @param e The KeyEvent triggered by a key press.
   */
  public void keyPressed(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_H) {
      if (this.playerType == PlayerType.BLACK) {
        hintSystem.toggleHintsForBlack();
      } else if (this.playerType == PlayerType.WHITE) {
        hintSystem.toggleHintsForWhite();
      }
      componentToRepaint.update();
    }
  }


}
