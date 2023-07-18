package com.ldts2223.chess.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.ldts2223.chess.model.Position;

import java.io.IOException;

public class LanternaGUI implements GUI{

    private final Screen screen;

    public LanternaGUI(int width, int height) throws IOException {
        Terminal terminal = createTerminal(width, height);
        this.screen = createScreen(terminal);
    }

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(new TerminalPosition(1,1));
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    private Terminal createTerminal(int width, int height) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory defaultTerminalFactory =
                new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        defaultTerminalFactory.setForceAWTOverSwing(true);
        Terminal terminal = defaultTerminalFactory.createTerminal();
        return terminal;
    }

    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString(color));
        textGraphics.putString(x, y + 1, "" + c);
    }

    @Override
    public void drawPiece(Position position, Character character) {
        drawCharacter(position.getX(), position.getY(), character, "#000000");
    }

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString(color));
        textGraphics.putString(position.getX(), position.getY(), text);
    }

    @Override
    public void drawBoard(int width, int height){
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString("#F5F5DC"));

        for(int y = 0; y < height; y++) {
            int start = y % 2 == 0 ? 0 : 1;
            for (int x = start; x < width; x += 2)
                textGraphics.fillRectangle(
                        new TerminalPosition(x, y),
                        new TerminalSize(1, 1),
                        '*');
        }
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
