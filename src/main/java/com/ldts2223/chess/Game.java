package com.ldts2223.chess;

import com.ldts2223.chess.gui.LanternaGUI;
import com.ldts2223.chess.model.menu.MainMenu;
import com.ldts2223.chess.states.MenuState;
import com.ldts2223.chess.states.State;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game {

    private final LanternaGUI gui;
    private State state;

    public Game() throws IOException, URISyntaxException, FontFormatException {
        this.gui = new LanternaGUI();
        this.state = new MenuState(new MainMenu());
    }

    public static void main(String[] args) throws IOException, URISyntaxException, FontFormatException, InterruptedException {
        new Game().start();
    }

    public void setState(State state){
        this.state = state;
    }

    private void start() throws IOException, InterruptedException {
        int FPS = 30;
        int frameTime = 1000 / FPS;

        while (this.state != null) {
            long startTime = System.currentTimeMillis();

            state.step(this, gui, startTime);

            long elapsedTime = System.currentTimeMillis() - startTime;
            long sleepTime = frameTime - elapsedTime;

            try {
                if (sleepTime > 0) Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
            }
        }
        gui.close();
    }
}