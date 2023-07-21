package com.ldts2223.chess.viewer.match;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.viewer.match.MatchViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MatchViewerTest {

    private GUI gui;
    private MatchViewer matchViewer;
    private Match match;

    @BeforeEach
    void setUp() {
        match = new MatchBuilder().buildMatch(0);
        gui = Mockito.mock(GUI.class);
        matchViewer = new MatchViewer(match);
    }

    @Test
    void drawElements() {
        matchViewer.drawElements(gui);

        Mockito.verify(gui).drawBoard(8,2,1);
        Mockito.verify(gui).drawBackground("#363636");
        Mockito.verify(gui, Mockito.times(32)).drawPiece(
                Mockito.any(Position.class), Mockito.any(), Mockito.anyBoolean(), Mockito.anyBoolean());
        Mockito.verify(gui, Mockito.times(4)).drawText(
                Mockito.any(Position.class), Mockito.any(), Mockito.eq("#FFFFFF"), Mockito.eq("#363636"));
    }
}
