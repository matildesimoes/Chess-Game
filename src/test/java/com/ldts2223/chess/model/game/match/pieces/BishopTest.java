package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultBishopRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BishopTest {

    private Bishop bishop;

    @BeforeEach
    void helper() {
        bishop = new Bishop(new Position(3, 4), true);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(new Position(3, 4), bishop.getPosition());
        Assertions.assertTrue(bishop.isWhite());
        Assertions.assertEquals('}', bishop.getImage());

        RuleEngine ruleEngine = bishop.getRuleEngine();
        Assertions.assertTrue(ruleEngine.getClass() == DefaultBishopRules.class);
    }

    @Test
    void cloneTest() {
        Piece clone = bishop.clone();
        Assertions.assertEquals(bishop.getPosition(), clone.getPosition());
        Assertions.assertEquals(bishop.isWhite(), clone.isWhite());
        Assertions.assertEquals(bishop.getRuleEngine().getClass(), clone.getRuleEngine().getClass());
    }

    @Test
    void getImage() {
        Assertions.assertEquals('}', bishop.getImage());
    }

    @Test
    void setImage() {
        bishop.setImage('B');
        Assertions.assertEquals('B', bishop.getImage());
    }
}
