package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultHorseRules;

public class Horse extends Piece {

    public Horse(Position position, boolean isWhite) {
        super(position, isWhite);
        setImage('^');
        setRuleEngine(new DefaultHorseRules());
    }

    public Piece clone(){
        Horse horse = new Horse(getPosition(), isWhite());
        horse.setRuleEngine(getRuleEngine());
        return horse;
    }
}

