package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultHorseMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DefaultHorseRulesTest {

    private DefaultHorseRules defaultHorseRules;
    private DefaultHorseMovement defaultHorseMovement;

    @BeforeEach
    void helper(){
        defaultHorseRules = new DefaultHorseRules();
        defaultHorseMovement = new DefaultHorseMovement();
    }

    @Test
    void testConstructor() {
        for(Movement movement: defaultHorseRules.getMovements()){
            Assertions.assertEquals(movement.getClass(), defaultHorseMovement.getClass());
        }
    }

    @Test
    void testClone() {
        RuleEngine clonedHorseRules = defaultHorseRules.clone();
        Assertions.assertEquals(defaultHorseRules.getClass(), clonedHorseRules.getClass());
    }
}
