package com.ldts2223.chess.model.game.match;

import com.ldts2223.chess.model.game.match.gamemode.GameMode;
import com.ldts2223.chess.model.game.match.player.*;
import com.ldts2223.chess.model.game.match.player.bot.CaptureBot;
import com.ldts2223.chess.model.game.match.player.bot.RandomBot;

public class MatchBuilder {

    private Settings settings = Settings.getInstance();

    public Match buildMatch(long time){
        Match match = new Match(time);

        match.setBlackPlayer(createPlayer(false, time));
        match.setWhitePlayer(createPlayer(true, time));

        GameMode gameMode = settings.getGamemode();

        match.setBoard(new Board(gameMode.getBoardSize()));
        gameMode.load(match);

        return match;
    }

    private Player createPlayer(boolean isWhite, long time){
        Player player = null;
        String playerType;
        if (isWhite)
            playerType = settings.getWhitePlayer();
        else
            playerType = settings.getBlackPlayer();

        if (playerType == "User")
            player = new User(time,isWhite);
        else if (playerType == "Easy Bot")
            player = new RandomBot(time,isWhite);
        else if (playerType == "Medium Bot")
            player = new CaptureBot(time,isWhite);

        player.setTime(settings.getTime());

        return player;
    }
}
