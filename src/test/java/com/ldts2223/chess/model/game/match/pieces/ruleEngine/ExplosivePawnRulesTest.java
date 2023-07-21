package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultPawnMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExplosivePawnRulesTest {

    private ExplosivePawnRules explosivePawnRules;
    private DefaultPawnMovement defaultPawnMovement;

    @BeforeEach
    void helper(){
        explosivePawnRules = new ExplosivePawnRules();
        defaultPawnMovement = new DefaultPawnMovement();
    }

    @Test
    void testConstructor() {
        for(Movement movement: explosivePawnRules.getMovements()){
            Assertions.assertEquals(movement.getClass(), defaultPawnMovement.getClass());
        }
    }

    @Test
    void testClone() {
        RuleEngine clonedExplosivePawnRules = explosivePawnRules.clone();
        Assertions.assertEquals(explosivePawnRules.getClass(), clonedExplosivePawnRules.getClass());
    }
}
