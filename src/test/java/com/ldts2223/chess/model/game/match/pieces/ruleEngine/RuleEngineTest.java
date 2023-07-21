package com.ldts2223.chess.model.game.match.pieces.ruleEngine;

import com.ldts2223.chess.model.Position;
import com.ldts2223.chess.model.game.match.Match;
import com.ldts2223.chess.model.game.match.MatchBuilder;
import com.ldts2223.chess.model.game.match.pieces.Bishop;
import com.ldts2223.chess.model.game.match.pieces.Piece;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultBishopMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.DefaultRookMovement;
import com.ldts2223.chess.model.game.match.pieces.ruleEngine.Movements.Movement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.HashSet;

public class RuleEngineTest {

    @Test
    void addMovements(){
        HashSet<Movement> movements = new HashSet<>();
        movements.add(new DefaultBishopMovement());
        movements.add(new DefaultRookMovement());

        HashSet<Movement> expected = new HashSet<>();
        expected.add(new DefaultBishopMovement());
        expected.add(new DefaultRookMovement());

        Assertions.assertTrue(expected.size() == movements.size());
    }

    @Test
    void getPossiblePlays() {
        RuleEngine ruleEngine = new DefaultBishopRules();
        Match match = new MatchBuilder().buildMatch(600);
        Piece piece = new Bishop(new Position(8,8),true);
        HashSet<Movement> movements = new HashSet<>(
                Arrays.asList(
                        Mockito.mock(Movement.class),
                        Mockito.mock(Movement.class)));

        ruleEngine.setMovements(movements);
        ruleEngine.getPossiblePlays(match, piece);

        for(Movement movement: movements){
            Mockito.verify(movement).getPossiblePlays(match, piece, ruleEngine.getCaptureGenerator());
        }
    }
}
