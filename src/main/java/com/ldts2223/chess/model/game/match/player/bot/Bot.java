package com.ldts2223.chess.model.game.match.player.bot;

import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.player.Player;
import com.ldts2223.chess.model.game.match.plays.Play;

import java.util.Random;

public abstract class Bot extends Player {

    private double timeToWait = 0;
    private long beginWait;
    private boolean waiting = false;

    public Bot(long initialTime, boolean white) {
        super(initialTime,white);
    }
    private void beginWaiting(long time){
        if (!waiting) {
            beginWait = time;
            timeToWait = (new Random().nextInt(6) + 0.6) * 1000;
            waiting = true;
        }
    }

    private boolean waitedTime(long time){
        return (time - beginWait >= timeToWait);
    }

    public void play(long time, Match match, Input.ACTION action, Position click) {
        updateTime(time);
        beginWaiting(time);
        if (waitedTime(time)) {
            waiting = false;
            Play selectedPlay = selectPlay(match);
            selectedPlay.execute(match);
            match.switchTurn(time);
        }
    }

    public abstract Play selectPlay(Match match);

    abstract public Player clone();
}
