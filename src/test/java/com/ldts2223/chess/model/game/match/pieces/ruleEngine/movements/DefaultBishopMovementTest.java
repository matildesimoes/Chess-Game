package com.ldts2223.chess.model.game.match.pieces.ruleEngine.movements;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultBishopRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultBishopMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import com.ldts2223.chess.model.game.match.plays.Capture;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class DefaultBishopMovementTest {

    @Test
    public void testGetPossiblePlays() {
        Match match = new MatchBuilder().buildMatch(600);
        RuleEngine ruleEngine = new DefaultBishopRules();
        DefaultBishopMovement defaultBishopMovement = new DefaultBishopMovement();
        Piece bishop = null;
        Piece pawn = null;

        for(Piece piece : match.getActivePlayer().getPieces()){
            if (piece.getImage() == '}') {
                bishop = piece;
                bishop.setPosition(new Position(6, 6));
                break;
            }
        }

        for(Piece piece : match.getIdlePlayer().getPieces()){
            if(piece.getImage() == '`' && piece.getPosition().equals(new Position(2,2))){
                pawn = piece;
            }
        }

        HashSet<Play> plays = defaultBishopMovement.getPossiblePlays(match, bishop, ruleEngine.getCaptureGenerator());

        Assertions.assertEquals(6, plays.size());

        Assertions.assertTrue(plays.contains(new Move(new Position(5, 5), bishop)));
        Assertions.assertTrue(plays.contains(new Move(new Position(4, 4), bishop)));
        Assertions.assertTrue(plays.contains(new Move(new Position(3, 3), bishop)));
        Assertions.assertTrue(plays.contains(new Capture(new Position(2, 2), bishop, pawn)));
        Assertions.assertTrue(plays.contains(new Move(new Position(7, 5), bishop)));
        Assertions.assertTrue(plays.contains(new Move(new Position(8, 4), bishop)));
        Assertions.assertFalse(plays.contains(new Move(new Position(7, 7), bishop)));
        Assertions.assertFalse(plays.contains(new Move(new Position(9, 3), bishop)));
    }
}

