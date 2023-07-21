package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultBishopMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultRookMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultQueenRulesTest {

    private DefaultQueenRules defaultQueenRules;
    private DefaultBishopMovement defaultBishopMovement;
    private DefaultRookMovement defaultRookMovement;

    @BeforeEach
    void helper(){
        defaultQueenRules = new DefaultQueenRules();
        defaultBishopMovement = new DefaultBishopMovement();
        defaultRookMovement = new DefaultRookMovement();
    }

    @Test
    void testConstructor() {
        for(Movement movement: defaultQueenRules.getMovements()){
            Assertions.assertTrue((movement.getClass() == defaultBishopMovement.getClass()) ||
                    (movement.getClass() == defaultRookMovement.getClass()));
        }
    }

    @Test
    void testClone() {
        RuleEngine clonedQueenRules = defaultQueenRules.clone();
        Assertions.assertEquals(defaultQueenRules.getClass(), clonedQueenRules.getClass());
    }
}
