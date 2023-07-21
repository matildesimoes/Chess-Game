package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultBishopRules;

public class Bishop extends Piece {

    public Bishop(Position position, boolean isWhite){
        super(position, isWhite);
        setImage('}');
        setRuleEngine(new DefaultBishopRules());
    }

    public Piece clone(){
        Bishop bishop = new Bishop(getPosition(), isWhite());
        bishop.setRuleEngine(getRuleEngine());
        return bishop;
    }
}
