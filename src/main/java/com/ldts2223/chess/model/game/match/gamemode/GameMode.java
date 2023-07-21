package com.ldts2223.chess.model.game.match.gamemode;

import com.ldts2223.chess.model.game.match.Match;

public interface GameMode {
    void load(Match match);
    int getBoardSize();
    String getTitle();
}
