package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultBishopMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultHorseMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultRookMovement;

public class HorseWithQueenRules extends RuleEngine {

    public HorseWithQueenRules() {
        addMovement(new DefaultBishopMovement());
        addMovement(new DefaultRookMovement());
        addMovement(new DefaultHorseMovement());
    }

    @Override
    public RuleEngine clone(){
        return new HorseWithQueenRules();
    }
}
