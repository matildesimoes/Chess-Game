package com.ldts2223.chess.viewer.game;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.player.pieces.Piece;
import com.ldts2223.chess.viewer.Viewer;

import java.io.IOException;

public class PlayerViewer extends Viewer<Player> {

    public PlayerViewer(Player player) {
        super(player);
    }

    @Override
    public void drawElements(GUI gui) throws IOException {

        for (Piece piece: getModel().getPieces()) {
            drawPiece(gui, new PieceViewer(piece));
        }
    }

    private void drawPiece(GUI gui, PieceViewer viewer) throws IOException {
        viewer.draw(gui);
    }

}
