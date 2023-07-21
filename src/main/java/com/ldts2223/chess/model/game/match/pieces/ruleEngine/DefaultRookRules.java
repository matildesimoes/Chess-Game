package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultRookMovement;

public class DefaultRookRules extends RuleEngine {

    public DefaultRookRules() {
        addMovement(new DefaultRookMovement());
    }

    @Override
    public RuleEngine clone(){
        return new DefaultRookRules();
    }
}
