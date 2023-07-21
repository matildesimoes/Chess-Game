package com.ldts2223.chess.model.game.match;

import com.ldts2223.chess.model.game.match.player.User;
import com.ldts2223.chess.model.game.match.player.bot.CaptureBot;
import com.ldts2223.chess.model.game.match.player.bot.RandomBot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class MatchBuilderTest {

    private MatchBuilder matchBuilder;

    @BeforeEach
    public void setUp(){
        matchBuilder = new MatchBuilder();
    }

    @Test
    public void buildTraditional(){
        matchBuilder = new MatchBuilder();

        Match match = matchBuilder.buildMatch(0);

        Assertions.assertEquals(16, match.getWhitePlayer().getPieces().size());
        Assertions.assertEquals(16, match.getBlackPlayer().getPieces().size());
    }

    @Test
    public void buildExplosive(){
        Settings.getInstance().incrementGamemode();
        matchBuilder = new MatchBuilder();
        Match match = matchBuilder.buildMatch(0);

        Assertions.assertEquals(30, match.getWhitePlayer().getPieces().size());
        Assertions.assertEquals(30, match.getBlackPlayer().getPieces().size());
    }

    @Test
    public void testPlayerTypes(){
        matchBuilder = new MatchBuilder();
        Match match = matchBuilder.buildMatch(0);

        Assertions.assertSame(User.class, match.getWhitePlayer().getClass());
        Assertions.assertSame(CaptureBot.class, match.getBlackPlayer().getClass());

        Settings.getInstance().incrementWhitePlayer();
        Settings.getInstance().incrementBlackPlayer();
        match = matchBuilder.buildMatch(0);

        Assertions.assertSame(RandomBot.class, match.getWhitePlayer().getClass());
        Assertions.assertSame(User.class, match.getBlackPlayer().getClass());

        Settings.getInstance().incrementWhitePlayer();
        Settings.getInstance().incrementBlackPlayer();
        match = matchBuilder.buildMatch(0);

        Assertions.assertSame(CaptureBot.class, match.getWhitePlayer().getClass());
        Assertions.assertSame(RandomBot.class, match.getBlackPlayer().getClass());
    }

    @Test
    public void testTime(){
        Match match = matchBuilder.buildMatch(0);

        Assertions.assertEquals(Settings.getInstance().getTime(), match.getWhitePlayer().getTime());
        Assertions.assertEquals(Settings.getInstance().getTime(), match.getBlackPlayer().getTime());

        Settings.getInstance().increaseTime();
        match = matchBuilder.buildMatch(0);

        Assertions.assertEquals(Settings.getInstance().getTime(), match.getWhitePlayer().getTime());
        Assertions.assertEquals(Settings.getInstance().getTime(), match.getBlackPlayer().getTime());
    }

    @AfterEach
    public void resetSettings(){
        Settings.getInstance().reset();
    }
}
