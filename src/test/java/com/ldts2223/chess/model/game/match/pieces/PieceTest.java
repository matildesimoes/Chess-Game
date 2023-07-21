package com.ldts2223.chess.model.game.match.pieces;

import com.ldts2223.chess.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PieceTest {

    @Test
    void testEquals() {
        Piece king1 = new King(new Position(2,3), true);
        Piece king2 = new King(new Position(2,3), true);
        Assertions.assertEquals(king1,king2);

        Piece king3 = new King(new Position(2,3), false);
        Assertions.assertFalse(king1.equals(king3));
    }
}
