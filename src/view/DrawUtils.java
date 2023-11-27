package view;

import controller.players.AIPlayer;
import controller.players.Player;
import controller.players.PlayerType;
import model.Board;
import model.HexShape;
import model.ReadOnlyBoardModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

/**
 * Draws out our board and hexagons.
 */
public class DrawUtils extends JPanel implements ReversiView, Observer {
  private Player currentPlayer;
  private ReadOnlyBoardModel board;
  boolean isClicked = false;
  private HexShape firstClickedHex;
  private HexShape hoveredHex;
  PlayerActionListener playerAction;
  private Player humanPlayer;
  private Player aiPlayer;


  public void setEventListener(PlayerActionListener playerAction) {
    this.playerAction = playerAction;
  }

  @Override
  public void update() {
    repaint();
  }

  public void updateBoard(ReadOnlyBoardModel newBoardModel) {
    if (newBoardModel instanceof Board) {
      board = newBoardModel;
    }
    repaint();
    //playerAction.updateScore();
  }

  /**
   * Constructs a board to be made in the game.
   */
  public DrawUtils(ReadOnlyBoardModel board) {
    this.board = board;

    JButton quitButton;
    JButton scoreButtonWhite;
    JPanel buttonPanel;
    setPreferredSize(new Dimension(650, 650));
    setBackground(new Color(this.getWindowWidth() / 11, 34, 83));

    addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        try {
          HexShape newClickedHex = findHex(e.getX(), e.getY());
          if (firstClickedHex != null && firstClickedHex.equals(newClickedHex)) {
            firstClickedHex = null;
            System.out.println("Deselected: " + newClickedHex.getColumn()
                    + ", " + newClickedHex.getRow());
          } else {
            firstClickedHex = newClickedHex;
            triggerAi(Integer.parseInt(firstClickedHex.getRow()), Integer.parseInt(firstClickedHex.getColumn()));
          }
          repaint();
        } catch (Exception ignored) {
        }
      }
    });
    addMouseMotionListener(new MouseAdapter() {
      public void mouseMoved(MouseEvent e) {
        try {
          HexShape currentHover = findHex(e.getX(), e.getY());
          if (hoveredHex != currentHover) {
            hoveredHex = currentHover;
            repaint();
          }
        } catch (Exception ignored) {
        }
      }
    });
    addKeyListener(new KeyAdapter() {
      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
          if (firstClickedHex != null) {
            playerAction.onPlayerMove(Integer.parseInt(firstClickedHex.getColumn()),
                    Integer.parseInt(firstClickedHex.getRow()));
            System.out.println("Chosen: "
                    + firstClickedHex.getColumn() + ", " + firstClickedHex.getRow());
          } else {
            System.out.println("No hex selected");
          }
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
          System.out.println("Pass");
        }
      }
    });
    setFocusable(true);
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton passTurnButton = new JButton("Pass Turn");
    passTurnButton.addActionListener((ActionEvent e) -> playerAction.onPass());
    bottomPanel.add(passTurnButton);

    quitButton = new JButton("Quit");
    quitButton.addActionListener((ActionEvent e) -> System.exit(0));
    bottomPanel.add(quitButton);

    scoreButtonWhite = new JButton("Score White: " + board.getScoreWhite());
    bottomPanel.add(scoreButtonWhite);

    setLayout(new BorderLayout());
    add(bottomPanel, BorderLayout.SOUTH);
  }

  /**
   * Finds a certain hexagon based on the mouse position.
   */
  @Override
  public HexShape findHex(int mouseX, int mouseY) {
    int hexSize = getHexSize();
    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = board.getMidPoint();
    double hexHeight = 3.0 / 2 * hexSize;
    int horizontalDistanceBetweenAdjacentHexagonCenters = (int) (Math.sqrt(3) * hexSize);
    int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);
    int startY = getHeight() / 2 - (sizeOfEntireBoard
            * horizontalDistanceBetweenAdjacentHexagonCenters / 2);

    for (int currentRow = 0; currentRow < sizeOfEntireBoard; currentRow++) {
      int currentHexesMade;
      if (currentRow <= midPoint) {
        currentHexesMade = midPoint + currentRow + 1;
      } else {
        currentHexesMade = sizeOfEntireBoard - (currentRow - midPoint);
      }

      int spacesBefore = (sizeOfEntireBoard - currentHexesMade);

      for (int h = 0; h < currentHexesMade; h++) {
        int offSet = (sizeOfEntireBoard - currentHexesMade)
                * horizontalDistanceBetweenAdjacentHexagonCenters / 2;
        int centerX = startX + offSet + h * horizontalDistanceBetweenAdjacentHexagonCenters;
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
    return null;
  }

  /**
   * Returns the hex size.
   */
  @Override
  public int getHexSize() {
    return (getWindowHeight() * getWindowWidth()) / 15000;
  }

  /**
   * Determines if the mouse hovers over a hex.
   */
  @Override
  public boolean isPointInHex(int x, int y, int centerX, int centerY, int hexSize) {
    double xDistance = Math.abs(x - centerX);
    double yDistance = Math.abs(y - centerY);

    if (xDistance > hexSize * Math.sqrt(3) / 2) {
      return false;
    }
    return (!(yDistance > ((double) (hexSize * 3) / 2) / 2));
  }

  /**
   * Gets the window width.
   */
  @Override
  public int getWindowWidth() {
    return Math.min(this.getWidth(), 650);
  }

  /**
   * Paints a board.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    drawBoard(g, (Board) board);

    if (isClicked) {
      isClicked = false;
    }
  }

  /**
   * Draws a single hexagon.
   */
  @Override
  public void drawEachHexagon(Graphics g, HexShape hex, int centerX, int centerY,
                              int hexSize, PlayerType playerType) {
    int sides = 6;
    Polygon hexagon = new Polygon();
    int radius = hexSize / 2;

    for (int i = 0; i < sides; i++) {
      double angle = 2 * Math.PI / sides * i + Math.PI / 6;
      int x = (int) (centerX + hexSize * Math.cos(angle));
      int y = (int) (centerY + hexSize * Math.sin(angle));
      hexagon.addPoint(x, y);
    }

    if (hex.equals(firstClickedHex)) {
      g.setColor(Color.CYAN);
    } else if (hex.equals(hoveredHex)) {
      g.setColor(Color.GREEN);
    } else {
      g.setColor(Color.LIGHT_GRAY);
    }

    g.fillPolygon(hexagon);
    g.setColor(Color.BLACK);
    g.drawPolygon(hexagon);

    if (hex.equals(firstClickedHex)) {
      g.setColor(Color.CYAN);
    } else {
      g.setColor(getColor(playerType));
    }
    g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
  }


  /**
   * Sets the player type for color purposes.
   */
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

  /**
   * Builds the board out.
   */
  @Override
  public void drawBoard(Graphics g, Board board) {
    int hexSize = getHexSize();
    int sizeOfEntireBoard = board.getBoardSize();
    int midPoint = board.getMidPoint();
    int horizontalDistanceBetweenAdjacentHexagonCenters = (int) (Math.sqrt(3) * hexSize);
    double hexHeight = 3.0 / 2 * hexSize;
    int startX = getWidth() / 2 - (midPoint * hexSize * 3 / 2);
    int startY = getHeight() / 2 - (sizeOfEntireBoard
            * horizontalDistanceBetweenAdjacentHexagonCenters / 2);

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
        int offSet = (sizeOfEntireBoard - currentHexesMade)
                * horizontalDistanceBetweenAdjacentHexagonCenters / 2;
        int centerX = startX + offSet + h * horizontalDistanceBetweenAdjacentHexagonCenters;
        int centerY = (int) Math.round(startY + currentRow * hexHeight);

        PlayerButton playerButton = new PlayerButton(currentHex);
        playerButton.setBounds(centerX - hexSize / 2,
                centerY - hexSize / 2, hexSize, hexSize);
        playerButton.setVisible(true);

        drawEachHexagon(g, currentHex, centerX, centerY, hexSize, currentHex.getPlayerType());
      }
    }
  }

  /**
   * Returns the window height.
   */
  @Override
  public int getWindowHeight() {
    return Math.min(this.getHeight(), 650);
  }

  public Player getHuman() {
    return humanPlayer;
  }

  public Player getAiPlayer() {
    return aiPlayer;
  }

  public void handleGameOver() {
    if (board.isGameOver()) {
      int black = board.getScoreBlack();
      int white = board.getScoreWhite();
      if (black > white) {
        JOptionPane.showMessageDialog(this, "Black won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
      }
      if (white > black) {
        JOptionPane.showMessageDialog(this, "White won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);

      }
    }
  }

  public void triggerAi(int row, int column) {
    playerAction.onPlayerMove(row, column);
  }
}
