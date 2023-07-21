package com.ldts2223.chess.controller;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.controller.game.PlayerController;
import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.same;

public class PlayerControllerTest {

    private Match match;
    private PlayerController playerController;
    private Player player;
    private Input input;
    private Game game;
    private long time;

    @BeforeEach
    public void setUp(){
        match = Mockito.spy(new MatchBuilder().buildMatch(0));
        playerController = new PlayerController(match);
        player = Mockito.mock(Player.class);
        Mockito.doReturn(player).when(match).getActivePlayer();
        input = new Input(new Position(2,2), Input.ACTION.none);
        game = Mockito.mock(Game.class);
        time = 0;
    }
    @Test
    public void step() {
        playerController.step(game, input, time);

        Mockito.verify(match).getActivePlayer();
        Mockito.verify(player).play(eq(time), same(match), same(input.getAction()), eq(new Position(1,1)));
    }
}

