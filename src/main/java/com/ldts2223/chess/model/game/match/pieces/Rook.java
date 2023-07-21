package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultRookRules;

public class Rook extends Piece {

    public Rook(Position position, boolean isWhite){
        super(position, isWhite);
        setImage('|');
        setRuleEngine(new DefaultRookRules());
    }

    public Piece clone(){
        Rook rook = new Rook(getPosition(), isWhite());
        rook.setRuleEngine(getRuleEngine());
        return rook;
    }
}
