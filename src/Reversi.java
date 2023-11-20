import java.awt.*;

import controller.AIPlayer;
import controller.Player;
import controller.PlayerType;
import model.Board;
import model.ReadOnlyBoardModel;
import model.strategies.CaptureStrategy;
import org.testng.mustache.Model;
import view.DrawUtils;

import javax.swing.*;

public class Reversi {


    static ReadOnlyBoardModel board = new Board(7);
    public static void main(String[] args) {

        Board model = new Board();
        DrawUtils view1 = new DrawUtils(model);
        DrawUtils view2 = new DrawUtils(model);
        Player player1 = new Player("", PlayerType.WHITE, model);
        Player player2 = new AIPlayer("", PlayerType.BLACK, model, new CaptureStrategy());

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
