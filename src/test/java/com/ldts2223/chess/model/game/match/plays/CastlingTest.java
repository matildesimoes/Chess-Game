package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.King;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class CastlingTest {

    @Test
    public void testConstructor(){
        Castling castling = new Castling(new Position(2,2),new King(new Position(6,2),true),new Rook(new Position(2,2),true),false);

        Assertions.assertEquals(new Position(2,2),castling.getDestination());
        Assertions.assertEquals(new Position(6,2),castling.getSelectedPiece().getPosition());
        Assertions.assertEquals("#FF8C00", castling.getColor());
        Assertions.assertFalse(castling.rookRight);
    }

    @Test
    public void execute(){
        Piece king = new King(new Position(5, 8), true);
        Piece rook = new Rook(new Position(8, 8), true);

        Match match = new MatchBuilder().buildMatch(0);
        match.getActivePlayer().setPieces(new HashSet<>(Arrays.asList(king, rook)));
        Castling castling = new Castling(new Position(7,8),king, rook, true);

        castling.furtherExecute(match);

        Assertions.assertEquals(new Position(7,8), king.getPosition());
        Assertions.assertEquals(new Position(6,8), rook.getPosition());
        Assertions.assertTrue(rook.hasMoved());
        Assertions.assertTrue(king.hasMoved());
    }
}
