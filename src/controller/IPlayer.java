package controller;

import java.io.IOException;

/** A Player interface for all text-based views, to be used in the Reversi game.
 */
public interface IPlayer {

    /**
     * Renders a model in some manner (e.g. as text, or as graphics, etc.).
     * @throws IOException if the rendering fails for some reason
     */
    void move() throws IOException;

    //Should think about what the player should be specifically allowed to do
}
