package com.ldts2223.chess.controller;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.controller.menu.MenuController;
import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.menu.GameOverMenu;
import com.ldts2223.chess.model.menu.MainMenu;
import com.ldts2223.chess.model.menu.Menu;
import com.ldts2223.chess.states.MatchState;
import com.ldts2223.chess.states.MenuState;
import com.ldts2223.chess.states.SettingsState;
import com.ldts2223.chess.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MenuControllerTest {

    private Menu mainMenu;
    private Menu gameOver;
    private MenuController mainMenuController;
    private MenuController gameOverController;
    private Input input;
    private Game game;
    private long time;

    @BeforeEach
    public void setUp(){
        mainMenu = Mockito.spy(new MainMenu());
        mainMenuController = new MenuController(mainMenu);
        gameOver = Mockito.spy(new GameOverMenu(""));
        gameOverController = new MenuController(gameOver);
        game = Mockito.mock(Game.class);
        time = 0;
    }

    @Test
    public void quitEsc(){
        input = new Input(new Position(2,2), Input.ACTION.quit);

        mainMenuController.step(game, input, time);

        Mockito.verify(game).setState(null);
    }

    @Test
    public void invalidInput(){
        input = new Input(new Position(0,7), Input.ACTION.mousebutton1);
        mainMenuController.step(game, input, time);
        input = new Input(new Position(7,0), Input.ACTION.mousebutton1);
        mainMenuController.step(game, input, time);

        Mockito.verify(mainMenu, Mockito.times(0)).getEntry(Mockito.anyInt());
    }

    @Test
    public void noInput(){
        input = new Input(new Position(7,7), Input.ACTION.none);

        mainMenuController.step(game, input, time);

        Mockito.verify(mainMenu).setSelectedEntry(1);
        Mockito.verify(mainMenu).getEntry(1);
        Mockito.verify(game, Mockito.times(0)).setState(Mockito.any(State.class));
    }

    @Test
    public void start(){
        input = new Input(new Position(7,6), Input.ACTION.mousebutton1);

        mainMenuController.step(game, input, time);

        Mockito.verify(mainMenu).setSelectedEntry(0);
        Mockito.verify(mainMenu).getEntry(0);
        Mockito.verify(game).setState(Mockito.any(MatchState.class));
    }

    @Test
    public void settings(){
        input = new Input(new Position(7,7), Input.ACTION.mousebutton1);

        mainMenuController.step(game, input, time);

        Mockito.verify(mainMenu).setSelectedEntry(1);
        Mockito.verify(mainMenu).getEntry(1);
        Mockito.verify(game).setState(Mockito.any(SettingsState.class));
    }

    @Test
    public void quit(){
        input = new Input(new Position(7,8), Input.ACTION.mousebutton1);

        mainMenuController.step(game, input, time);

        Mockito.verify(mainMenu).setSelectedEntry(2);
        Mockito.verify(mainMenu).getEntry(2);
        Mockito.verify(game).setState(null);
    }

    @Test
    public void playAgain(){
        input = new Input(new Position(7,6), Input.ACTION.mousebutton1);

        gameOverController.step(game, input, time);

        Mockito.verify(gameOver).setSelectedEntry(0);
        Mockito.verify(gameOver).getEntry(0);
        Mockito.verify(game).setState(Mockito.any(MatchState.class));
    }

    @Test
    public void backToMenu(){
        input = new Input(new Position(7,7), Input.ACTION.mousebutton1);

        gameOverController.step(game, input, time);

        Mockito.verify(gameOver).setSelectedEntry(1);
        Mockito.verify(gameOver).getEntry(1);
        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }
}
