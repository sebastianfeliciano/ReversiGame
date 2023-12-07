package adapters;

import model.HexShape;
import model.ReadOnlyBoardModel;
import provider.view.JFrameView;
import provider.controller.IViewFeatures;
import view.ReversiView;

import java.awt.BorderLayout;

/**
 * Adapter class that extends JFrameView and integrates DrawUtils for drawing the Reversi board.
 * This class acts as a bridge between the view represented by JFrame and the Reversi game logic,
 * allowing for graphical representation and interaction with the game.
 */
public class JFrameViewAdapter extends JFrameView {

  private final ReversiView drawUtils;

  /**
   * Constructs a JFrameViewAdapter object with the given model and DrawUtils.
   * This constructor initializes the view with the model for the game data and
   * the utilities needed for drawing the game board.
   *
   * @param model     The read-only model to be used for the game.
   * @param drawUtils The DrawUtils instance for rendering the game board.
   */
  public JFrameViewAdapter(ReadOnlyBoardModel model, ReversiView drawUtils) {
    super(new IROModelAdapter(model));

    if (drawUtils == null) {
      throw new IllegalArgumentException("DrawUtils cannot be null");
    }
    this.drawUtils = new BoardPanelAdapter(model, this);
    HexShape firstClickedHex = null;
    setLayout(new BorderLayout());
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  @Override
  public void addFeatures(IViewFeatures features) {
    super.addFeatures(features);
  }

  @Override
  public void updateView() {
    super.updateView();
    drawUtils.update();
  }

  @Override
  public void displayErrorMessage(String message) {
    super.displayErrorMessage(message);
  }
}
