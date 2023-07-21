package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultKingMovement;

public class DefaultKingRules extends RuleEngine {

    public DefaultKingRules() {
        addMovement(new DefaultKingMovement());
    }

    @Override
    public RuleEngine clone(){
        return new DefaultKingRules();
    }
}
