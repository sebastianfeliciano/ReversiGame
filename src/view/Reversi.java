package view;

import model.Board;
import javax.swing.*;
public class Reversi {
    static Board board = new Board(11);
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hexagon Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DrawUtils(board));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
