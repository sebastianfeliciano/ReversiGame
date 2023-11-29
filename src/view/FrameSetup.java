package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.im.InputContext;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.DrawUtils;

public class FrameSetup {

  private JFrame frame;

  public static JLabel setupFrame(JFrame frame, DrawUtils view, String playerTypeLabel, String score) {
    JLabel playerType = new JLabel(playerTypeLabel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.add(view, BorderLayout.CENTER);
    JLabel scoreLabel = new JLabel(score);
    frame.add(scoreLabel, BorderLayout.NORTH);
    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.add(playerType);
    frame.add(bottomPanel, BorderLayout.SOUTH);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    return scoreLabel;
  }

  }


