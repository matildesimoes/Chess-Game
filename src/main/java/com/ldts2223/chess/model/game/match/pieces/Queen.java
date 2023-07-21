package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultQueenRules;

public class Queen extends Piece {

    public Queen(Position position, boolean isWhite){
        super(position, isWhite);
        setImage('{');
        setRuleEngine(new DefaultQueenRules());
    }

    public Piece clone(){
        Queen queen = new Queen(getPosition(), isWhite());
        queen.setRuleEngine(getRuleEngine());
        return queen;
    }
}
