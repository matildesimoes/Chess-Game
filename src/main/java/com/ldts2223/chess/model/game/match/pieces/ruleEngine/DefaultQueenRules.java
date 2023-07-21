package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultBishopMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultRookMovement;

public class DefaultQueenRules extends RuleEngine  {

    public DefaultQueenRules() {
        addMovement(new DefaultBishopMovement());
        addMovement(new DefaultRookMovement());
    }

    @Override
    public RuleEngine clone(){
        return new DefaultQueenRules();
    }
}
