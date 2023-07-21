package com.ldts2223.chess.model.game.match.pieces.ruleEngine.movements;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultRookRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultRookMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class DefaultRookMovementTest {

    @Test
    public void testGetPossiblePlays() {
        Match match = new MatchBuilder().buildMatch(600);
        RuleEngine ruleEngine = new DefaultRookRules();
        DefaultRookMovement defaultRookMovement = new DefaultRookMovement();
        Piece rook = null;
        Piece pawn = null;

        for(Piece piece : match.getActivePlayer().getPieces()){
            if (piece.getImage() == '|') {
                rook = piece;
                rook.setPosition(new Position(7, 4));
                break;
            }
        }

        for(Piece piece : match.getIdlePlayer().getPieces()){
            if(piece.getImage() == '`' && piece.getPosition().equals(new Position(7,2))){
                pawn = piece;
            }
        }

        HashSet<Play> plays = defaultRookMovement.getPossiblePlays(match, rook, ruleEngine.getCaptureGenerator());

        Assertions.assertEquals(11, plays.size());

        Assertions.assertTrue(plays.contains(new Move(new Position(6, 4), rook)));
        Assertions.assertTrue(plays.contains(new Move(new Position(5, 4), rook)));
        Assertions.assertTrue(plays.contains(new Move(new Position(4, 4), rook)));
        Assertions.assertTrue(plays.contains(new Move(new Position(3, 4), rook)));
        Assertions.assertTrue(plays.contains(new Move(new Position(2, 4), rook)));
        Assertions.assertTrue(plays.contains(new Move(new Position(1, 4), rook)));
        Assertions.assertTrue(plays.contains(new Capture(new Position(7, 2), rook, pawn)));
        Assertions.assertTrue(plays.contains(new Move(new Position(7, 3), rook)));
        Assertions.assertTrue(plays.contains(new Move(new Position(8, 4), rook)));
        Assertions.assertTrue(plays.contains(new Move(new Position(7, 5), rook)));
        Assertions.assertTrue(plays.contains(new Move(new Position(7, 6), rook)));

        Assertions.assertFalse(plays.contains(new Move(new Position(7, 7), rook)));
        Assertions.assertFalse(plays.contains(new Move(new Position(7, 8), rook)));
        Assertions.assertFalse(plays.contains(new Move(new Position(7, 1), rook)));
    }
}
