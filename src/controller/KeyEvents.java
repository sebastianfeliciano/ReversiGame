package controller;

import model.HexShape;

public interface KeyEvents {
    public void onHexPressed(HexShape hex);

    public void getHexPressed(HexShape hex);

    public void flipPieces(HexShape hex);

}
