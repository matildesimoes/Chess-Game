package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultPawnMovement;

public class DefaultPawnRules extends RuleEngine {

    public DefaultPawnRules() {
        addMovement(new DefaultPawnMovement());
    }

    @Override
    public RuleEngine clone(){
        return new DefaultPawnRules();
    }
}
