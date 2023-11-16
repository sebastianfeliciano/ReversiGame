package controller;

import java.io.IOException;

/**
 * A Player interface for all text-based views, to be used in the Reversi game.
 */
public interface IPlayer {

    /**
     * Renders a model in a manner in which the Player can play a character
     *
     * @throws IOException if the rendering fails for some reason
     */
    void placeKey(int x, int y) throws IOException;
}
