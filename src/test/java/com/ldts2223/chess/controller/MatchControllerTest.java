package com.ldts2223.chess.controller;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.controller.game.MatchController;
import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.states.MenuState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MatchControllerTest {

    private Game game;
    private Input input;
    private Match match;

    @BeforeEach
    void helper(){
        game = Mockito.mock(Game.class);
        match = Mockito.spy(new MatchBuilder().buildMatch(0));
    }

    @Test
    public void quitTest() {
        input = new Input(new Position(3,4), Input.ACTION.quit);
        MatchController controller = new MatchController(match);

        controller.step(game, input, 600);

        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }

    @Test
    public void isWhiteCheckmate() {
        MatchController controller = Mockito.spy(new MatchController(match));
        input = new Input(new Position(3,4), Input.ACTION.none);

        Mockito.doReturn(true).when(match).isCheckMate();
        controller.step(game, input, 600);

        Mockito.verify(match).isCheckMate();
        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }

    @Test
    public void isBlackCheckmate() {
        MatchController controller = Mockito.spy(new MatchController(match));
        input = new Input(new Position(3,4), Input.ACTION.none);

        Mockito.doReturn(true).when(match).isCheckMate();
        match.switchTurn(0);
        controller.step(game, input, 600);

        Mockito.verify(match).isCheckMate();
        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }

    @Test
    public void isStaleMate() {
        MatchController controller = Mockito.spy(new MatchController(match));
        input = new Input(new Position(3,4), Input.ACTION.none);

        Mockito.doReturn(true).when(match).isStaleMate();
        controller.step(game, input, 600);

        Mockito.verify(match).isStaleMate();
        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }

    @Test
    public void isWhiteTimeOver() {
        MatchController controller = Mockito.spy(new MatchController(match));
        input = new Input(new Position(3,4), Input.ACTION.none);

        Mockito.doReturn(true).when(match).isTimeOver();
        controller.step(game, input, 600);

        Mockito.verify(match).isTimeOver();
        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }

    @Test
    public void isBlackTimeOver() {
        MatchController controller = Mockito.spy(new MatchController(match));
        input = new Input(new Position(3,4), Input.ACTION.none);

        Mockito.doReturn(true).when(match).isTimeOver();
        match.switchTurn(0);
        controller.step(game, input, 600);

        Mockito.verify(match).isTimeOver();
        Mockito.verify(game).setState(Mockito.any(MenuState.class));
    }
}

