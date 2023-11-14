import model.Board;
import model.ReadOnlyBoardModel;
import view.DrawUtils;

import javax.swing.*;

public class Reversi {


    static ReadOnlyBoardModel board = new Board(11);
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hexagon Drawing");
       // JLabel score = new JLabel("Black: "+board.getScoreBlack() +"White: "+board.getScoreWhite());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DrawUtils(board));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
