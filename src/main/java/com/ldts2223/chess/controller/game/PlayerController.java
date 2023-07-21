package com.ldts2223.chess.controller.game;

import com.ldts2223.chess.Game;
import com.ldts2223.chess.controller.Controller;
import com.ldts2223.chess.gui.Input;
import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.player.Player;


public class PlayerController extends Controller<Match> {

    public PlayerController(Match match){
        super(match);
    }

    public void step(Game game, Input input, long time){
        Player activePlayer = getModel().getActivePlayer();
        Position clickPosition = getBoardPosition(input);
        activePlayer.play(time, getModel(), input.getAction(), clickPosition);
    }

    private Position getBoardPosition(Input input) {
        int x = 1 + (input.getPosition().getX() - 2) / getModel().getBoard().getSquareSize();
        int y = 1 + (input.getPosition().getY() - 2) / getModel().getBoard().getSquareSize();

        Position trueClick = new Position(x, y);
        return trueClick;
    }
}
