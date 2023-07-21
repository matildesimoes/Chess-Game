package com.ldts2223.chess.viewer.match;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.plays.Play;

public class PlayViewer {

    protected void draw(Play play, GUI gui) {
        String pieceColor = play.getSelectedPiece().isWhite() ? "#000000" : "#FFFFFF";
        Position position = play.getPosition();
        String color = play.getColor();
        Character image = play.getImage();

        gui.drawPlay(position, color, pieceColor, image);
    }
}
