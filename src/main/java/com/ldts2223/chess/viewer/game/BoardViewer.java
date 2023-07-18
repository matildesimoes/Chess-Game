package com.ldts2223.chess.viewer.game;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.game.match.Board;
import com.ldts2223.chess.viewer.Viewer;

public class BoardViewer extends Viewer<Board> {

    public BoardViewer(Board board) {
        super(board);
    }

    @Override
    public void drawElements(GUI gui) {
        gui.drawBoard(getModel().getWidth(), getModel().getHeight());
    }
}
