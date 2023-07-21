package com.ldts2223.chess.viewer.match;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import com.ldts2223.chess.viewer.match.PlayViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayViewerTest {

    private Play playMove;
    private Play playCapture;
    private GUI gui;
    private PlayViewer playViewer;
    private Piece pieceKing;
    private Piece piecePawn;

    @BeforeEach
    void setUp() {
        pieceKing = new King(new Position(2,1), true);
        piecePawn = new Pawn(new Position(2,3), false);
        playMove = new Move(new Position(2,2), pieceKing);
        playCapture = new Capture(new Position(2,3), pieceKing, piecePawn);
        gui = Mockito.mock(GUI.class);
        playViewer = new PlayViewer();
    }

    @Test
    void drawMove() {
        playViewer.draw(playMove, gui);

        Mockito.verify(gui, Mockito.times(1)).drawPlay(
                new Position(2,2), "#32CD32", "#000000",' ');
    }

    @Test
    void drawCapture() {
        playViewer.draw(playCapture, gui);

        Mockito.verify(gui, Mockito.times(1)).drawPlay(
                new Position(2,3), "#FF0000", "#000000",'`');
    }
}
