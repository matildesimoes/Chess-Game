package com.ldts2223.chess.viewer.match;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.game.match.Board;
import com.ldts2223.chess.viewer.match.BoardViewer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

public class BoardViewerTest {

    private GUI gui;
    private BoardViewer boardViewer;
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(10);
        gui = Mockito.mock(GUI.class);
        boardViewer = new BoardViewer();
    }

    @Test
    void drawBoard() {
        boardViewer.draw(board, gui);

        Mockito.verify(gui, Mockito.times(1)).drawBoard(10,2,1);
    }
}
