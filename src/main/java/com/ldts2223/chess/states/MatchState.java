package com.ldts2223.chess.states;

import com.ldts2223.chess.controller.Controller;
import com.ldts2223.chess.controller.game.MatchController;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.viewer.Viewer;
import com.ldts2223.chess.viewer.match.MatchViewer;

public class MatchState extends State<Match>{

    public MatchState(Match match) {
        super(match);
    }

    @Override
    protected Viewer<Match> getViewer(){
        return new MatchViewer(getModel());
    }

    @Override
    protected Controller<Match> getController(){
        return new MatchController(getModel());
    }
}
