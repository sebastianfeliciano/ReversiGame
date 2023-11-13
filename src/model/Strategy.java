package model;

import controller.DirectionsEnum;
import controller.Player;
import controller.PlayerType;

import java.awt.*;

public interface Strategy {

    DirectionsEnum chooseMove(PlayerType playerType);
}
