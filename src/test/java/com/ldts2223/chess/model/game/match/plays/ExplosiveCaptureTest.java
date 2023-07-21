package com.ldts2223.chess.model.game.match.plays;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.Bishop;
import com.ldts2223.chess.model.game.match.pieces.Pawn;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

public class ExplosiveCaptureTest {

    @Test
    public void testConstructor(){
        Position destination = new Position(3, 4);
        Pawn selectedPiece = new Pawn(new Position(3, 3),true);
        Bishop captured = new Bishop(new Position(4, 4),false);

        ExplosiveCapture capture = new ExplosiveCapture(destination, selectedPiece, captured);

        Assertions.assertEquals(destination, capture.getDestination());
        Assertions.assertEquals(selectedPiece, capture.getSelectedPiece());
    }

    @Test
    public void execute(){
        Piece pawnExplode = new Pawn(new Position(7, 5), true);

        Piece pawn2 = new Pawn(new Position(8, 4), false);
        Piece pawn3 = new Pawn(new Position(7, 4), false);
        Piece pawn4 = new Pawn(new Position(8, 3), false);
        Piece pawn5 = new Pawn(new Position(7, 3), false);
        Piece pawn6 = new Pawn(new Position(8, 5), false);
        Piece pawn7 = new Pawn(new Position(8, 2), false);

        Match match = new MatchBuilder().buildMatch(0);
        match.setPawnThatDoubled(pawn2);
        match.getActivePlayer().setPieces(new HashSet<>(Arrays.asList(pawnExplode)));
        match.getIdlePlayer().setPieces(new HashSet<>(Arrays.asList(pawn2, pawn3, pawn4, pawn5, pawn6, pawn7)));

        Position destination = pawn2.getPosition();
        ExplosiveCapture explosiveCapture = new ExplosiveCapture(destination, pawnExplode, pawn2);

        explosiveCapture.furtherExecute(match);

        Assertions.assertEquals(1, match.getIdlePlayer().getPieces().size());
        Assertions.assertEquals(0, match.getActivePlayer().getPieces().size());
        for (Piece piece: match.getIdlePlayer().getPieces())
            Assertions.assertEquals(pawn7, piece);
    }
}
