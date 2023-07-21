package com.ldts2223.chess.model.game.match.pieces.ruleEngine.movements;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.DefaultHorseRules;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultHorseMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.RuleEngine;
import com.ldts2223.chess.model.game.match.plays.Move;
import com.ldts2223.chess.model.game.match.plays.Play;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

public class DefaultHorseMovementTest {

    @Test
    public void testGetPossiblePlays() {
        Match match = new MatchBuilder().buildMatch(600);
        RuleEngine ruleEngine = new DefaultHorseRules();
        DefaultHorseMovement defaultHorseMovement = new DefaultHorseMovement();
        Piece horse = null;

        for(Piece piece : match.getActivePlayer().getPieces()){
            if (piece.getImage() == '^') {
                horse = piece;
                horse.setPosition(new Position(2, 6));
                break;
            }
        }

        HashSet<Play> plays = defaultHorseMovement.getPossiblePlays(match, horse, ruleEngine.getCaptureGenerator());

        Assertions.assertEquals(3, plays.size());

        Assertions.assertTrue(plays.contains(new Move(new Position(1, 4), horse)));
        Assertions.assertTrue(plays.contains(new Move(new Position(3, 4), horse)));
        Assertions.assertTrue(plays.contains(new Move(new Position(4, 5), horse)));
        Assertions.assertFalse(plays.contains(new Move(new Position(3, 8), horse)));
        Assertions.assertFalse(plays.contains(new Move(new Position(1, 8), horse)));
    }
}
