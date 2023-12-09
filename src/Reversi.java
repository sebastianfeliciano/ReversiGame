import javax.swing.JFrame;
import javax.swing.JLabel;

import controller.Command;
import controller.players.IPlayer;
import controller.ReversiController;
import model.BoardModel;
import adapters.IModelFeaturesAdapter;
import adapters.IViewFeaturesAdapter;
import adapters.IBoardAdapter;
import adapters.IROModelAdapter;
import adapters.IPlayerAdapter;
import adapters.IGraphicalReversiViewAdapter;
import adapters.IViewAdapter;
import adapters.JFrameViewAdapter;
import adapters.ReversiViewAdapter;
import view.DrawUtils;
import view.FrameSetup;
import view.Game;
import view.ReversiView;

/**
 * Represents the GUI view.
 */
public class Reversi {

  private JLabel scoreLabel1; // Score label for first frame
  private JLabel scoreLabel2; // Score label for second frame

  /**
   * Entry point for GUI.
   */
  public static void main(String[] args) {

    Command commandLine = new Command(args);
    BoardModel board = commandLine.getBoard();
    IBoardAdapter boardAdapter = new IBoardAdapter(board);

    ReversiView view2 = new DrawUtils(board);
    String score = "Black: " + board.getScoreBlack() + " White: " + board.getScoreWhite();
    JLabel label = new JLabel(score);
    view2.setScoreLabel(label);
    IViewAdapter viewAdapter = new IViewAdapter(view2);
    IPlayer player2 = commandLine.getPlayer2();
    IPlayerAdapter playerAdapter1 = new IPlayerAdapter(player2);
    JFrameViewAdapter viewAdapter1 = new JFrameViewAdapter(board, view2);
    IGraphicalReversiViewAdapter graphicalReversiView
            = new IGraphicalReversiViewAdapter(viewAdapter, label, viewAdapter1);
    IViewFeaturesAdapter featuresAdapter
            = new IViewFeaturesAdapter(playerAdapter1, board, graphicalReversiView);
    playerAdapter1.addFeatures(featuresAdapter);
    graphicalReversiView.addFeatures(featuresAdapter);
    featuresAdapter.setPlayer(playerAdapter1);
    IROModelAdapter readonlyBoardAdapted = new IROModelAdapter(board);
    ReversiViewAdapter viewAdapter2 = new ReversiViewAdapter(view2, board);
    viewAdapter1.addFeatures(featuresAdapter);
    IModelFeaturesAdapter controllerAdapter = new IModelFeaturesAdapter(player2, board, view2);
    readonlyBoardAdapted.addFeatures(controllerAdapter);
    viewAdapter1.add(label);
    viewAdapter2.addFeatures(featuresAdapter);
    viewAdapter1.setVisible(true);
    ReversiController controller2 = new ReversiController(player2, board, view2);
    player2.setMoveHandler(controller2);
    view2.setEventListener(controller2);
    viewAdapter1.setResizable(true);


    ReversiView view1 = new DrawUtils(board);
    IPlayer player1 = commandLine.getPlayer1();
    ReversiController controller1 = new ReversiController(player1, board, view1);
    player1.setMoveHandler(controller1);
    JFrame frame2 = new JFrame("Reversi - Player 1");
    JLabel frame2Setup = FrameSetup.setupFrame(frame2, view1,
            "You are Player " + player1.getColor(), score);
    view1.setEventListener(controller1);
    view1.setScoreLabel(frame2Setup);


    Game model = new Game(controller1, controllerAdapter, board);
    model.start();
  }

}
