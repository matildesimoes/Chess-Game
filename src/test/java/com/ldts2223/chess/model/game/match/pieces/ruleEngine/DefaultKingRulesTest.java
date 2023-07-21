package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultKingMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultKingRulesTest {

    private DefaultKingRules defaultKingRules;
    private DefaultKingMovement defaultKingMovement;

    @BeforeEach
    void helper(){
        defaultKingRules = new DefaultKingRules();
        defaultKingMovement = new DefaultKingMovement();
    }

    @Test
    void testConstructor() {
        for(Movement movement: defaultKingRules.getMovements()){
            Assertions.assertEquals(movement.getClass(), defaultKingMovement.getClass());
        }
    }

    @Test
    void testClone() {
        RuleEngine clonedKingRules = defaultKingRules.clone();
        Assertions.assertEquals(defaultKingRules.getClass(), clonedKingRules.getClass());
    }
}
