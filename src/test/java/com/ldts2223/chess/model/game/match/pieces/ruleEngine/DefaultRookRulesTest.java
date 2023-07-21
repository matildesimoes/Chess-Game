package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultRookMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultRookRulesTest {

    private DefaultRookRules defaultRookRules;
    private DefaultRookMovement defaultRookMovement;

    @BeforeEach
    void helper(){
        defaultRookRules = new DefaultRookRules();
        defaultRookMovement = new DefaultRookMovement();
    }

    @Test
    void testConstructor() {
        for(Movement movement: defaultRookRules.getMovements()){
            Assertions.assertEquals(movement.getClass(), defaultRookMovement.getClass());
        }
    }

    @Test
    void testClone() {
        RuleEngine clonedRookRules = defaultRookRules.clone();
        Assertions.assertEquals(defaultRookRules.getClass(), clonedRookRules.getClass());
    }
}
