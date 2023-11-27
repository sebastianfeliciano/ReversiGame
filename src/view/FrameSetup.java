package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.DrawUtils;

public class FrameSetup {

  public static void setupFrame(JFrame frame, DrawUtils view, String playerTypeLabel) {
    JLabel scoreLabel = new JLabel();
    JLabel playerType = new JLabel(playerTypeLabel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(view, BorderLayout.CENTER);
    frame.add(scoreLabel, BorderLayout.NORTH);
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(playerType);
    frame.add(bottomPanel, BorderLayout.SOUTH);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

