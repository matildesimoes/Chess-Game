package com.ldts2223.chess.model.game.match.player.bot;

import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.plays.Play;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class BotTest {

    private Bot bot;
    private Play play;
    private Match match;

    @BeforeEach
    void helper(){
        bot = Mockito.spy(new RandomBot(0, true));
        play = Mockito.mock(Play.class);
        match = Mockito.mock(Match.class);
        Mockito.doReturn(play).when(bot).selectPlay(match);
        bot.play(0, match, Input.ACTION.none, new Position(0,0));
    }

    @Test
    public void playWaitedTime() {
        bot.play(7000, match, Input.ACTION.none, new Position(0,0));

        Mockito.verify(bot, Mockito.times(2)).updateTime(Mockito.anyLong());
        Mockito.verify(bot).selectPlay(match);
        Mockito.verify(play).execute(match);
        Mockito.verify(match).switchTurn(7000);
    }

    @Test
    public void playNotWaitedTime() {
        bot.play(1, match, Input.ACTION.none, new Position(0,0));

        Mockito.verify(bot, Mockito.times(2)).updateTime(Mockito.anyLong());
        Mockito.verify(bot, Mockito.times(0)).selectPlay(match);
        Mockito.verify(play, Mockito.times(0)).execute(match);
        Mockito.verify(match, Mockito.times(0)).switchTurn(7000);
    }
}
