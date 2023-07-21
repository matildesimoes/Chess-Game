package com.ldts2223.chess.model.game.match.pieces.ruleEngine.movements;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultKingRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultKingMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class DefaultKingMovementTest {

    @Test
    public void testGetPossiblePlays() {
        Match match = new MatchBuilder().buildMatch(600);
        RuleEngine ruleEngine = new DefaultKingRules();
        DefaultKingMovement defaultKingMovement = new DefaultKingMovement();
        Piece king = null;

        for(Piece piece : match.getActivePlayer().getPieces()){
            if (piece.getImage() == '_') {
                king = piece;
                king.setPosition(new Position(7, 6));
                break;
            }
        }

        HashSet<Play> plays = defaultKingMovement.getPossiblePlays(match, king, ruleEngine.getCaptureGenerator());

        Assertions.assertEquals(5, plays.size());

        Assertions.assertTrue(plays.contains(new Move(new Position(6, 6), king)));
        Assertions.assertTrue(plays.contains(new Move(new Position(6, 5), king)));
        Assertions.assertTrue(plays.contains(new Move(new Position(7, 5), king)));
        Assertions.assertTrue(plays.contains(new Move(new Position(8, 5), king)));
        Assertions.assertTrue(plays.contains(new Move(new Position(8, 6), king)));
        Assertions.assertFalse(plays.contains(new Move(new Position(6, 7), king)));
        Assertions.assertFalse(plays.contains(new Move(new Position(7, 7), king)));
        Assertions.assertFalse(plays.contains(new Move(new Position(8, 7), king)));
    }
}
