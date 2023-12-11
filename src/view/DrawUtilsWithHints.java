package view;

import model.BoardModel;
import model.ReadOnlyBoardModel;
import controller.players.PlayerType;
import model.Shape;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * As an assist to players,
 * this class enables a “hint” mode where the selected cell
 * shows how many discs would be flipped if that player chose that move.
 */
public class DrawUtilsWithHints extends DrawUtils implements ReversiView {
  private final HintSystem hintSystem;
  ReadOnlyBoardModel boardModel;
  PlayerType currentPlayer;

  /**
   * Constructs the Model, with the hints ability.
   */
  public DrawUtilsWithHints(ReadOnlyBoardModel boardModel,
                            HintSystem hintSystem, PlayerType currentPlayer) {
    super(boardModel);
    this.hintSystem = hintSystem;
    this.boardModel = boardModel;
    this.currentPlayer = currentPlayer;
    addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent e) {
        repaint();
      }
    });
    this.addKeyListener(new HintKeyToggle(hintSystem, currentPlayer, this));
    setFocusable(true);
    this.requestFocusInWindow();
  }

  @Override
  public void setEventListener(IGameControlled controller) {
    super.setEventListener(controller);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (this.boardModel == null) {
      System.out.println("Board is null");
    }
    BoardModel board = this.boardModel.getRegularBoard();

    boolean black = this.currentPlayer == PlayerType.BLACK;

    boolean showHints = false;
    if (black) {
      showHints = hintSystem.areHintsEnabledForBlack();
    }
    if (!black) {
      showHints = hintSystem.areHintsEnabledForWhite();
    }

    if (showHints) {
      setBackground(new Color(127, 23, 31));
    } else {
      setBackground(getbackground());
    }

    Shape selectedHex = getFirstClickedHex();
    if (selectedHex == null) {
      return;
    }
    if (showHints && selectedHex.getPlayerType() != PlayerType.EMPTY) {
      int centerX = calculateCenterX(selectedHex);
      int centerY = calculateCenterY(selectedHex);
      drawX(g, centerX, centerY);
    }

    if (showHints && selectedHex.getPlayerType() == PlayerType.EMPTY) {
      BoardModel board1 = boardModel.getRegularBoard();
      int flips = board1.calculateCaptures(Integer.parseInt(selectedHex.getColumn()),
              Integer.parseInt(selectedHex.getRow()), this.currentPlayer, board1);
      int centerX = calculateCenterX(selectedHex);
      int centerY = calculateCenterY(selectedHex);
      drawHint(g, centerX + 215, centerY + 122, flips);
    }
  }

  private void drawX(Graphics g, int centerX, int centerY) {
    g.setColor(Color.RED);
    int textSize = calculateTextSize() * 2;
    Font font = new Font("Arial", Font.BOLD, textSize);
    g.setFont(font);

    FontMetrics metrics = g.getFontMetrics(font);

    int textX = centerX - (metrics.stringWidth("X") / 2) + 215;
    int textY = centerY + (metrics.getAscent() / 2) + 125;

    g.drawString("X", textX, textY);
  }


  private void drawHint(Graphics g, int centerX, int centerY, int flips) {
    g.setColor(Color.BLACK);
    String text = Integer.toString(flips);
    int textSize = calculateTextSize() * 2;
    Font font = new Font("Impact", Font.PLAIN, textSize);
    g.setFont(font);
    FontMetrics metrics = g.getFontMetrics(g.getFont());

    int textX = centerX - (metrics.stringWidth(text) / 2);
    int textY = centerY + (metrics.getAscent() / 2);
    g.drawString(text, textX, textY);
  }

  private int calculateCenterX(Shape hex) {
    int hexSize = getHexSize();
    int midPoint = boardModel.getMidPoint();
    int sizeOfEntireBoard = boardModel.getBoardSize();
    int horizontalDistanceBetweenAdjacentHexagonCenters = (int) (Math.sqrt(3) * hexSize);
    int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);

    int currentRow = Integer.parseInt(hex.getRow());
    int currentHexesMade = currentRow <= midPoint ? midPoint
            + currentRow + 1 : sizeOfEntireBoard - (currentRow - midPoint);
    int spacesBefore = (sizeOfEntireBoard - currentHexesMade);
    int column = Integer.parseInt(hex.getColumn());

    int offSet = (sizeOfEntireBoard - currentHexesMade)
            * horizontalDistanceBetweenAdjacentHexagonCenters / 2;
    return startX + offSet + (column - spacesBefore)
            * horizontalDistanceBetweenAdjacentHexagonCenters;
  }

  private int calculateCenterY(Shape hex) {
    int hexSize = getHexSize();
    int sizeOfEntireBoard = boardModel.getBoardSize();
    int midPoint = boardModel.getMidPoint();
    double hexHeight = 3.0 / 2 * hexSize;
    int startY = getHeight() / 2 - (sizeOfEntireBoard * (int) (Math.sqrt(3) * hexSize) / 2);
    int currentRow = Integer.parseInt(hex.getRow());
    return (int) Math.round(startY + currentRow * hexHeight);
  }


  private int calculateTextSize() {
    double factor = 0.02;
    int baseSize = 12;
    int maxSize = 36;

    int width = getWidth();
    int height = getHeight();

    int size = (int) (Math.min(width, height) * factor);

    size = Math.max(baseSize, Math.min(size, maxSize));

    return size;
  }

}
