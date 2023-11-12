package view;
import controller.PlayerType;
import model.Board;
import model.HexShape;
import model.ReadOnlyBoardModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.*;
import javax.swing.border.Border;

public class DrawUtils extends JPanel implements ReversiView {
  protected static Board board = new Board(11);
  boolean isClicked = false;
  private int clickedRow = board.BOARD_SIZE;
  private int storedClickedRow =  clickedRow;
  private int clickedColumn = board.BOARD_SIZE;
  private JButton quitButton;
  private JPanel buttonPanel;
  private JButton executeButton;
  private int storedClickedColumn = clickedColumn;

  public DrawUtils(ReadOnlyBoardModel board) {
    setPreferredSize(new Dimension(650, 650));
    setBackground(new Color(this.getWindowWidth() / 11, 34, 83));
    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        try {
          HexShape clickedHex = findHex(e.getX(), e.getY());
          storedClickedRow = clickedRow;
          storedClickedColumn = clickedColumn;

          if (clickedHex != null) {
            int newClickedRow = Integer.parseInt(clickedHex.getRow());
            int newClickedColumn = Integer.parseInt(clickedHex.getColumn());

            if (newClickedRow == storedClickedRow && newClickedColumn == storedClickedColumn) {
              isClicked = false;
            } else {
              isClicked = true;
              storedClickedRow = newClickedRow;
              storedClickedColumn = newClickedColumn;
            }
            clickedRow = newClickedRow;
            clickedColumn = newClickedColumn;
            repaint();
            System.out.println(Objects.requireNonNull(findHex(e.getX(), e.getY())).getColumn() + ", " + Objects.requireNonNull(findHex(e.getX(), e.getY())).getRow());
          }
          else {
            isClicked = false;
            clickedRow = board.getBoardSize();
            clickedColumn = board.getBoardSize();
          }
          repaint();
            } catch (Exception ignored) {
        }
      }
    });
    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    executeButton = new JButton("Execute");
    executeButton.addActionListener((ActionEvent e) ->
            System.out.println("Execute:"));
    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) ->
            System.exit(0));
    add(executeButton, BorderLayout.SOUTH);
    add(quitButton, BorderLayout.WEST);
  }

  @Override
  public HexShape findHex(int mouseX, int mouseY) {
    int hexSize = getHexSize();
    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = board.getMidPoint();
    double hexHeight = 3.0 / 2 * hexSize;
    int horizontalDistanceBetweenAdjacentHexagonCenters = (int) (Math.sqrt(3) * hexSize);
    int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);
    int startY = getHeight() / 2 - (sizeOfEntireBoard * horizontalDistanceBetweenAdjacentHexagonCenters / 2);

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int currentHexesMade;
      if (currentRow <= midPoint) {
        currentHexesMade = midPoint + currentRow + 1;
      } else {
        currentHexesMade = sizeOfEntireBoard - (currentRow - midPoint);
      }

      int spacesBefore = (sizeOfEntireBoard - currentHexesMade);

      for (int h = 0; h < currentHexesMade; h++) {
        int offSet = (sizeOfEntireBoard - currentHexesMade) * horizontalDistanceBetweenAdjacentHexagonCenters / 2;
        int centerX = startX + offSet + h * horizontalDistanceBetweenAdjacentHexagonCenters; //Moves the chain from top to bottom left
        int centerY = (int) Math.round(startY + currentRow * hexHeight);

        if (isPointInHex(mouseX, mouseY, centerX, centerY, hexSize)) {
          if (currentRow <= midPoint) {
            return board.getCurrentHex(currentRow, h + spacesBefore);
          } else {
            return board.getCurrentHex(currentRow, h);
          }
        }

      }

    }
    System.out.println("No Hexagon Found");
    return null;
  }

  @Override
  public int getHexSize() {
    return (getWindowHeight() * getWindowWidth()) / 15000;
  }

  @Override
  public boolean isPointInHex(int x, int y, int centerX, int centerY, int hexSize) {
    double xDistance = Math.abs(x - centerX);
    double yDistance = Math.abs(y - centerY);

    if (xDistance > hexSize * Math.sqrt(3) / 2) {
      return false;
    }
      return !(yDistance > ((double) (hexSize * 3) / 2) / 2);
  }

  @Override
  public int getWindowWidth() {
      return Math.min(this.getWidth(), 650);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawBoard(g, board);

    if (isClicked) {
      isClicked = false;
    }
  }

  @Override
  public void drawEachHexagon(Graphics g, HexShape hex, int centerX, int centerY, int hexSize, PlayerType playerType) {
    int sides = 6;
    Polygon hexagon = new Polygon();
    int radius = hexSize / 2;

    for (int i = 0; i < sides; i++) {
      double angle = 2 * Math.PI / sides * i + Math.PI / 6;
      int x = (int) (centerX + hexSize * Math.cos(angle));
      int y = (int) (centerY + hexSize * Math.sin(angle));
      hexagon.addPoint(x, y);
    }

    boolean isCurrentHexClicked = isClicked && Integer.parseInt(hex.getRow()) == clickedRow && Integer.parseInt(hex.getColumn()) == clickedColumn;

    if (isCurrentHexClicked){
      g.setColor(Color.CYAN);
    }
    else {
      g.setColor(Color.LIGHT_GRAY);
    }

    g.fillPolygon(hexagon);
    g.setColor(Color.BLACK);
    g.drawPolygon(hexagon);


    if (isCurrentHexClicked){
      g.setColor(Color.CYAN);
    }
    else {
      g.setColor(getColor(playerType));
    }
    g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
    }

    @Override
    public Color getColor(PlayerType playerType) {
    Color color;
    switch (playerType) {
      case BLACK:
        color = Color.BLACK;
        break;
      case WHITE:
        color = Color.WHITE;
        break;
      default:
        color = Color.LIGHT_GRAY;
        break;
    }
    return color;
  }

  @Override
  public void drawBoard(Graphics g, Board board) {
    int hexSize = getHexSize();
    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = board.getMidPoint();
    int horizontalDistanceBetweenAdjacentHexagonCenters = (int) (Math.sqrt(3) * hexSize);
    double hexHeight = 3.0 / 2 * hexSize;
    int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);
    int startY = getHeight() / 2 - (sizeOfEntireBoard * horizontalDistanceBetweenAdjacentHexagonCenters / 2);

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int currentHexesMade;

      if (currentRow <= midPoint) {
        currentHexesMade = midPoint + currentRow + 1;
      } else {
        currentHexesMade = sizeOfEntireBoard - (currentRow - midPoint);
      }

      int spacesBefore = (sizeOfEntireBoard - currentHexesMade);

      for (int h = 0; h < currentHexesMade; h++) {
        HexShape currentHex;
        if (currentRow <= midPoint) {
          currentHex = board.getCurrentHex(currentRow, spacesBefore + h);
        } else {
          currentHex = board.getCurrentHex(currentRow, h);
        }
        int offSet = (sizeOfEntireBoard - currentHexesMade) * horizontalDistanceBetweenAdjacentHexagonCenters / 2;
        int centerX = startX + offSet + h * horizontalDistanceBetweenAdjacentHexagonCenters; //Moves the chain from top to bottom left
        int centerY = (int) Math.round(startY + currentRow * hexHeight); //Moves chain from left to right

        PlayerButton playerButton = new PlayerButton(currentHex);
        playerButton.setBounds(centerX - hexSize / 2, centerY - hexSize / 2, hexSize, hexSize);
        playerButton.setVisible(true);

        drawEachHexagon(g, currentHex, centerX, centerY, hexSize, currentHex.getPlayerType());
      }
    }
  }

  @Override
  public int getWindowHeight() {
      return Math.min(this.getHeight(), 650);
  }
}
