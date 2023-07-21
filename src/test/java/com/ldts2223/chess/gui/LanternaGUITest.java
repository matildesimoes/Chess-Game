package com.ldts2223.chess.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.ldts2223.chess.model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class LanternaGUITest {

    private Screen screen;
    private LanternaGUI gui;
    private TextGraphics textGraphics;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        gui = new LanternaGUI(screen);
        textGraphics = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(textGraphics);
    }

    @Test
    void drawBackground() {
        gui.drawBackground("#363636");

        Mockito.verify(textGraphics,
                Mockito.times(1)).setBackgroundColor(TextColor.Factory.fromString("#363636"));
        Mockito.verify(textGraphics, Mockito.times(1)).fillRectangle(
                TerminalPosition.TOP_LEFT_CORNER,
                new TerminalSize(25, 15),
                ' ');
    }

    @Test
    void drawPieceWhiteNotSelected() {
        gui.drawPiece(new Position(2,3), '_', true, false);

        Mockito.verify(textGraphics,
                Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(textGraphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#A0522D"));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(3,4, "_");
    }

    @Test
    void drawPieceBlackSelected() {
        gui.drawPiece(new Position(1,1), '^', false, true);

        Mockito.verify(textGraphics,
                Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#16161B"));
        Mockito.verify(textGraphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#4169E1"));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(2,2, "^");
    }

    @Test
    void drawPlay() {
        gui.drawPlay(new Position(7,5), "#FF0000", "#FFFFFF", '|');

        Mockito.verify(textGraphics,
                Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(textGraphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#FF0000"));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(8,6    , "|");
    }
    @Test
    void drawText() {
        gui.drawText(new Position(5,5), "Hello", "#FFFFFF", "#16161B");

        Mockito.verify(textGraphics,
                Mockito.times(1)).setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        Mockito.verify(textGraphics, Mockito.times(1)).
                setBackgroundColor(TextColor.Factory.fromString("#16161B"));
        Mockito.verify(textGraphics, Mockito.times(1)).putString(5,5    , "Hello");
    }

    @Test
    void drawBoard() {
        gui.drawBoard(8,2,1);

        Mockito.verify(textGraphics,
              Mockito.times(32)).setBackgroundColor(TextColor.Factory.fromString("#A0522D"));
        Mockito.verify(textGraphics,
                Mockito.times(32)).setBackgroundColor(TextColor.Factory.fromString("#DEB887"));
        Mockito.verify(textGraphics, Mockito.times(64)).fillRectangle(
                Mockito.any(TerminalPosition.class),
                Mockito.any(TerminalSize.class),
                Mockito.anyChar());
    }
}

