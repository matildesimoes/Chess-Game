package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultPawnMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultPawnRulesTest {

    private DefaultPawnRules defaultPawnRules;
    private DefaultPawnMovement defaultPawnMovement;

    @BeforeEach
    void helper(){
        defaultPawnRules = new DefaultPawnRules();
        defaultPawnMovement = new DefaultPawnMovement();
    }

    @Test
    void testConstructor() {
        for(Movement movement: defaultPawnRules.getMovements()){
            Assertions.assertEquals(movement.getClass(), defaultPawnMovement.getClass());
        }
    }

    @Test
    void testClone() {
        RuleEngine clonedPawnRules = defaultPawnRules.clone();
        Assertions.assertEquals(defaultPawnRules.getClass(), clonedPawnRules.getClass());
    }
}
