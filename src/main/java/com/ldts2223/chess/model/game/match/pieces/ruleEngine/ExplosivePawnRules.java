package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultPawnMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.captureGenerator.ExplosiveCaptureEngine;

public class ExplosivePawnRules extends RuleEngine {

    public ExplosivePawnRules() {
        addMovement(new DefaultPawnMovement());
        setCaptureGenerator(new ExplosiveCaptureEngine());
    }

    @Override
    public RuleEngine clone(){
        return new ExplosivePawnRules();
    }
}

