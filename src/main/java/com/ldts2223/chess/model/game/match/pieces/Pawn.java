package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultPawnRules;

public class Pawn extends Piece {

    public Pawn(Position position, boolean isWhite){
        super(position, isWhite);
        setImage('`');
        setRuleEngine(new DefaultPawnRules());
    }

    public Piece clone(){
        Pawn pawn = new Pawn(getPosition(), isWhite());
        pawn.setRuleEngine(getRuleEngine());
        return pawn;
    }
}
