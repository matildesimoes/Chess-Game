package com.ldts2223.chess.gui;

import com.ldts2223.chess.model.Position;

import java.io.IOException;

public interface GUI {

    void drawPiece(Position position, Character character);

    void drawBoard(int width, int height);
    void drawText(Position position, String text, String color);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

}
