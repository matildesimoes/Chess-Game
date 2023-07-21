package com.ldts2223.chess.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.ldts2223.chess.model.Position;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import static java.lang.Math.ceil;

public class LanternaGUI implements GUI{

    private final Screen screen;
    private Input input = new Input(new Position(0,0), Input.ACTION.none);
    private final int fontSize = 50;
    private final int screenHeight = 15;
    private final int screenWidth = 25;
    private int boardStart = 100 / fontSize;
    private int squareSize = 1;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI() throws IOException, URISyntaxException, FontFormatException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(screenWidth, screenHeight, fontConfig);
        this.screen = createScreen(terminal);
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();

        int trueWidth = ((AWTTerminalFrame)terminal).getComponent(0).getWidth();
        int trueHeight = ((AWTTerminalFrame)terminal).getComponent(0).getHeight();
        int characterWidth = (int)ceil(trueWidth * 1.0 / screenWidth);
        int characterHeight = (int)ceil(trueHeight * 1.0 / screenHeight) - 4;

        ((AWTTerminalFrame)terminal).getComponent(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    input.setPosition(new Position(e.getX() / characterWidth, e.getY() / characterHeight));
                    input.setAction(Input.ACTION.mousebutton1);
                }
            }
        });

        ((AWTTerminalFrame)terminal).getComponent(0).addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                input.setPosition(new Position(e.getX() / characterWidth, e.getY() / characterHeight));
            }
        });

        ((AWTTerminalFrame)terminal).getComponent(0).addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 27)
                    input.setAction(Input.ACTION.quit);
            }
        });

        return terminal;
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("fonts/SquareChess.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, fontSize);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    public void drawBackground(String color){
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(color));
        textGraphics.fillRectangle(
                TerminalPosition.TOP_LEFT_CORNER,
                new TerminalSize(screenWidth, screenHeight),
                ' ');
    }

    @Override
    public void drawPiece(Position position, Character character, boolean isWhite, boolean isSelected) {
        int squareX = boardStart + (position.getX() - 1) * squareSize;
        int squareY = boardStart + (position.getY() - 1) * squareSize;

        String foreColor = isWhite ? "#FFFFFF" : "#16161B";
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString(foreColor));

        if (isSelected)
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#4169E1"));
        else {
            setBackgroundBoardColor(position, textGraphics);
        }
        textGraphics.putString(squareX, squareY, "" + character);
    }

    private void setBackgroundBoardColor(Position position, TextGraphics textGraphics) {
        if ((position.getY() + position.getX()) % 2 == 1)
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#A0522D"));
        else
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#DEB887"));
    }

    @Override
    public void drawPlay(Position position, String color, String pieceColor, Character image){
        int squareX = boardStart + (position.getX() - 1) * squareSize;
        int squareY = boardStart + (position.getY() - 1) * squareSize;
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(color));
        textGraphics.setForegroundColor(TextColor.Factory.fromString(pieceColor));
        textGraphics.putString(squareX,squareY, ""+ image);
    }

    @Override
    public void drawTextCentered(int y, String text, String foreColor, String backColor){
        int length = text.length() % 2 == 1 ? text.length() - 1 : text.length();
        Position position = new Position(screenWidth / 2 - (int) ceil(length / 2.0), y);
        drawText(position, text, foreColor, backColor);
    }

    @Override
    public void drawText(Position position, String text, String foreColor, String backColor) {
        TextGraphics textGraphics = screen.newTextGraphics();
        textGraphics.setForegroundColor(TextColor.Factory.fromString(foreColor));
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(backColor));
        textGraphics.putString(position.getX(), position.getY(), text);
    }

    @Override
    public void drawBoard(int boardSize, int boardStart, int squareSize){
        this.boardStart = boardStart;
        this.squareSize = squareSize;

        TextGraphics textGraphics = screen.newTextGraphics();
        drawSquares(boardSize, textGraphics);
    }

    private void drawSquares(int boardSize, TextGraphics textGraphics) {
        for(int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                setSquareColor(textGraphics, x, y);
                drawSquare(textGraphics, x, y);
            }
        }
    }
    private void setSquareColor(TextGraphics textGraphics, int x, int y) {
        if (x % 2 != y % 2) {
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#A0522D"));
        }
        else{
            textGraphics.setBackgroundColor(TextColor.Factory.fromString("#DEB887"));
        }
    }

    private void drawSquare(TextGraphics textGraphics, int x, int y){
        textGraphics.fillRectangle(
                new TerminalPosition((boardStart + x * squareSize), boardStart + y * squareSize),
                new TerminalSize(squareSize, squareSize),
                ' ');
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

    @Override
    public Input getNextMouseAction(){
        Input inputCopy = new Input(input.getPosition(), input.getAction());
        input.setAction(Input.ACTION.none);
        return inputCopy;
    }
}
