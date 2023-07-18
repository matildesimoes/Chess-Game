package com.ldts2223.chess.viewer.game;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.player.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class PieceViewerTest {

    private Piece pieceStub;
    private PieceViewer pieceViewer;
    private GUI gui;

    @BeforeEach
    void helper() {

        pieceStub = Mockito.mock(Piece.class);
        Mockito.when(pieceStub.getPosition()).thenReturn(new Position(2,3));
        Mockito.when(pieceStub.getCharacter()).thenReturn('P');

        pieceViewer = new PieceViewer(pieceStub);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() throws IOException {
        pieceViewer.draw(gui);

        Mockito.verify(
                gui,
                Mockito.times(1)).drawPiece(new Position(2,3), 'P');
    }
}
