package com.ldts2223.chess.viewer.game;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.game.match.player.pieces.Piece;
import com.ldts2223.chess.viewer.Viewer;

import java.io.IOException;

public class PieceViewer extends Viewer<Piece> {

    public PieceViewer(Piece piece){
        super(piece);
    }

    @Override
    protected void drawElements(GUI gui) throws IOException {
        gui.drawPiece(getModel().getPosition(), getModel().getCharacter());
    }
}
