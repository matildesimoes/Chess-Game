package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class EnPassantTest {

    @Test
    public void testConstructor(){
        EnPassant enPassant = new EnPassant(new Position(4,4),new Pawn(new Position(3,4),true),new Pawn(new Position(4,4),false));

        Assertions.assertEquals(new Position(4,4),enPassant.getDestination());
        Assertions.assertEquals(new Position(3,4),enPassant.getSelectedPiece().getPosition());
        Assertions.assertEquals("#FF8C00", enPassant.getColor());
    }

    @Test
    public void execute(){
        Piece pawn1 = new Pawn(new Position(7, 4), true);
        Piece pawn2 = new Pawn(new Position(8, 4), false);

        Match match = new MatchBuilder().buildMatch(0);
        match.setPawnThatDoubled(pawn2);
        match.getActivePlayer().setPieces(new HashSet<>(Arrays.asList(pawn1)));
        match.getIdlePlayer().setPieces(new HashSet<>(Arrays.asList(pawn2)));

        Position destination = new Position(8,3);
        EnPassant enPassant = new EnPassant(destination, pawn1, pawn2);

        enPassant.furtherExecute(match);

        Assertions.assertEquals(0, match.getIdlePlayer().getPieces().size());
        Assertions.assertEquals(destination, pawn1.getPosition());
    }
}
