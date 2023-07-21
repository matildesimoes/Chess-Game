package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultBishopMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultHorseMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultRookMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HorseWithQueenRulesTest {

    private HorseWithQueenRules horseWithQueenRules;
    private DefaultBishopMovement defaultBishopMovement;
    private DefaultRookMovement defaultRookMovement;
    private DefaultHorseMovement defaultHorseMovement;

    @BeforeEach
    void helper(){
        horseWithQueenRules = new HorseWithQueenRules();
        defaultBishopMovement = new DefaultBishopMovement();
        defaultRookMovement = new DefaultRookMovement();
        defaultHorseMovement = new DefaultHorseMovement();
    }

    @Test
    void testConstructor() {
        for(Movement movement: horseWithQueenRules.getMovements()){
            Assertions.assertTrue(
                    (movement.getClass() == defaultBishopMovement.getClass()) ||
                            (movement.getClass() == defaultRookMovement.getClass()) ||
                            (movement.getClass() == defaultHorseMovement.getClass()));
        }
    }

    @Test
    void testClone() {
        RuleEngine clonedHorseAsQueenRules = horseWithQueenRules.clone();
        Assertions.assertEquals(horseWithQueenRules.getClass(), clonedHorseAsQueenRules.getClass());
    }
}
