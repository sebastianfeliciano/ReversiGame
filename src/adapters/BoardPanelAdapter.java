package adapters;

import model.Board;
import provider.model.IROModel;
import provider.view.BoardPanel;
import view.DrawUtils;
import view.ReversiView;
import view.DrawInterfaceMocker;
import model.ReadOnlyBoardModel;

import javax.swing.JFrame;

import java.awt.Graphics;

/**
 * Adapter class that extends DrawUtils and implements ReversiView and DrawInterfaceMocker.
 * This class adapts a BoardPanel to work with a ReadOnlyBoardModel, providing methods to update
 * and draw the board in a graphical user interface.
 */
public class BoardPanelAdapter extends DrawUtils implements ReversiView, DrawInterfaceMocker {
  private final BoardPanel boardPanel;

  /**
   * Constructs a BoardPanelAdapter with a specified ReadOnlyBoardModel and JFrame.
   *
   * @param model The ReadOnlyBoardModel to be adapted.
   * @param frame The JFrame in which the board panel is displayed.
   */
  public BoardPanelAdapter(ReadOnlyBoardModel model, JFrame frame) {
    super(model);
    IROModel iroModel = adaptToIROModel(model);
    boardPanel = new BoardPanel(iroModel, frame);
  }

  /**
   * Converts a ReadOnlyBoardModel to an IROModel.
   *
   * @param model The ReadOnlyBoardModel to be converted.
   * @return An IROModel adapted from the given ReadOnlyBoardModel.
   */
  private IROModel adaptToIROModel(ReadOnlyBoardModel model) {
    return new IROModelAdapter(model);
  }

  /**
   * Updates the state of the board panel.
   */
  @Override
  public void update() {
    super.update();
    // Fetch the state from ReadOnlyBoardModel
    // Update BoardPanel's state
    boardPanel.updateBoard(); // This method should internally update the BoardPanel's view
  }

  /**
   * Draws the board on the given Graphics context.
   *
   * @param g     The Graphics context to draw on.
   * @param board The Board to be drawn.
   */
  @Override
  public void drawBoard(Graphics g, Board board) {
    // Delegate drawing to BoardPanel
    boardPanel.repaint();
  }

  /**
   * Paints the component with the specified Graphics context.
   *
   * @param g The Graphics context used for painting.
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
  }

  /**
   * Gets the BoardPanel associated with this adapter.
   *
   * @return The BoardPanel instance.
   */
  public BoardPanel getBoardPanel() {
    return boardPanel;
  }
}
