package view;

import view.HintSystem;
import controller.players.PlayerType;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class HintKeyToggle extends KeyAdapter {
  private final HintSystem hintSystem;
  private final PlayerType playerType;
  private final ReversiView componentToRepaint;
  private boolean showHints = true;

  public HintKeyToggle(HintSystem hintSystem, PlayerType playerType, ReversiView componentToRepaint) {
    this.hintSystem = hintSystem;
    this.playerType = playerType;
    this.componentToRepaint = componentToRepaint;
    componentToRepaint.requestFocusInWindow();
  }

  public void keyPressed(KeyEvent e) {
     if (e.getKeyCode() == KeyEvent.VK_H) {
      System.out.println("Pressed");
      if (this.playerType == PlayerType.BLACK) {
        hintSystem.toggleHintsForBlack();
      } else if (this.playerType == PlayerType.WHITE) {
        hintSystem.toggleHintsForWhite();
      }
      componentToRepaint.update();
    }
  }


}
