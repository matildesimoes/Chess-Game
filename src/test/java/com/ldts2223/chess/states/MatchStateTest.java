package com.ldts2223.chess.states;

import com.ldts2223.chess.model.game.match.Match;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchStateTest {

    private MatchState matchState;
    private Match match = new Match(600);

    @BeforeEach
    void setUp() {
        matchState = new MatchState(match);
    }

    @Test
    void testGetModel(){
        Assertions.assertEquals(match, matchState.getModel());
    }
}
