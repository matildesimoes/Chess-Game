package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultHorseMovement;

public class DefaultHorseRules extends RuleEngine {

    public DefaultHorseRules() {
        addMovement(new DefaultHorseMovement());
    }

    @Override
    public RuleEngine clone(){
        return new DefaultHorseRules();
    }
}
