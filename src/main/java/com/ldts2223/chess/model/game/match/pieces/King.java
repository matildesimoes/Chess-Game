package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultKingRules;

public class King extends Piece {

    public King(Position position, boolean isWhite) {
        super(position, isWhite);
        setImage('_');
        setRuleEngine(new DefaultKingRules());
    }

    public Piece clone(){
        King king = new King(getPosition(), isWhite());
        king.setRuleEngine(getRuleEngine());
        return king;
    }
}