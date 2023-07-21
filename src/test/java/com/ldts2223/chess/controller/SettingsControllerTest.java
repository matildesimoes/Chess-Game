package com.ldts2223.chess.controller;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.controller.settings.SettingsController;
import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Settings;
import com.ldts2223.chess.states.MenuState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SettingsControllerTest {

    private Settings settings;
    private Controller settingsController;
    private Input input;
    private Game game;
    private long time;

    @BeforeEach
    public void setUp(){
        settings = Mockito.spy(Settings.getInstance());
        settingsController = new SettingsController(settings);
        game = Mockito.mock(Game.class);
        time = 0;
    }

    @Test
    public void quitEsc() throws InterruptedException {
        input = new Input(new Position(2,2), Input.ACTION.quit);

        settingsController.step(game, input, time);

        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }

    @Test
    public void incrementGameMode() throws InterruptedException {
        input = new Input(new Position(22,6), Input.ACTION.mousebutton1);

        settingsController.step(game, input, time);

        Mockito.verify(settings).incrementGamemode();
    }

    @Test
    public void incrementWhitePLayer() throws InterruptedException {
        input = new Input(new Position(22,7), Input.ACTION.mousebutton1);

        settingsController.step(game, input, time);

        Mockito.verify(settings).incrementWhitePlayer();
    }

    @Test
    public void incrementBlackPLayer() throws InterruptedException {
        input = new Input(new Position(22,8), Input.ACTION.mousebutton1);

        settingsController.step(game, input, time);

        Mockito.verify(settings).incrementBlackPlayer();
    }

    @Test
    public void increaseTime() throws InterruptedException {
        input = new Input(new Position(22,9), Input.ACTION.mousebutton1);

        settingsController.step(game, input, time);

        Mockito.verify(settings).increaseTime();
    }

    @Test
    public void decrementGameMode() throws InterruptedException {
        input = new Input(new Position(9,6), Input.ACTION.mousebutton1);

        settingsController.step(game, input, time);

        Mockito.verify(settings).decrementGamemode();
    }

    @Test
    public void decrementWhitePLayer() throws InterruptedException {
        input = new Input(new Position(9,7), Input.ACTION.mousebutton1);

        settingsController.step(game, input, time);

        Mockito.verify(settings).decrementWhitePlayer();
    }

    @Test
    public void decrementBlackPLayer() throws InterruptedException {
        input = new Input(new Position(9,8), Input.ACTION.mousebutton1);

        settingsController.step(game, input, time);

        Mockito.verify(settings).decrementBlackPlayer();
    }

    @Test
    public void decreaseTime() throws InterruptedException {
        input = new Input(new Position(9,9), Input.ACTION.mousebutton1);

        settingsController.step(game, input, time);

        Mockito.verify(settings).decreaseTime();
    }

    @Test
    public void goBack() throws  InterruptedException {
        input = new Input(new Position(4,13), Input.ACTION.mousebutton1);

        settingsController.step(game, input, time);

        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }

    @AfterEach
    public void resetSettings(){
        Settings.getInstance().reset();
    }
}
