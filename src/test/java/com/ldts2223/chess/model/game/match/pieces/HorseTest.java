package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultHorseRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HorseTest {

    private Horse horse;

    @BeforeEach
    void helper() {
        horse = new Horse(new Position(1, 8), false);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(new Position(1, 8), horse.getPosition());
        Assertions.assertFalse(horse.isWhite());
        Assertions.assertEquals('^', horse.getImage());

        RuleEngine ruleEngine = horse.getRuleEngine();
        Assertions.assertTrue(ruleEngine.getClass() == DefaultHorseRules.class);
    }

    @Test
    void cloneTest() {
        Piece clone = horse.clone();
        Assertions.assertEquals(horse.getPosition(), clone.getPosition());
        Assertions.assertEquals(horse.isWhite(), clone.isWhite());
        Assertions.assertEquals(horse.getRuleEngine().getClass(), clone.getRuleEngine().getClass());
    }

    @Test
    void getImage() {
        Assertions.assertEquals('^', horse.getImage());
    }

    @Test
    void setImage() {
        horse.setImage('H');
        Assertions.assertEquals('H', horse.getImage());
    }
}
