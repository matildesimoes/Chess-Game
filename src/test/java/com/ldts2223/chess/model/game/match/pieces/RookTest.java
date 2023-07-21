package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultRookRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class RookTest {

    private Rook rook;

    @BeforeEach
    void helper() {
        rook = new Rook(new Position(4, 4), false);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(new Position(4, 4), rook.getPosition());
        Assertions.assertFalse(rook.isWhite());
        Assertions.assertEquals('|', rook.getImage());

        RuleEngine ruleEngine = rook.getRuleEngine();
        Assertions.assertTrue(ruleEngine.getClass() == DefaultRookRules.class);
    }

    @Test
    void cloneTest() {
        Piece clone = rook.clone();
        Assertions.assertEquals(rook.getPosition(), clone.getPosition());
        Assertions.assertEquals(rook.isWhite(), clone.isWhite());
        Assertions.assertEquals(rook.getRuleEngine().getClass(), clone.getRuleEngine().getClass());
    }

    @Test
    void getImage() {
        Assertions.assertEquals('|', rook.getImage());
    }

    @Test
    void setImage() {
        rook.setImage('R');
        Assertions.assertEquals('R', rook.getImage());
    }
}
