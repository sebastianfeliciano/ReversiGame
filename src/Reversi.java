

import model.Board;
import model.ReadOnlyBoardModel;
import view.DrawUtils;

import javax.swing.*;
import java.awt.*;


/**
 * Represents the GUI view.
 */
public class Reversi {
    static ReadOnlyBoardModel board = new Board(7);

    /**
     * Entry point for GUI.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hexagon Drawing");
        JLabel score = new JLabel("Black: " + board.getScoreBlack() + " White: " + board.getScoreWhite());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DrawUtils(board));
        frame.add(score, BorderLayout.NORTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
