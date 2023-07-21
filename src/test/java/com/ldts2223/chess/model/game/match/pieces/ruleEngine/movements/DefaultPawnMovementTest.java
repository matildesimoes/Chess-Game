package com.ldts2223.chess.model.game.match.pieces.ruleEngine.movements;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultPawnRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultPawnMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class DefaultPawnMovementTest {

    @Test
    public void testGetPossiblePlays() {
        Match match = new MatchBuilder().buildMatch(600);
        RuleEngine ruleEngine = new DefaultPawnRules();
        DefaultPawnMovement defaultPawnMovement = new DefaultPawnMovement();
        Piece pawn = null;
        Piece pawnCapture1 = null;
        Piece pawnCapture2 = null;

        for(Piece piece : match.getActivePlayer().getPieces()){
            if (piece.getImage() == '`') {
                pawn = piece;
                pawn.setPosition(new Position(2, 3));
                break;
            }
        }

        for(Piece piece : match.getIdlePlayer().getPieces()){
            if(piece.getImage() == '`' && piece.getPosition().equals(new Position(1,2))){
                pawnCapture1 = piece;
            }
            if(piece.getImage() == '`' && piece.getPosition().equals(new Position(3,2))){
                pawnCapture2 = piece;
            }
        }

        HashSet<Play> plays = defaultPawnMovement.getPossiblePlays(match, pawn, ruleEngine.getCaptureGenerator());

        Assertions.assertEquals(2, plays.size());

        Assertions.assertTrue(plays.contains(new Capture(new Position(1, 2), pawn, pawnCapture1)));
        Assertions.assertTrue(plays.contains(new Capture(new Position(3, 2), pawn, pawnCapture2)));
        Assertions.assertFalse(plays.contains(new Move(new Position(2, 2), pawn)));
    }
}
