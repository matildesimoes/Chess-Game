package com.ldts2223.chess.viewer.match;

import com.ldts2223.chess.gui.GUI;
import com.ldts2223.chess.model.game.match.Board;

public class BoardViewer {

    public void draw(Board board, GUI gui) {
        gui.drawBoard(board.getSize(), board.getBoardStart(), board.getSquareSize());
    }
}
