package view;


import javax.swing.*;

import controller.PlayerType;

import java.awt.*;

import model.Board;
import model.HexShape;

/**
 * Represents a player's button (PlayerType).
 */
public class PlayerButton extends JButton {
    private PlayerType type;
    private Board b;

    /**
     * Constructors a player button on a hex.
     */
    public PlayerButton(HexShape hex) {
        hex.setButton(this);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setOpaque(false);
        this.type = PlayerType.EMPTY;
        Board b1 = this.b;
        setPreferredSize(new Dimension(100, 100));
    }

    /**
     * Paints a button for the player.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
