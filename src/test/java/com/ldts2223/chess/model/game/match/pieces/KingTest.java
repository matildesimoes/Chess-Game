package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultKingRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class KingTest {

    private King king;

    @BeforeEach
    void helper() {
        king = new King(new Position(6, 7), true);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(new Position(6, 7), king.getPosition());
        Assertions.assertTrue(king.isWhite());
        Assertions.assertEquals('_', king.getImage());

        RuleEngine ruleEngine = king.getRuleEngine();
        Assertions.assertTrue(ruleEngine.getClass() == DefaultKingRules.class);
    }

    @Test
    void cloneTest() {
        Piece clone = king.clone();
        Assertions.assertEquals(king.getPosition(), clone.getPosition());
        Assertions.assertEquals(king.isWhite(), clone.isWhite());
        Assertions.assertEquals(king.getRuleEngine().getClass(), clone.getRuleEngine().getClass());
    }

    @Test
    void getImage() {
        Assertions.assertEquals('_', king.getImage());
    }

    @Test
    void setImage() {
        king.setImage('K');
        Assertions.assertEquals('K', king.getImage());
    }
}
