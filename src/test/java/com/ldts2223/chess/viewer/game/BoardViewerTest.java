package com.ldts2223.chess.viewer.game;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.game.match.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class BoardViewerTest {

    private Board board;

    private GUI gui;

    private BoardViewer boardViewer;

    @BeforeEach
    void helper() {
        board = new Board(10,9);

        boardViewer = new BoardViewer(board);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void drawElement() throws IOException {
        boardViewer.draw(gui);

        Mockito.verify(
                gui,
                Mockito.times(1)).drawBoard(10,9);
    }
}
