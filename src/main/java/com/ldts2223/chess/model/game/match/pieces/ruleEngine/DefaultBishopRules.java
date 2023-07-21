package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultBishopMovement;

public class DefaultBishopRules extends RuleEngine {

    public DefaultBishopRules() {
        addMovement(new DefaultBishopMovement());
    }

    @Override
    public RuleEngine clone(){
        return new DefaultBishopRules();
    }
}
