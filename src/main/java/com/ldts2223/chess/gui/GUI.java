package com.ldts2223.chess.gui;

import com.ldts2223.chess.model.Position;
import java.io.IOException;

public interface GUI {

    void drawBackground(String color);
    void drawPiece(Position position, Character character, boolean isWhite, boolean isSelected);
    void drawPlay(Position position, String color, String pieceColor, Character image);
    void drawBoard(int boardSize, int boardStart, int squareSize);
    void drawTextCentered(int y, String text, String foreColor, String backColor);
    void drawText(Position position, String text, String foreColor, String backColor);
    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
    Input getNextMouseAction() throws IOException;
}
