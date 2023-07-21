package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultBishopMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultBishopRulesTest {

    private DefaultBishopRules defaultBishopRules;
    private DefaultBishopMovement defaultBishopMovement;

    @BeforeEach
    void helper(){
        defaultBishopRules = new DefaultBishopRules();
        defaultBishopMovement = new DefaultBishopMovement();
    }

    @Test
    void testConstructor() {
        for(Movement movement: defaultBishopRules.getMovements()){
            Assertions.assertEquals(movement.getClass(), defaultBishopMovement.getClass());
        }
    }

    @Test
    void testClone() {
        RuleEngine clonedBishopRules = defaultBishopRules.clone();
        Assertions.assertEquals(defaultBishopRules.getClass(), clonedBishopRules.getClass());
    }
}
