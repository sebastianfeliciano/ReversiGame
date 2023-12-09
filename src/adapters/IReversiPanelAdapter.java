package adapters;

import java.awt.Dimension;

import provider.controller.IViewFeatures;
import provider.view.IReversiPanel;
import view.ReversiView;

/**
 * Adapter class that implements IReversiPanel to adapt a ReversiView for use in a specific context.
 * This class enables the use of a ReversiView within a different framework or interface by adapting
 * its methods to the IReversiPanel interface.
 */
public class IReversiPanelAdapter implements IReversiPanel {
  private final ReversiView viewer;

  /**
   * Constructs an IReversiPanelAdapter with a specified ReversiView.
   *
   * @param viewer The ReversiView to be adapted.
   */
  public IReversiPanelAdapter(ReversiView viewer) {
    this.viewer = viewer;
  }

  /**
   * Adds view features to this panel.
   *
   * @param feature The IViewFeatures to add to the panel.
   */
  @Override
  public void addFeatures(IViewFeatures feature) {
    // Feature is already assigned. Do not need to
    // re-assign.
  }

  /**
   * Updates the board based on the current state of the game.
   * Delegates the update action to the adapted ReversiView.
   */
  @Override
  public void updateBoard() {
    viewer.update();
  }

  /**
   * Gets the dimensions of the panel.
   *
   * @return The dimensions of the panel as a Dimension object.
   */
  @Override
  public Dimension getDimensions() {
    return new Dimension(viewer.getWindowWidth(), viewer.getWindowHeight());
  }
}
