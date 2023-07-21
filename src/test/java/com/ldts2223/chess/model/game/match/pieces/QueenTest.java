package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultQueenRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenTest {

    private Queen queen;

    @BeforeEach
    void helper() {
        queen = new Queen(new Position(6, 2), true);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(new Position(6, 2), queen.getPosition());
        Assertions.assertTrue(queen.isWhite());
        Assertions.assertEquals('{', queen.getImage());

        RuleEngine ruleEngine = queen.getRuleEngine();
        Assertions.assertTrue(ruleEngine.getClass() == DefaultQueenRules.class);
    }

    @Test
    void cloneTest() {
        Piece clone = queen.clone();
        Assertions.assertEquals(queen.getPosition(), clone.getPosition());
        Assertions.assertEquals(queen.isWhite(), clone.isWhite());
        Assertions.assertEquals(queen.getRuleEngine().getClass(), clone.getRuleEngine().getClass());
    }

    @Test
    void getImage() {
        assertEquals('{', queen.getImage());
    }

    @Test
    void setImage() {
        queen.setImage('Q');
        Assertions.assertEquals('Q', queen.getImage());
    }
}
