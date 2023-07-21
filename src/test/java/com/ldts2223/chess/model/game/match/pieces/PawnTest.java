package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultPawnRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

    private Pawn pawn;

    @BeforeEach
    void helper() {
        pawn = new Pawn(new Position(3, 5), false);
    }

    @Test
    public void constructorTest() {
        Assertions.assertEquals(new Position(3, 5), pawn.getPosition());
        Assertions.assertFalse(pawn.isWhite());
        Assertions.assertEquals('`', pawn.getImage());

        RuleEngine ruleEngine = pawn.getRuleEngine();
        Assertions.assertTrue(ruleEngine.getClass() == DefaultPawnRules.class);
    }

    @Test
    void cloneTest() {
        Piece clone = pawn.clone();
        Assertions.assertEquals(pawn.getPosition(), clone.getPosition());
        Assertions.assertEquals(pawn.isWhite(), clone.isWhite());
        Assertions.assertEquals(pawn.getRuleEngine().getClass(), clone.getRuleEngine().getClass());
    }

    @Test
    void getImage() {
        assertEquals('`', pawn.getImage());
    }

    @Test
    void setImage() {
        pawn.setImage('P');
        assertEquals('P', pawn.getImage());
    }
}
